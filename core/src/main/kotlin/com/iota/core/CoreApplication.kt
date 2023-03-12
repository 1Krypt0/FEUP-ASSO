package com.iota.core

import com.iota.core.config.broker.BrokerConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class CoreApplication

fun main(args: Array<String>) {
	val app = runApplication<CoreApplication>(*args)

	println(app.getBean(BrokerConfig::class.java).broker())
	println(app.getBean(BrokerConfig::class.java).broker())
	println(app.getBean(BrokerConfig::class.java).broker())
}
