package com.iota.core.queue.mqtt.handlers

import com.iota.core.repository.DeviceRepository
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttMessage

class DeviceHandler(
    val deviceRepository: DeviceRepository,
) : IMqttMessageListener {
    override fun messageArrived(topic: String?, message: MqttMessage?) {
        println(topic)
        println(message?.payload?.let { String(it) })
    }
}
