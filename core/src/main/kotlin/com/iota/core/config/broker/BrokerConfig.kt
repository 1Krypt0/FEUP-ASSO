package com.iota.core.config.broker

import com.iota.core.queue.Broker
import com.iota.core.queue.mqtt.MosquittoBroker
import com.iota.core.repository.DeviceRepository
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.springframework.context.annotation.Configuration

@Configuration
class BrokerConfig(
    private val properties: BrokerConfigProperties,
    private val repository: DeviceRepository,
) {
    private val memoryPersistence = MemoryPersistence()
    private var _broker: Broker? = null
    private fun mosquitto(): Broker {
        val client = MqttClient(properties.hostname, properties.port, memoryPersistence)

        val opts = MqttConnectOptions()
        opts.isCleanSession = true
        client.connect(opts)

        return MosquittoBroker(client, repository)
    }

    fun broker(): Broker {
        if (_broker == null) {
            _broker = when (properties.provider) {
                "mosquitto" -> mosquitto()
                else -> throw Error("invalid broker")
            }
        }

        _broker!!.subscribeDiscoverability()

        return _broker as Broker
    }
}
