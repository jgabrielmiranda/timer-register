package org.mycompanyname.timerregister.model.entities

import java.time.LocalDateTime

data class UpdatedDate (
    val registerId: Int,
    val updatedDate: LocalDateTime,
    val reason: String,
)