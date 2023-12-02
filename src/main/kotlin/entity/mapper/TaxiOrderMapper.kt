package entity.mapper

import entity.projection.AvgByCount
import entity.projection.CountCabType
import entity.projection.ExtractByDateTime
import entity.projection.TripDistance
import org.apache.ibatis.annotations.Select

interface TaxiOrderMapper {

    @Select("SELECT VendorID, count(*) as count FROM taxi GROUP BY 1")
    fun countCabType(): List<CountCabType>

    @Select("""
        SELECT passenger_count as passengerCount, avg(total_amount) as average
        FROM taxi 
        GROUP BY 1
    """)
    fun avgByCount(): List<AvgByCount>

    @Select("""
        SELECT 
            passenger_count as passengerCount,
            extract(year from tpep_pickup_datetime) as year,
            count(*) as count
        FROM taxi
        GROUP BY 1, 2
    """)
    fun extractByDateTime(): List<ExtractByDateTime>

    @Select("""
        SELECT
            passenger_count as passengerCount,
            extract(year from tpep_pickup_datetime) as year,
            round(trip_distance) as tripDistance,
            count(*) as count
        FROM taxi
        GROUP BY 1, 2, 3
        ORDER BY 2, 4 desc
    """)
    fun tripDistance(): List<TripDistance>
}