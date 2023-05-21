package com.iota.core.queue.mqtt.handlers

import com.iota.core.model.Device
import com.iota.core.model.DeviceType
import com.iota.core.model.NetworkStatus
import com.iota.core.model.discoverability.DiscoverableDevice
import com.iota.core.queue.Broker
import com.iota.core.repository.DeviceRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttMessage

class DiscoverabilityHandler (
    val deviceRepository: DeviceRepository,
    val broker: Broker
    ) : IMqttMessageListener {
    override fun messageArrived(topic: String?, message: MqttMessage?) {
        message?.payload?.let {
            val value = Json.decodeFromString<DiscoverableDevice>(String(it))

            val device = deviceRepository.findDeviceByMacAddress(value.mac)

            broker.addToTopic("discoverability-${value.mac}", "ok")

            if (device.isPresent) {
                return
            }

//            val entity = Device()
//            entity.name = value.name
//            entity.macAddress = value.mac
//            entity.type = DeviceType.LIGHT // TODO Change this
//            entity.dataTopic = "DATA-" + value.mac
//            entity.actionTopic = "ACTION-" + value.mac
//            entity.status = NetworkStatus.CONNECTED
//
//            deviceRepository.save(entity)
        }
    }
}
