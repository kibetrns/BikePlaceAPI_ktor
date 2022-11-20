package ipsum_amet.me.data.remote.dtos.requests.mpesa


import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MpesaExternalSTKPushRequest(
    @SerialName("Amount")
    val amount: Int,
    @SerialName("UserPhoneNumber")
    val userPhoneNumber: Long,
    @SerialName("BikeDropOffLocation")
    val bikeDropOffLocation: String?,
    @SerialName("BikeLeaseActivation")
    val bikeLeaseActivation: String,
    @SerialName("BikeLeaseExpiry")
    val bikeLeaseExpiry: String,
    @SerialName("UserName")
    val userName: String,
    @SerialName("BikeId")
    val bikeId: String,
    @SerialName("BikeName")
    val bikeName: String,
    @SerialName("BikeReturnStatus")
    val bikeReturnStatus: String
)