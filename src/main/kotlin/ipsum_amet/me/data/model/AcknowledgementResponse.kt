package ipsum_amet.me.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AcknowledgementResponse(
    @SerialName("message")
    val message: String
)