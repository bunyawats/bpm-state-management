package com.ssc.camunda.bpm.bpmstatemanagement

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class BpmRoutes(private val handler: BpmHandler) {
    @Bean
    fun route() = router {

        GET("/route") { _ -> ServerResponse.ok().bodyValue(arrayOf(1, 2, 3)) }

        GET( "/bpmn/{id}",  handler::getDefinition )
    }
}
