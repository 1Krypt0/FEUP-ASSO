package com.iota.core.queue

interface Broker {
    fun subscribeDiscoverability()

    fun subscribeDevice(deviceId: Long, topic: String)

    fun unsubscribe(topic: String)

    fun addToTopic(topic: String, value: String)
}
