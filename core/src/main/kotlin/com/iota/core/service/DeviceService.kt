package com.iota.core.service

import com.iota.core.config.broker.BrokerConfig
import com.iota.core.dto.model.DeviceDto
import com.iota.core.model.Device
import com.iota.core.model.NetworkStatus
import com.iota.core.repository.DeviceRepository
import org.springframework.stereotype.Service

@Service
class DeviceService(
    private val deviceRepository: DeviceRepository,
    private val brokerConfig: BrokerConfig
) {
    fun findAll(): List<Device> = deviceRepository.findAll().toList()

    fun device(id: Long): Device {
        val device = deviceRepository.findById(id)
        return device.get()
    }

    fun new(dto: DeviceDto): Device {
        val device = dto.create()
        device.dataTopic = "DATA-" + device.macAddress
        device.actionTopic = "ACTION-" + device.macAddress
        device.status = NetworkStatus.CONNECTED

        deviceRepository.save(device)
        return device
    }
}
