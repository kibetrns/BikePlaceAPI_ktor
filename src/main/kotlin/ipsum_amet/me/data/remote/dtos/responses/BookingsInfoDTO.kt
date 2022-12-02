package ipsum_amet.me.data.remote.dtos.responses

import ipsum_amet.me.data.models.BookingsInfo
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.serialization.Serializable
import me.ipsum_amet.bikeplace.data.model.BikeDropOffAddress
import me.ipsum_amet.bikeplace.data.model.ReturnStatus

@Serializable
data class BookingsInfoDTO(
    val bookingId: String,
    val dateBookingMade: LocalDateTime,
    var bikeDropOffAddress: BikeDropOffAddress?,
    val bikeLeaseActivation: LocalDateTime,
    val bikeLeaseExpiry: LocalDateTime,
    val userName:String,
    val userId: String,
    val bikeId: String,
    val bikeName:String,
    val amount: Double,
    val bikeReturnStatus: ReturnStatus,
    val userPhoneNumber: Long
)
fun BookingsInfoDTO.toBookingsInfo(): BookingsInfo {
    return BookingsInfo(
        bookingId,
        dateBookingMade.toJavaLocalDateTime(),
        bikeDropOffAddress,
        bikeLeaseActivation.toJavaLocalDateTime(),
        bikeLeaseExpiry.toJavaLocalDateTime(),
        userName,
        userId,
        bikeId,
        bikeName,
        amount,
        bikeReturnStatus,
        userPhoneNumber
    )

}