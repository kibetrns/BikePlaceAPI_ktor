package ipsum_amet.me.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ipsum_amet.me.Util.OAUTH_ENDPOINT
import ipsum_amet.me.data.remote.dtos.AuthorisationResponse


class MpesaServiceI(private val client: HttpClient) {
    suspend fun retrieveMpesaAuthToken(): AuthorisationResponse {
           return client.get(OAUTH_ENDPOINT).body()
    }
}