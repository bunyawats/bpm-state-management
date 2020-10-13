package com.ssc.camunda.bpm.bpmstatemanagement

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class BpmHandler(private val service: BpmServices){

    fun getDefinition(request: ServerRequest): Mono<ServerResponse> {

        val helloBpm = service.loadBpmDefinition()
        println("Message from service: $helloBpm")

        val id = request.pathVariable("id")
        println("part param id : $id")
        return ServerResponse.ok().bodyValue(arrayOf(1, 2, 3))
    }

}