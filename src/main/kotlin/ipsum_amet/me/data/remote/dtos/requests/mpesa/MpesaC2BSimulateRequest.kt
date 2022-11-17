package ipsum_amet.me.data.remote.dtos.requests.mpesa


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MpesaC2BSimulateRequest(
    @SerialName("Amount")
    val amount: Int,
    @SerialName("BillRefNumber")
    val billRefNumber: String,
    @SerialName("CommandID")
    val commandID: String,
    @SerialName("Msisdn")
    val msisdn: Long,
    @SerialName("ShortCode")
    val shortCode: Int
)