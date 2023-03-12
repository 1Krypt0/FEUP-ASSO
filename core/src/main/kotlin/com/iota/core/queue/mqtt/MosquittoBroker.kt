package com.iota.core.queue.mqtt

import com.iota.core.queue.Broker
import com.iota.core.queue.mqtt.handlers.DeviceHandler
import com.iota.core.repository.DeviceRepository
import com.iota.core.service.DeviceService
import org.eclipse.paho.client.mqttv3.MqttClient

class MosquittoBroker(
    private val client: MqttClient,
    private val repository: DeviceRepository
): Broker {

    private var subscriptions: HashSet<String> = HashSet()

    override fun subscribe(topic: String) {
        if (subscriptions.contains(topic)) {
            return
        }
        subscriptions.add(topic)

        val handler = DeviceHandler(repository)
        client.subscribe(topic, handler)
    }

    override fun unsubscribe(topic: String) {
    }

    override fun addToTopic(topic: String) {
    }
}
