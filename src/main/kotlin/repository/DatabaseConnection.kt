package org.mycompanyname.timerregister.repository

import java.sql.DriverManager
import java.sql.Connection
import java.util.Properties

object DatabaseConnection {
    private val properties = Properties()

    init {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("config.properties")

        if(inputStream != null) {
            properties.load(inputStream)
        } else {
            throw RuntimeException("File config.properties not found!")
        }
    }

    fun getConnection(): Connection {
        return DriverManager.getConnection(
            properties.getProperty("db.url"),
            properties.getProperty("db.user"),
            properties.getProperty("dbpassword"),
        )
    }
}