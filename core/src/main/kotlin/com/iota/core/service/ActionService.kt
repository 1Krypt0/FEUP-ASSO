package com.iota.core.service

import com.iota.core.dto.model.ActionDto
import com.iota.core.exception.device.ActionNotFoundException
import com.iota.core.model.Action
import com.iota.core.repository.ActionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class ActionService {
    @Autowired
    private lateinit var actionRepository: ActionRepository

    fun new(dto: ActionDto): Action {
        val action: Action = dto.create()

        try {
            actionRepository.save(action)
        } catch (ex: DataIntegrityViolationException) {
            throw ex
        }

        return action
    }

    fun action(id: Long): Action {
        try {
            val action: Optional<Action> = actionRepository.findById(id)

            return action.get()
        } catch (ex: NoSuchElementException) {
            throw ActionNotFoundException(ex.message, ex.cause, id)
        }
    }
}
