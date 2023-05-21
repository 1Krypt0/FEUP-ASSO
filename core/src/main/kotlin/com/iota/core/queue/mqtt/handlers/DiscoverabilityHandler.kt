package com.iota.core.queue.mqtt.handlers

import com.iota.core.dto.model.DeviceDto
import com.iota.core.model.discoverability.DiscoverableDevice
import com.iota.core.queue.Broker
import com.iota.core.repository.DeviceRepository
import com.iota.core.service.DeviceService
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttMessage

class DiscoverabilityHandler (
    private val deviceService: DeviceService,
    private val deviceRepository: DeviceRepository,
    private val broker: Broker
    ) : IMqttMessageListener {
    override fun messageArrived(topic: String?, message: MqttMessage?) {
        message?.payload?.let {
            val value: DiscoverableDevice;
            try {
                value = Json.decodeFromString(String(it))
            }
            catch (err: SerializationException) {
                println("Could not parse device config $err")
                return
            }
            val device = deviceRepository.findDeviceByMacAddress(value.mac)

            broker.addToTopic("discoverability-${value.mac}", "ok")

            if (device.isPresent) {
                return
            }

            val dto = DeviceDto(value)
            val newDevice = deviceService.new(dto)

            newDevice.id.let {id -> broker.subscribeDevice(id, newDevice.dataTopic) }
            deviceRepository.save(newDevice)

            println("Added new device ${newDevice.name}")
        }
    }
}
