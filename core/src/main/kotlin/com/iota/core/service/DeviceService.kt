package com.iota.core.service

import com.iota.core.config.broker.BrokerConfig
import com.iota.core.dto.model.DeviceDto
import com.iota.core.exception.device.DeviceNotFoundException
import com.iota.core.exception.device.MACAlreadyRegistered
import com.iota.core.model.Device
import com.iota.core.model.DeviceType
import com.iota.core.model.NetworkStatus
import com.iota.core.repository.DeviceRepository
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class DeviceService(
    private val deviceRepository: DeviceRepository,
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
            throw DeviceNotFoundException(ex.message, ex.cause, id);
        }
    }

    fun new(dto: DeviceDto): Device {
        val device = dto.create()
        device.dataTopic = "DATA-" + device.macAddress
        device.actionTopic = "ACTION-" + device.macAddress
        device.status = NetworkStatus.CONNECTED

        try {
            deviceRepository.save(device)
        } catch (ex: DataIntegrityViolationException) {
            val rootCause = ex.rootCause?.message

            // if a mac address is registered between saving and validating the DTO, then the save() function will throw
            // this is a precautionary measure if that ever happens
            if (rootCause != null) {
                if(rootCause.contains("UC_MAC")) {
                    throw MACAlreadyRegistered("already registered", ex.cause, "macAddress")
                }
            }
            throw ex;
        }
        return device
    }
}
