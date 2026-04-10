package org.mycompanyname.timerregister

enum class Commands(val command: String) {
    RECORD("RECORD"),
    PAUSE("PAUSE"),
    END("END");

    companion object {
        fun validateCommand(command: String) {
            var exceptionMessage = ""
                try {
                    valueOf(command.uppercase())
                } catch(ex: Exception) {
                    exceptionMessage += "${ex.message} "
                }

            if(exceptionMessage.isNotEmpty())
                throw Exception(exceptionMessage)
        }
    }
}