package ipsum_amet.me.data.remote.dtos.requests.mpesa


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MpesaRegisterUrlRequest(
    @SerialName("ConfirmationURL")
    val confirmationURL: String,
    @SerialName("ResponseType")
    val responseType: String,
    @SerialName("ShortCode")
    val shortCode: String,
    @SerialName("ValidationURL")
    val validationURL: String
)