package com.iota.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@ConfigurationPropertiesScan
@SpringBootApplication
class CoreApplication {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer? {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedOriginPatterns("*")
            }
        }
    }
}

fun main(args: Array<String>) {
    val app = runApplication<CoreApplication>(*args)
}
