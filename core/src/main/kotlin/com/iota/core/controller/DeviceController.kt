package com.iota.core.controller

import com.iota.core.config.broker.BrokerConfig
import com.iota.core.dto.device.DeviceGet
import com.iota.core.dto.device.DeviceUpdate
import com.iota.core.dto.model.DeviceDto
import com.iota.core.model.DeviceType
import com.iota.core.service.DeviceService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/devices")
class DeviceController(
    private val service: DeviceService,
    private val brokerConfig: BrokerConfig
) {
    private val broker = brokerConfig.broker()

    @GetMapping("")
    fun list(@RequestParam(required = false) type: DeviceType?): List<DeviceGet> {
        return service.findAll(type).map { DeviceGet(it) }
    }

    @GetMapping("/{id}")
    fun device(@PathVariable id: Long): DeviceGet {
        val device = service.device(id)
        broker.subscribe(id, device.dataTopic) // TODO Remove this for a better fault tolerance

        return DeviceGet(device)
    }

    @GetMapping("/{id}/status")
    fun deviceStatus(@PathVariable id: Long) = mapOf("status" to service.device(id).status)

    @PostMapping("/{id}/value")
    fun updateDeviceValue(@PathVariable id: Long, @Valid @RequestBody deviceUpdate: DeviceUpdate) {
        val device = service.device(id)
        // TODO
//        broker.addToTopic(device.actionTopic, deviceUpdate.value ?: "")
    }

    @PostMapping("/new")
    fun newDevice(@Valid @RequestBody dto: DeviceDto): DeviceGet {
        val device = service.new(dto)

        device.id.let {
            broker.subscribe(it, device.dataTopic)
        }

        return DeviceGet(device)
    }
}
