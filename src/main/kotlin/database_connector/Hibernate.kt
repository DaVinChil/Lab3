package database_connector

import entity.TaxiOrder
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import query.TargetQuery

data object Hibernate : DatabaseConnector("Hibernate") {
    private val sessionFactory: SessionFactory by lazy {
        val configuration = Configuration().configure()
        configuration.addAnnotatedClass(TaxiOrder::class.java)
        val builder = StandardServiceRegistryBuilder().applySettings(configuration.properties)
        configuration.buildSessionFactory(builder.build())
    }

    private fun <E> inTransaction(query: Session.() -> E): E {
        var res: E? = null
        sessionFactory.inTransaction {
            res = it.query()
        }
        return res!!
    }

    override fun query(query: TargetQuery) {
        inTransaction {
            createNativeQuery(query.sql).resultList
        }
    }
}