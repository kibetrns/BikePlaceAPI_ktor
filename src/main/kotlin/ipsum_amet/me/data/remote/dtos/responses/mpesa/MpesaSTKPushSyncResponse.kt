package ipsum_amet.me.data.remote.dtos.responses.mpesa


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MpesaSTKPushSyncResponse(
    @SerialName("CheckoutRequestID")
    val checkoutRequestID: String,
    @SerialName("CustomerMessage")
    val customerMessage: String,
    @SerialName("MerchantRequestID")
    val merchantRequestID: String,
    @SerialName("ResponseCode")
    val responseCode: String,
    @SerialName("ResponseDescription")
    val responseDescription: String
)