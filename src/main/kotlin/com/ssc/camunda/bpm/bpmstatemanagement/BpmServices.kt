package com.ssc.camunda.bpm.bpmstatemanagement

import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.engine.repository.ProcessDefinition
import org.camunda.bpm.model.bpmn.instance.FlowNode
import org.springframework.stereotype.Service

//ref: https://forum.camunda.org/t/get-preview-next-task/8581/6

@Service
class BpmServices(
    private val bpmRepositoryService: RepositoryService
) {

    private fun findProcessDefinition(
        processKey: String
    ): ProcessDefinition? {
        try {
            return bpmRepositoryService.createProcessDefinitionQuery()
                .apply {
                    processDefinitionKey(processKey)
                    processDefinitionVersion(1)
                }.singleResult()
        } catch (e: Exception) {
            println(e.message)
        }
        return null
    }

    fun loadBpmDefinition(
        processKey: String
    ): String? {

        val definitions = findProcessDefinition(processKey)
        println(" process definition id : ${definitions?.name} ")

        return definitions?.name
    }

    fun findNextBpmState(
        processKey: String,
        currentState: String
    ): List<String> {

        val nextStateList = mutableListOf<String>()

        try {
            val definitions = findProcessDefinition(processKey)
            val modelInstance = bpmRepositoryService.getBpmnModelInstance(definitions?.id)
            val flowNode = modelInstance?.getModelElementById<FlowNode>(currentState)

            flowNode?.outgoing?.forEach {
                println("flow id: ${it.id}")
                it.target?.let { node ->
                    nextStateList.add(node.id)
                }
            }
        } catch (e: Exception) {
            println(e.message)
        }
        return nextStateList;
    }

}