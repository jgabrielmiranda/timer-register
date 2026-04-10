package org.mycompanyname.timerregister.repository

import org.mycompanyname.timerregister.model.entities.Register
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Timestamp
import java.time.LocalDateTime

class RegisterRepository () {
    val url = "jdbc:h2:~/test;DB_CLOSE_DELAY=-1"
    val user = "sa"
    val password = ""

    fun save(register: Register) {
        try {
            DriverManager.getConnection(url, user, password).use { connection ->
                connection.prepareStatement("INSERT INTO REGISTER (TASK_DESCRIPTION, START_DATE) VALUES(?, ?)").use { statement ->
                    statement.setString(1, register.taskDescription)
                    statement.setTimestamp(2, Timestamp.valueOf(register.startDate))
                    val rows = statement.executeUpdate()
                    println("✅ $rows record inserted successfully!")
                }
            }
            getRegister()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun end(taskDescription: String) {
        val register = getRegisterByDescription(taskDescription)
        try {
            DriverManager.getConnection(url, user, password).use { connection ->
                connection.prepareStatement("INSERT INTO UPDATED_DATE VALUES(?, ?, ?)").use { statement ->
                    statement.setInt(1, register?.registerId!!) //todo: what if register was not found (register = null)
                    statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()))
                    statement.setString(3, "Task Ended")
                    val rows = statement.executeUpdate()
                    println("✅ $rows record inserted successfully!")
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        getRegister()
    }

    fun getRegister() {
        try {
            DriverManager.getConnection(url, user, password).use { connection ->
                connection.createStatement().use { statement ->
                    statement.executeQuery("SELECT * FROM REGISTER").use { resultSet ->
                        println("\n--- All Records from REGISTER Table ---")
                        var found = false
                        while (resultSet.next()) {
                            found = true
                            val id = resultSet.getInt("ID")
                            val taskDescription = resultSet.getString("TASK_DESCRIPTION")
                            val startDate = resultSet.getTimestamp("START_DATE")
                            println("ID: $id | Task Description: $taskDescription | Start Date: $startDate")
                        }
                        if (!found) {
                            println("No records found yet. Insert some data first!")
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun getRegisterByDescription(description: String) : Register? {
        var register: Register? = null
        try {
            DriverManager.getConnection(url, user, password).use { connection ->
                connection.prepareStatement("SELECT * FROM REGISTER WHERE TASK_DESCRIPTION = ?").use { statement ->
                    statement.setString(1, description)
                    statement.executeQuery().use { resultSet ->
                        while (resultSet.next()) {
                            register = Register(
                                registerId = resultSet.getInt("ID"),
                                taskDescription = resultSet.getString("TASK_DESCRIPTION"),
                                startDate = LocalDateTime.now() // todo: changeg to get data from database
                            )
                        }
                        if (register == null) {
                            println("No records found yet for description \"${description}.")
                        }
                        return register
                    }
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return register
    }
}