package ipsum_amet.me.data.models

import ipsum_amet.me.data.remote.dtos.responses.BookingsInfoDTO
import kotlinx.datetime.toKotlinLocalDateTime
import me.ipsum_amet.bikeplace.data.model.BikeDropOffAddress
import me.ipsum_amet.bikeplace.data.model.ReturnStatus
import java.time.LocalDateTime


data class BookingsInfo(
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
fun BookingsInfo.toDTO() : BookingsInfoDTO {
    return BookingsInfoDTO(
        bookingId,
        dateBookingMade.toKotlinLocalDateTime(),
        bikeDropOffAddress,
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
