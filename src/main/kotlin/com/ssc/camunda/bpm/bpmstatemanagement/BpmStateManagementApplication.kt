package com.ssc.camunda.bpm.bpmstatemanagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BpmStateManagementApplication

fun main(args: Array<String>) {
	runApplication<BpmStateManagementApplication>(*args)
	println("\nBpmStateManagementApplication  Started\n")
}
