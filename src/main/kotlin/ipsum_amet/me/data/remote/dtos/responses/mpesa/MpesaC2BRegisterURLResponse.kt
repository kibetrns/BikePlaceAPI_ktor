package ipsum_amet.me.data.remote.dtos.responses.mpesa


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MpesaC2BRegisterURLResponse(
    @SerialName("OriginatorCoversationID")
    val originatorCoversationID: String,
    @SerialName("ResponseCode")
    val responseCode: String,
    @SerialName("ResponseDescription")
    val responseDescription: String
)