package com.iota.core.service

import com.iota.core.model.workflows.Workflow
import com.iota.core.repository.WorkflowsRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class WorkflowsService (
    private val workflowsRepository: WorkflowsRepository
) {
    fun findAll(): List<Workflow> {
        return workflowsRepository.findAll().toList()
    }
}
