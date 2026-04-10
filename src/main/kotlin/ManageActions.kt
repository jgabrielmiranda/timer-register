package org.mycompanyname.timerregister

import org.mycompanyname.timerregister.model.entities.Register
import org.mycompanyname.timerregister.repository.RegisterRepository
import java.time.LocalDateTime

class ManageActions {
    companion object {
        fun controlAction(action: String) {

            val registerRepository = RegisterRepository()
            val command = action.take(action.indexOf('='))
            val taskDescription = action.substring( action.indexOf('=') + 1, action.length)

            Commands.validateCommand(command)
            when(command.uppercase()) {
                Commands.RECORD.name -> registerRepository.save(Register(
                    taskDescription = taskDescription,
                    startDate = LocalDateTime.now(),
                ))
                Commands.PAUSE.name -> throw NotImplementedError()
                Commands.END.name -> registerRepository.end(taskDescription)
            }
        }
    }
}