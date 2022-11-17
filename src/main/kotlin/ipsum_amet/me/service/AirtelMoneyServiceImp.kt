package ipsum_amet.me.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import ipsum_amet.me.Util.AirtelMoneyEndpoints
import ipsum_amet.me.data.remote.dtos.requests.airtelMoney.AirtelMoneyAuthRequest
import ipsum_amet.me.data.remote.dtos.responses.airtelMoney.AirtelMAuthResponse
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

class AirtelMoneyServiceImp(private val client: HttpClient) : AirtelMoneyService {
    val log = LoggerFactory.getLogger(MpesaServiceImpl::class.java)

    override suspend fun retrieveAirtelMoneyAuthToken(authRequest: AirtelMoneyAuthRequest): AirtelMAuthResponse {
        log.debug(authRequest.toString())

        return client.post(AirtelMoneyEndpoints.Authorisation.url) {
           headers {
               append("Accept", "*/*")
            }
            contentType(ContentType.Application.Json)
            setBody(authRequest)
        }.body()
    }
}