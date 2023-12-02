package database_connector

import org.jdbi.v3.core.Jdbi
import query.TargetQuery

data object JDBI : DatabaseConnector("JDBI") {
    private val jdbi: Jdbi by lazy {
        Jdbi.create("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")
    }

    private fun execQuery(query: String): List<Map<String, Any>> {
        return jdbi.open().use {
            it.createQuery(query).mapToMap().list()
        }
    }

    override fun query(query: TargetQuery) {
        execQuery(query.sql)
    }
}