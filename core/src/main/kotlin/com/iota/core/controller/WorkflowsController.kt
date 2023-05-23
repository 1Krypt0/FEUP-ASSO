package com.iota.core.controller

import com.iota.core.model.workflows.Workflow
import com.iota.core.service.WorkflowsService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/workflows")
class WorkflowsController (
    private val workflowsService: WorkflowsService
) {

    @GetMapping("")
    fun get() : List<Workflow> {
        val workflows = workflowsService.findAll()

        return workflows
    }
}
