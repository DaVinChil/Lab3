package database_connector

import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence
import query.TargetQuery


data object Jpa : DatabaseConnector("Jpa") {
    private val entityManager: EntityManager by lazy {
        Persistence.createEntityManagerFactory("ru.ns.taxi").createEntityManager()
    }

    override fun query(query: TargetQuery) {
        entityManager.createNativeQuery(query.sql).resultList
    }
}