package com.ssc.camunda.bpm.bpmstatemanagement

import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.camunda.bpm.model.bpmn.instance.FlowNode
import org.springframework.stereotype.Service

//ref: https://forum.camunda.org/t/get-preview-next-task/8581/6

@Service
class BpmServices(private val bpmRepositoryService: RepositoryService) {

    companion object {
        const val PROCESS_KEY = "KK_ONBOARDING"
    }

    private fun findProcessInstance(processKey: String): BpmnModelInstance? {

        try {
            val processDefinitionId = bpmRepositoryService.createProcessDefinitionQuery()
                .apply {
                    processDefinitionKey(processKey)
                    processDefinitionVersion(1)
                }.singleResult().id

            return bpmRepositoryService.getBpmnModelInstance(processDefinitionId)

        } catch (e: Exception) {
            println(e.message)
        }
        return null
    }

    fun loadBpmDefinition(processKey: String): String {

        return findProcessInstance(processKey).toString()
    }

    fun findNextBpmState(processKey: String, currentState: String): List<String> {

        val nextStateList = mutableListOf<String>()
        try {
            val processModel = findProcessInstance(processKey)
            val flowNode = processModel?.getModelElementById<FlowNode>(currentState)
            flowNode?.outgoing?.forEach {
                println("flow id: ${it.id}")
                it.target?.let{ node ->
                    nextStateList.add(node.id)
                }
            }
        } catch (e: Exception) {
            println(e.message)
        }
        return nextStateList;
    }

}