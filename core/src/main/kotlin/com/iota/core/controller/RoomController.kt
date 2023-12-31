package com.iota.core.controller

import com.iota.core.dto.model.RoomDto
import com.iota.core.dto.room.RoomDeleted
import com.iota.core.dto.room.RoomGet
import com.iota.core.dto.room.RoomGetSimple
import com.iota.core.dto.room.RoomUpdate
import com.iota.core.service.RoomService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rooms")
class RoomController(
    private val service: RoomService,
) {
    @PostMapping("/")
    fun newRoom(@Valid @RequestBody dto: RoomDto): RoomGetSimple {
        return RoomGetSimple(service.new(dto))
    }

    @GetMapping("/{id}")
    fun room(@PathVariable id: Long): RoomGet {
        return RoomGet(service.room(id))
    }

    @GetMapping("/")
    fun list(): List<RoomGetSimple> {
        return service.findAll().map { RoomGetSimple(it) }
    }

    @PutMapping("/{id}")
    fun updateRoom(@PathVariable id: Long, @Valid @RequestBody dto: RoomUpdate): RoomGetSimple {
        return RoomGetSimple(service.update(id, dto))
    }

    @DeleteMapping("/{id}")
    fun deleteRoom(@PathVariable id: Long): RoomDeleted {
        service.delete(id)

        return RoomDeleted(id)
    }
}