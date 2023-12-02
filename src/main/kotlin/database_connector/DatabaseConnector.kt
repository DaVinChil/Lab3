package database_connector

import query.TargetQuery

sealed class DatabaseConnector(val library: String) {
    abstract fun query(query: TargetQuery)
}