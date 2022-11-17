package ipsum_amet.me.di

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import ipsum_amet.me.Util.DATABASE_NAME
import ipsum_amet.me.service.AirtelMoneyService
import ipsum_amet.me.service.AirtelMoneyServiceImp
import ipsum_amet.me.service.MpesaService
import ipsum_amet.me.service.MpesaServiceImpl
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


val mainModule = module(createdAtStart = true) {
    single { provideDatabase() }
    single(named("mpesaClient")) { provideDefaultMpesaHttpClient() }
    single(named("airtelMoneyClient")) { provideDefaultAirtelMoneyHttpClient() }

    single<MpesaService> {
        MpesaServiceImpl(get(named("mpesaClient")))
    }
    single<AirtelMoneyService> {
        AirtelMoneyServiceImp(get(named("airtelMoneyClient")))
    }
}


@OptIn(ExperimentalSerializationApi::class)
private fun provideDefaultMpesaHttpClient(): HttpClient {
    val consumerKey = System.getenv("CONSUMER_KEY")
    val consumerSecret = System.getenv("CONSUMER_SECRET")


    return HttpClient(CIO) {

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
        install(Auth) {
            basic {
                sendWithoutRequest { true }
                credentials {
                    BasicAuthCredentials(
                        username = consumerKey,
                        password = consumerSecret
                    )
                }
            }
        }
    }
}

private fun provideDatabase() {
    KMongo.createClient()
        .coroutine
        .getDatabase(DATABASE_NAME)
}

@OptIn(ExperimentalSerializationApi::class)
private fun provideBearerHttpClient(
    loadToken: BearerTokens,
    refreshToken: BearerTokens
): HttpClient {
    return HttpClient(CIO) {
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
                loadTokens { loadToken }
                refreshTokens { refreshToken }
            }
        }
    }
}

@OptIn(ExperimentalSerializationApi::class)
fun provideDefaultAirtelMoneyHttpClient(): HttpClient {
    return HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
    }
}






