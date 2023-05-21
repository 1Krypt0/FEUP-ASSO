package com.iota.core.service

import com.iota.core.config.broker.BrokerConfig
import com.iota.core.dto.model.DeviceDto
import com.iota.core.exception.device.ActionNameNotFoundException
import com.iota.core.exception.device.ActionNotFoundException
import com.iota.core.exception.device.DeviceNotFoundException
import com.iota.core.exception.device.MACAlreadyRegistered
import com.iota.core.model.*
import com.iota.core.repository.ActionRepository
import com.iota.core.repository.DeviceActionRepository
import com.iota.core.repository.DeviceRepository
import jakarta.transaction.Transactional
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeviceService(
    private val deviceRepository: DeviceRepository,
    private val deviceActionRepository: DeviceActionRepository,
    private val actionRepository: ActionRepository,
    private val brokerConfig: BrokerConfig
) {
    fun findAll(type: DeviceType?): List<Device> {
        return if (type == null) {
            deviceRepository.findAll().toList()
        } else {
            deviceRepository.findAllByType(type).toList()
        }
    }

    fun device(id: Long): Device {
        try {
            val device = deviceRepository.findById(id)

            return device.get()
        } catch (ex: NoSuchElementException) {
            throw DeviceNotFoundException(ex.message, ex.cause, id)
        }
    }

    fun new(dto: DeviceDto): Device {
        val device = dto.create()
        device.dataTopic = "DATA-" + device.macAddress
        device.actionTopic = "ACTION-" + device.macAddress
        device.status = NetworkStatus.CONNECTED

        try {
            return saveDevice(device, dto)
        } catch (ex: DataIntegrityViolationException) {
            val rootCause = ex.rootCause?.message

            // if a mac address is registered between saving and validating the DTO, then the save() function will throw
            // this is a precautionary measure if that ever happens
            if (rootCause != null) {
                if (rootCause.contains("UC_MAC")) {
                    throw MACAlreadyRegistered("already registered", ex.cause, "macAddress")
                }
            }
            throw ex
        }
    }

    @Transactional
    fun saveDevice(device: Device, deviceDto: DeviceDto): Device {
        val newDevice = deviceRepository.save(device)

        for (deviceAction in deviceDto.actions) {
            var realAction: Action? = null

            try {
                val action: Optional<Action> = actionRepository.findByName(deviceAction.actionName)
                realAction = action.get()
            } catch (ex: NoSuchElementException) {
                throw ActionNameNotFoundException(ex.message, ex.cause, deviceAction.actionName)
            }

            val realDeviceAction = deviceAction.create()

            realDeviceAction.device = newDevice
            realDeviceAction.action = realAction

            deviceActionRepository.save(realDeviceAction)
        }

        return deviceRepository.findById(newDevice.id).get()
    }

    fun delete(id: Long) {
        // If the device is not found, then this will throw a DeviceNotFoundException
        device(id)

        deviceRepository.deleteById(id)
    }

    fun deviceActions(id: Long): Set<DeviceAction> {
        val device = device(id)

        return device.deviceActions
    }
}
