package com.iota.core.config.broker

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "broker")
class BrokerConfigProperties (
    val provider: String?,
    val hostname: String?,
    val port: String?,
)
