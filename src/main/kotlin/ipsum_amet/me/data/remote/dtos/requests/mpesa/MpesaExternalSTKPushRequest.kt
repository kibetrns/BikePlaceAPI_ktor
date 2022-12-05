package ipsum_amet.me.data.remote.dtos.requests.mpesa


import ipsum_amet.me.data.models.TYPE
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.ipsum_amet.bikeplace.data.model.BikeDropOffAddress
import me.ipsum_amet.bikeplace.data.model.ReturnStatus

@Serializable
data class MpesaExternalSTKPushRequest(
    @SerialName("Amount")
    val amount: Int,
    @SerialName("UserPhoneNumber")
    val userPhoneNumber: Long,
    @SerialName("BikeDropOffLocation")
    val bikeDropOffLocation: BikeDropOffAddress?,
    @SerialName("BikeLeaseActivation")
    val bikeLeaseActivation: LocalDateTime,
    @SerialName("BikeLeaseExpiry")
    val bikeLeaseExpiry: LocalDateTime,
    @SerialName("UserName")
    val userName: String,
    @SerialName("UserId")
    val userId: String,
    @SerialName("BikeId")
    val bikeId: String,
    @SerialName("BikeName")
    val bikeName: String,
    @SerialName("BikeType")
    val bikeType: TYPE,
    @SerialName("BikeReturnStatus")
    val bikeReturnStatus: ReturnStatus
)