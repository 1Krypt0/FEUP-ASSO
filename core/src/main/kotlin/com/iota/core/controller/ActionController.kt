package com.iota.core.controller

import com.iota.core.dto.action.ActionGet
import com.iota.core.dto.device.DeviceUpdate
import com.iota.core.dto.model.ActionDto
import com.iota.core.dto.model.DeviceDto
import com.iota.core.model.Device
import com.iota.core.service.ActionService
import com.iota.core.service.DeviceService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/actions")
class ActionController(
    private val service: ActionService,
) {
    @GetMapping("/{id}")
    fun action(@PathVariable id: Long): ActionGet {
        return ActionGet(service.action(id))
    }


    @PostMapping("/new")
    fun newAction(@Valid @RequestBody dto: ActionDto): ActionGet {
        return ActionGet(service.new(dto))
    }
}