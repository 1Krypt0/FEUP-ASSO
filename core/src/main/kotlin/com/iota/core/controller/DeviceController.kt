package com.iota.core.controller

import com.iota.core.config.broker.BrokerConfig
import com.iota.core.dto.model.DeviceDto
import com.iota.core.model.Device
import com.iota.core.service.DeviceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/devices")
class DeviceController(
    private val service: DeviceService,
    private val brokerConfig: BrokerConfig
) {
    private val broker = brokerConfig.broker()

    @GetMapping("")
    fun list(): List<Device> = service.findAll()

    @GetMapping("/{id}")
    fun device(@PathVariable id: Long): Device {
        val device = service.device(id)
        broker.subscribe(device.dataTopic)

        return device
    }

    @GetMapping("/{id}/status")
    fun deviceStatus(@PathVariable id: Long) = mapOf("status" to service.device(id).status)

    @PostMapping("/new")
    fun newDevice(@RequestBody dto: DeviceDto): Device {
        val broker = brokerConfig.broker()
        val device = service.new(dto)

        broker.subscribe(device.dataTopic)

        return device
    }
}
