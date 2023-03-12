package com.iota.core.queue

import com.iota.core.queue.mqtt.handlers.DeviceHandler

interface Broker {
    fun subscribe(topic: String)

    fun unsubscribe(topic: String)

    fun addToTopic(topic: String)
}