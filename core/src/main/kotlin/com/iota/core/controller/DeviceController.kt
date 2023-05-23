package com.iota.core.controller

import com.iota.core.config.broker.BrokerConfig
import com.iota.core.dto.action.DeviceActionGet
import com.iota.core.dto.action.DeviceActionGetSimple
import com.iota.core.dto.device.DeviceDeleted
import com.iota.core.dto.device.DeviceGet
import com.iota.core.dto.device.DeviceStatusUpdate
import com.iota.core.dto.device.DeviceUpdate
import com.iota.core.dto.model.DeviceDto
import com.iota.core.exception.device.ActionNotFoundException
import com.iota.core.exception.device.ActionNotUpdatableException
import com.iota.core.model.discoverability.StatusUpdate
import com.iota.core.service.DeviceService
import jakarta.validation.Valid
import kotlinx.serialization.json.Json
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/devices")
class DeviceController(
    private val service: DeviceService,
    brokerConfig: BrokerConfig
) {
    private val broker = brokerConfig.broker()

    @GetMapping("/")
    fun list(
        @RequestParam(required = false) category: Long?,
        @RequestParam(required = false) room: Long?
    ): List<DeviceGet> {
        return service.findAll(category, room).map { DeviceGet(it) }
    }

    @GetMapping("/{id}")
    fun device(@PathVariable id: Long): DeviceGet {
        val device = service.device(id)
        broker.subscribeDevice(id, device.dataTopic) // TODO Remove this for a better fault tolerance

        return DeviceGet(device)
    }

    @GetMapping("/{id}/status")
    fun deviceStatus(@PathVariable id: Long) = service.device(id).deviceActions.map { DeviceActionGetSimple(it) }

    @PostMapping("/{id}/value/{actionId}")
    fun updateDeviceValue(
        @PathVariable id: Long,
        @PathVariable actionId: String,
        @RequestBody update: DeviceStatusUpdate
    ) {
        val device = service.device(id)
        val action = device.deviceActions.find { it.idDevice == actionId }
        action?.let { deviceAction ->
            if (deviceAction.action?.updatable != true) {
                throw ActionNotUpdatableException()
            }
            deviceAction.validate(update.value)

            val statusUpdate = StatusUpdate(actionId, update.value)
            val json = Json.encodeToString(StatusUpdate.serializer(), statusUpdate)
            broker.addToTopic(device.actionTopic, json)
        } ?: kotlin.run {
            throw ActionNotFoundException(null, null, actionId.toLong())
        }
    }

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

    @PutMapping("/{id}")
    fun updateDevice(@PathVariable id: Long, @Valid @RequestBody dto: DeviceUpdate): DeviceGet {
        return DeviceGet(service.update(id, dto))
    }
}
