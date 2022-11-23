package ipsum_amet.me.data.remote.dtos.requests.mpesa

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookingsInfoRequest(
    @SerialName("UserId")
    var userId: String = "",
    @SerialName("MpesaReceiptNumber")
    var mpesaReceiptNumber: String = ""
)
