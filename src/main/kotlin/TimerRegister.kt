package org.mycompanyname.timerregister

fun main(args: Array<String>) {
    try {
        ManageActions.controlAction(args.first())
    } catch(ex: Exception) {
        println("At least one of the given commands was invalid. ${ex.message}")
    }
}