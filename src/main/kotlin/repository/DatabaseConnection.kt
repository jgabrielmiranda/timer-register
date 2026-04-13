package org.mycompanyname.timerregister.repository

import java.sql.DriverManager
import java.sql.Connection

class DatabaseConnection (val url: String = "jdbc:h2:~/test;DB_CLOSE_DELAY=-1",
                          val user: String = "sa",
                          val password: String = "") {

    fun getConnection(): Connection {
        return DriverManager.getConnection(url, user, password)
    }
}