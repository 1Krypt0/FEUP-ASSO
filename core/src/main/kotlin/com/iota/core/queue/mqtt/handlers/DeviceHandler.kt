package com.iota.core.queue.mqtt.handlers

import com.iota.core.repository.DeviceRepository
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttMessage

class DeviceHandler(
    val deviceId: Long,
    val deviceRepository: DeviceRepository,
) : IMqttMessageListener {
    override fun messageArrived(topic: String?, message: MqttMessage?) {
        val device = deviceRepository.findById(deviceId).get()

        message?.payload?.let {
            val value = String(it)
            device.currentValue = value
            deviceRepository.save(device)
        }
    }
}
