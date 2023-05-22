package com.iota.core.service

import com.iota.core.dto.model.ActionDto
import com.iota.core.dto.model.RoomDto
import com.iota.core.exception.device.ActionNotFoundException
import com.iota.core.exception.room.RoomNotFoundException
import com.iota.core.model.Action
import com.iota.core.model.Room
import com.iota.core.repository.ActionRepository
import com.iota.core.repository.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoomService {
    @Autowired
    private lateinit var roomRepository: RoomRepository

    fun new(dto: RoomDto): Room {
        val room: Room = dto.create()

        try {
            roomRepository.save(room)
        } catch (ex: DataIntegrityViolationException) {
            throw ex
        }

        return room
    }

    fun room(id: Long): Room {
        try {
            val room: Optional<Room> = roomRepository.findById(id)

            return room.get()
        } catch (ex: NoSuchElementException) {
            throw RoomNotFoundException(ex.message, ex.cause, id)
        }
    }
}
