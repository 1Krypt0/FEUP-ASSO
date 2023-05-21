package com.iota.core.controller

import com.iota.core.config.broker.BrokerConfig
import com.iota.core.dto.action.ActionGet
import com.iota.core.dto.action.DeviceActionGet
import com.iota.core.dto.device.DeviceDeleted
import com.iota.core.dto.device.DeviceGet
import com.iota.core.dto.device.DeviceUpdate
import com.iota.core.dto.model.DeviceDto
import com.iota.core.model.DeviceAction
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
        broker.subscribeDevice(id, device.dataTopic) // TODO Remove this for a better fault tolerance

        return DeviceGet(device)
    }

    @GetMapping("/{id}/status")
    fun deviceStatus(@PathVariable id: Long) = mapOf("status" to service.device(id).status)

//    @PostMapping("/{id}/value")
//    fun updateDeviceValue(@PathVariable id: Long, @Valid @RequestBody deviceUpdate: DeviceUpdate) {
//        val device = service.device(id)
//        // TODO
////        broker.addToTopic(device.actionTopic, deviceUpdate.value ?: "")
//    }

    @PostMapping("/new")
    fun newDevice(@Valid @RequestBody dto: DeviceDto): DeviceGet {
        val device = service.new(dto)

        device.id.let {
            broker.subscribeDevice(it, device.dataTopic)
        }

        return DeviceGet(device)
    }

    @DeleteMapping("/{id}")
    fun deleteDevice(@PathVariable id: Long): DeviceDeleted {
        service.delete(id)

        return DeviceDeleted(id)
    }

    @GetMapping("/{id}/actions")
    fun deviceActions(@PathVariable id: Long): List<DeviceActionGet> {
        return service.deviceActions(id).map { DeviceActionGet(it) }
    }
}
