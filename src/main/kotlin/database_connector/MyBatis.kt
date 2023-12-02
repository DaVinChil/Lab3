package database_connector

import entity.mapper.TaxiOrderMapper
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import query.TargetQuery


data object MyBatis : DatabaseConnector("MyBatis") {
    private val sqlSessionFactory: SqlSessionFactory by lazy {
        SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"))
    }

    private fun <E, M> inSession(mapperClass: Class<M>, query: M.() -> E) =
        sqlSessionFactory.openSession().use {
            val mapper = it.getMapper(mapperClass)
            mapper.query()
        }

    override fun query(query: TargetQuery) {
        val queries: Map<TargetQuery, TaxiOrderMapper.() -> List<Any>> = mapOf(
            TargetQuery.AVG_BY_COUNT        to TaxiOrderMapper::avgByCount ,
            TargetQuery.CAB_TYPES           to TaxiOrderMapper::countCabType,
            TargetQuery.EXTRACT_BY_DATETIME to TaxiOrderMapper::extractByDateTime,
            TargetQuery.TRIP_DISTANCE       to TaxiOrderMapper::tripDistance
        )
        queries[query]?.let { inSession(TaxiOrderMapper::class.java, it) }
    }
}