package com.ssc.camunda.bpm.bpmstatemanagement

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BpmController(
    private val service: BpmServices) {

    @GetMapping("/bpmninstance/{processKey}")
    fun findBpmnDefinition(
        @PathVariable processKey: String
    ): String? {

        println("request process key: $processKey")

        return service.loadBpmDefinition(processKey)
    }


    @GetMapping("/nextbpmnstate/{processKey}/{currentState}")
    fun findNextProcessState(
        @PathVariable processKey: String,
        @PathVariable currentState: String
    ): List<String> {

        println("find next state of process: $processKey with current state: $currentState")

        return service.findNextBpmState(processKey, currentState)


    }

}