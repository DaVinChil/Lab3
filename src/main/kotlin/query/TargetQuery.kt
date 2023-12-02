package query
enum class TargetQuery(val sql: String) {
    CAB_TYPES("SELECT VendorID, count(*) FROM taxi GROUP BY 1"),
    AVG_BY_COUNT("""
                SELECT passenger_count, avg(total_amount) 
                FROM taxi 
                GROUP BY 1
            """.trimIndent()),
    EXTRACT_BY_DATETIME("""
                SELECT 
                    passenger_count,
                    extract(year from tpep_pickup_datetime),
                    count(*)
                FROM taxi
                GROUP BY 1, 2;
            """.trimIndent()),
    TRIP_DISTANCE("""
                SELECT
                    passenger_count,
                    extract(year from tpep_pickup_datetime),
                    round(trip_distance),
                    count(*) 
                FROM taxi
                GROUP BY 1, 2, 3
                ORDER BY 2, 4 desc;
            """.trimIndent());
}