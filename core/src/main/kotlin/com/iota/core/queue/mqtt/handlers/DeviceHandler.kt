package com.iota.core.queue.mqtt.handlers

import com.iota.core.model.discoverability.DeviceStatusUpdate
import com.iota.core.queue.Broker
import com.iota.core.repository.ConditionNodeRepository
import com.iota.core.repository.DeviceActionRepository
import com.iota.core.repository.DeviceRepository
import com.iota.core.repository.EventNodeRepository
import com.iota.core.repository.OperatorNodeRepository
import com.iota.core.service.NodeVisitor
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttMessage

class DeviceHandler(
    val deviceId: Long,
    val deviceRepository: DeviceRepository,
    val actionRepository: DeviceActionRepository,
    val conditionNodeRepository: ConditionNodeRepository,
    val operatorNodeRepository: OperatorNodeRepository,
    val eventNodeRepository: EventNodeRepository,
    val broker: Broker,
) : IMqttMessageListener {
    override fun messageArrived(topic: String?, message: MqttMessage?) {
        val device = deviceRepository.findById(deviceId).get()
        message?.payload?.let {
            val value: DeviceStatusUpdate
            try {
                value = Json.decodeFromString(String(it))
            } catch (err: SerializationException) {
                println("Could not parse device config $err")
                err.printStackTrace()
                return
            }
            value.forEach { update ->
                device.deviceActions.find { a -> a.idDevice == update.id }?.let { action ->
                    action.status = update.status
                    actionRepository.save(action)

                    val nodes = eventNodeRepository.findByDeviceAction(action)
                    nodes?.let {
                        nodes.forEach { node ->
                            val visitor = NodeVisitor(conditionNodeRepository, operatorNodeRepository, eventNodeRepository, broker)
                            visitor.update(node, update.status)
                        }
                    }
                }
            }
        }
    }
}
