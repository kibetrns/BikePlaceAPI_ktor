package ipsum_amet.me.data.remote.dtos.responses.mpesa


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class STKPushAsyncResponse(
    @SerialName("Body")
    val body: Body
)