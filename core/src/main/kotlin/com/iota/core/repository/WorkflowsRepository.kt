package com.iota.core.repository

import com.iota.core.model.workflows.Workflow
import org.springframework.data.repository.CrudRepository

interface WorkflowsRepository : CrudRepository<Workflow, Long>
