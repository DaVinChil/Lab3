package database_connector

import query.TargetQuery
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

data object JDBC : DatabaseConnector("JDBC") {
    private val connection: Connection by lazy {
        DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")
    }

    private fun execQuery(sql: String): ResultSet {
        val statement = connection.createStatement()
        statement.execute(sql)
        return statement.resultSet
    }

    override fun query(query: TargetQuery) {
        execQuery(query.sql)
    }
}