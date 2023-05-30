package com.iota.core.dto.room

class RoomDeleted(id: Long) {
    var message: String = ""

    init {
        this.message = "Room $id deleted successfully"
    }
}