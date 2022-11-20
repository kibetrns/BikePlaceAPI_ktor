package ipsum_amet.me.data.models

import java.time.LocalDateTime


data class BookingsInfo(
    val bookingId: String,
    val dateBookingMade: LocalDateTime,
    var bikeDropOffLocation: String?,
    val bikeLeaseActivation: LocalDateTime,
    val bikeLeaseExpiry: LocalDateTime,
    val userName:String,
    val bikeId: String,
    val bikeName:String,
    val amount: Double,
    val bikeReturnStatus: String,
    val userPhoneNumber: Long
)
