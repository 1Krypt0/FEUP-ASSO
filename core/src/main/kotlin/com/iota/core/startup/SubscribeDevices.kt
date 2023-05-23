package com.iota.core.startup

import com.iota.core.config.broker.BrokerConfig
import com.iota.core.repository.ActionRepository
import com.iota.core.repository.DeviceRepository
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class SubscribeDevices (
    val deviceRepository: DeviceRepository,
    brokerConfig: BrokerConfig
) {
    private val broker = brokerConfig.broker()

    @EventListener
    fun setup(event: ContextRefreshedEvent) {
        deviceRepository.findAll().forEach {
            broker.subscribeDevice(it.id, it.dataTopic)
        }
    }
}