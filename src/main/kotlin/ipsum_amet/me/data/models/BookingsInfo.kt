package ipsum_amet.me.data.models

import ipsum_amet.me.data.remote.dtos.responses.BookingsInfoDTO
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime


data class BookingsInfo(
    val bookingId: String,
    val dateBookingMade: LocalDateTime,
    var bikeDropOffLocation: String?,
    val bikeLeaseActivation: LocalDateTime,
    val bikeLeaseExpiry: LocalDateTime,
    val userName:String,
    val userId: String,
    val bikeId: String,
    val bikeName:String,
    val amount: Double,
    val bikeReturnStatus: String,
    val userPhoneNumber: Long
)
fun BookingsInfo.toDTO() : BookingsInfoDTO {
    return BookingsInfoDTO(
        bookingId,
        dateBookingMade.toKotlinLocalDateTime(),
        bikeDropOffLocation,
        bikeLeaseActivation.toKotlinLocalDateTime(),
        bikeLeaseExpiry.toKotlinLocalDateTime(),
        userName,
        userId,
        bikeId,
        bikeName,
        amount,
        bikeReturnStatus,
        userPhoneNumber,
    )
}
