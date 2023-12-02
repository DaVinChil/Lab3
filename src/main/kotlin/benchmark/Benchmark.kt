package benchmark

import database_connector.DatabaseConnector
import query.TargetQuery
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

private fun testConnector(testTimes: Int, queries: List<TargetQuery>, dc: DatabaseConnector): Map<String, Long> {
    return mutableMapOf<String, Long>().apply {
        queries.forEach { query ->
            this[query.name] = avgFor(times = testTimes) { dc.query(query) }
        }
    }
}

fun doBenchmark(
    testTimes: Int,
    queries: List<TargetQuery>,
    connectors: List<DatabaseConnector>
): Map<String, Map<String, Long>> {
    val benchmark = mutableMapOf<String, Map<String, Long>>()
    connectors.forEach { connector ->
        benchmark[connector.library] = testConnector(testTimes, queries, connector)
    }
    return benchmark
}

private fun avgFor(times: Int, func: () -> Unit): Long {
    var avg = 0.0
    repeat(times) { avg += measureTimeMillis(func) / times.toDouble() }
    return avg.toLong()
}
