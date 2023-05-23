package com.iota.core.queue

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.*

class Message(
    val address: String,
    val content: Map<String, Any>,
    val timestamp: Date,
) {
    fun serialize(): String =
        jacksonObjectMapper().writeValueAsString(
            mapOf(
                "address" to address,
                "content" to content,
                "timestamp" to timestamp
            )
        )


    companion object {
        fun deserialize(json: String): Message =
            jacksonObjectMapper().readValue(json, Message::class.java)
    }
}
