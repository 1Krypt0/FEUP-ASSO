package com.iota.core.service

import com.iota.core.dto.model.RoomDto
import com.iota.core.dto.room.RoomUpdate
import com.iota.core.exception.room.RoomNotFoundException
import com.iota.core.model.Room
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

    fun findAll(): MutableIterable<Room> {
        return roomRepository.findAll()
    }

    fun update(id: Long, dto: RoomUpdate): Room {
        val room: Room = room(id)

        if (dto.name != null) {
            room.name = dto.name!!
        }

        try {
            roomRepository.save(room)
        } catch (ex: DataIntegrityViolationException) {
            throw ex
        }

        return room
    }
}
