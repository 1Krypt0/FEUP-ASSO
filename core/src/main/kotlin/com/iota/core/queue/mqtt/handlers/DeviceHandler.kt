package com.iota.core.queue.mqtt.handlers

import com.iota.core.model.discoverability.DeviceStatusUpdate
import com.iota.core.repository.DeviceActionRepository
import com.iota.core.repository.DeviceRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttMessage

class DeviceHandler(
    val deviceId: Long,
    val deviceRepository: DeviceRepository,
    val actionRepository: DeviceActionRepository
) : IMqttMessageListener {
    override fun messageArrived(topic: String?, message: MqttMessage?) {
        println(topic)
        val device = deviceRepository.findById(deviceId).get()
        message?.payload?.let {
            val value = Json.decodeFromString<DeviceStatusUpdate>(String(it))

            value.forEach {update ->
                device.deviceActions.find { a -> a.idDevice == update.id }?.let { action ->
                    action.status = update.status
                    actionRepository.save(action)
                }
            }
        }
    }
}
