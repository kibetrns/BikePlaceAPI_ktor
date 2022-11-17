package ipsum_amet.me.data.remote.dtos.requests.mpesa


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MpesaExternalSTKPushRequest(
    @SerialName("Amount")
    val amount: Int,
    @SerialName("PhoneNumber")
    val phoneNumber: Long
)