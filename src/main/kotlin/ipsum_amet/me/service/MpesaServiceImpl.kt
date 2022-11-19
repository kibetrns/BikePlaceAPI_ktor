package ipsum_amet.me.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import ipsum_amet.me.Util.MpesaEndpoints
import ipsum_amet.me.data.remote.dtos.requests.mpesa.MpesaRegisterUrlRequest
import ipsum_amet.me.data.remote.dtos.requests.mpesa.MpesaSTKPushRequest
import ipsum_amet.me.data.remote.dtos.responses.*
import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaAuthorisationResponse
import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaC2BRegisterURLResponse
import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaSTKPushAsyncResponse
import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaSTKPushSyncResponse
import ipsum_amet.me.data.repository.MpesaPaymentDataSource
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

class MpesaServiceImpl(
    private val client: HttpClient,
    private val repository: MpesaPaymentDataSource
    ) : MpesaService {
    private val bearerTokenStorage = mutableListOf<BearerTokens>()
    private val scope = CoroutineScope(Dispatchers.IO)

    private lateinit var tokenInfo: MpesaAuthorisationResponse


    val log = LoggerFactory.getLogger(MpesaServiceImpl::class.java)

    @OptIn(ExperimentalSerializationApi::class)
    private val client1 = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
        install(Auth) {
            bearer {
                loadTokens { bearerTokenStorage.first() }
                refreshTokens { bearerTokenStorage.last() }
            }
        }

    }


    private suspend fun loadTokenInfo() = scope.async {
        tokenInfo = retrieveMpesaAuthToken()
        bearerTokenStorage.add(BearerTokens(accessToken = tokenInfo.accessToken, refreshToken = tokenInfo.accessToken))
    }


    override suspend fun retrieveMpesaAuthToken(): MpesaAuthorisationResponse {
        return client.get(MpesaEndpoints.Authorisation.url).body()
    }

    override suspend fun retrieveC2BURL(registerUrlRequest: MpesaRegisterUrlRequest): MpesaC2BRegisterURLResponse {
        loadTokenInfo().await()
        log.debug(registerUrlRequest.toString())
        log.debug("********************************")
        //log.debug(y.toString())
        log.debug(tokenInfo.toString())
        log.debug(bearerTokenStorage.first().accessToken)
        log.debug(bearerTokenStorage.last().accessToken)
        return client1.post(MpesaEndpoints.C2BRegisterURL.url) {
            contentType(ContentType.Application.Json)
            setBody(registerUrlRequest)
        }.body()
    }

    override suspend fun initiateMpesaExpress(sTKPushRequest: MpesaSTKPushRequest): MpesaSTKPushSyncResponse {
        loadTokenInfo().await()
        return client1.post(MpesaEndpoints.MpesaExpress.url) {
            contentType(ContentType.Application.Json)
            setBody(sTKPushRequest)
        }.body()
    }

    override suspend fun insertAsyncResponse(mpesaSTKPushAsyncResponse: MpesaSTKPushAsyncResponse): Boolean {
        return repository.insertMpesaPaymentInfo(mpesaSTKPushAsyncResponse)

    }

//"https://en9u0gqzwo9t5.x.pipedream.net"
//

    override suspend fun getPosts(): List<Post> {
        return client.get("https://jsonplaceholder.typicode.com/posts").body()
    }
}