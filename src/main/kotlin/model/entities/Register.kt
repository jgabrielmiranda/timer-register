package org.mycompanyname.timerregister.model.entities

import java.time.LocalDateTime

data class Register (
    val registerId: Int? = null,
    val taskDescription: String,
    val startDate: LocalDateTime,
)