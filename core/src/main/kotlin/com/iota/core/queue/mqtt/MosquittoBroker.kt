package com.iota.core.queue.mqtt

import com.iota.core.queue.Broker
import com.iota.core.queue.mqtt.handlers.DeviceHandler
import com.iota.core.queue.mqtt.handlers.DiscoverabilityHandler
import com.iota.core.repository.ConditionNodeRepository
import com.iota.core.repository.DeviceActionRepository
import com.iota.core.repository.DeviceRepository
import com.iota.core.repository.EventNodeRepository
import com.iota.core.repository.OperatorNodeRepository
import com.iota.core.service.DeviceService
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage

class MosquittoBroker(
    private val client: MqttClient,
    private val repository: DeviceRepository,
    private val deviceService: DeviceService,
    private val actionRepository: DeviceActionRepository,
    private val conditionNodeRepository: ConditionNodeRepository,
    private val operatorNodeRepository: OperatorNodeRepository,
    private val eventNodeRepository: EventNodeRepository,
) : Broker {

    private var subscriptions: HashSet<String> = HashSet()
    override fun subscribeDiscoverability() {
        val topic = "discoverability"
        if (subscriptions.contains(topic)) {
            return
        }

        subscriptions.add(topic)
        val handler = DiscoverabilityHandler(deviceService, repository, this)
        client.subscribe(topic, handler)
    }

    override fun subscribeDevice(deviceId: Long, topic: String) {
        if (subscriptions.contains(topic)) {
            return
        }
        subscriptions.add(topic)


        val handler = DeviceHandler(
            deviceId,
            repository,
            actionRepository,
            conditionNodeRepository,
            operatorNodeRepository,
            eventNodeRepository,
            this
        )
        client.subscribe(topic, handler)
    }

    override fun unsubscribe(topic: String) {
    }

    override fun addToTopic(topic: String, value: String) {
        val message = MqttMessage(value.toByteArray())
        client.publish(topic, message)
    }
}
