package ipsum_amet.me.data.remote.dtos.responses.mpesa


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MpesaAuthorisationResponse(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("expires_in")
    val expiresIn: String
)