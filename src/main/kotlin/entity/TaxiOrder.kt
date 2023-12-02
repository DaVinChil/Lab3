package entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
open class TaxiOrder protected constructor() {
    @Id
    open val VendorID: Int? = null
    open val tpep_pickup_datetime: Date? = null
    open val tpep_dropoff_datetime: Date? = null
    open val passenger_count: Double? = null
    open val trip_distance: Double? = null
    open val RatecodeID: Double? = null
    open val store_and_fwd_flag: String? = null
    open val PULocationID: Int? = null
    open val DOLocationID: Int? = null
    open val payment_type: Int? = null
    open val fare_amount: Int? = null
    open val extra: Double? = null
    open val mta_tax: Double? = null
    open val tip_amount: Double? = null
    open val tolls_amount: Double? = null
    open val improvement_surcharge: Double? = null
    open val total_amount: Double? = null
    open val congestion_surcharge: Double? = null
    open val airport_fee: Double? = null
}