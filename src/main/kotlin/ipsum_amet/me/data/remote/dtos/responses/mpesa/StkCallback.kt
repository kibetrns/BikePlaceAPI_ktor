package ipsum_amet.me.data.remote.dtos.responses.mpesa


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StkCallback(
    @SerialName("CallbackMetadata")
    val callbackMetadata: CallbackMetadata,
    @SerialName("CheckoutRequestID")
    val checkoutRequestID: String,
    @SerialName("MerchantRequestID")
    val merchantRequestID: String,
    @SerialName("ResultCode")
    val resultCode: Int,
    @SerialName("ResultDesc")
    val resultDesc: String
)