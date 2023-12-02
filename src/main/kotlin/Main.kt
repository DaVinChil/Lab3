import benchmark.doBenchmark
import database_connector.*
import query.TargetQuery
import kotlin.math.max

val allDC = listOf(Hibernate, JDBC, MyBatis, Jpa, JDBI)

fun main() {
    val benchmark = doBenchmark(10, TargetQuery.entries, allDC)
    showBench(benchmark)
}

fun showBench(res: Map<String, Map<String, Long>>) {
    val maxLen = TargetQuery.entries.maxOf { it.name.length }
    print(" ".repeat(maxLen))
    for (db in res) {
        print("%5s".format(db.key))
        print(" ")
    }
    println()
    for(query in TargetQuery.entries) {
        print("%-${maxLen}s".format(query.name))
        for(db in res) {
            val sz = max(db.key.length, 5)
            print("%${sz}d ".format(db.value[query.name]))
        }
        println()
    }
    print("                  (time in milliseconds)")
}
