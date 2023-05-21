package com.iota.core.queue.mqtt

import com.iota.core.queue.Broker
import com.iota.core.queue.mqtt.handlers.DeviceHandler
import com.iota.core.queue.mqtt.handlers.DiscoverabilityHandler
import com.iota.core.repository.DeviceRepository
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage

class MosquittoBroker(
    private val client: MqttClient,
    private val repository: DeviceRepository
) : Broker {

    private var subscriptions: HashSet<String> = HashSet()
    override fun subscribeDiscoverability() {
        val topic = "discoverability"
        if (subscriptions.contains(topic)) {
            return
        }

        subscriptions.add(topic)
        val handler = DiscoverabilityHandler(repository, this)
        client.subscribe(topic, handler)
    }

    override fun subscribeDevice(deviceId: Long, topic: String) {
        if (subscriptions.contains(topic)) {
            return
        }
        subscriptions.add(topic)

        val handler = DeviceHandler(deviceId, repository)
        client.subscribe(topic, handler)
    }

    override fun unsubscribe(topic: String) {
    }

    override fun addToTopic(topic: String, value: String) {
        val message = MqttMessage(value.toByteArray())
        client.publish(topic, message)
    }
}
