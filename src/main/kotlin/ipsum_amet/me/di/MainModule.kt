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
import ipsum_amet.me.Util.DB_NAME
import ipsum_amet.me.data.repository.MpesaPaymentDataSource
import ipsum_amet.me.data.repository.MpesaPaymentDataSourceImpl
import ipsum_amet.me.service.MpesaService
import ipsum_amet.me.service.MpesaServiceImpl
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


val mainModule = module(createdAtStart = true) {
    single { provideDatabase() }

    single(named("mpesaClient")) { provideDefaultMpesaHttpClient() }

    single<MpesaPaymentDataSource> {
        MpesaPaymentDataSourceImpl(get())
    }

    single<MpesaService> {
        MpesaServiceImpl(get(named("mpesaClient")),get())
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

private fun provideDatabase(): CoroutineDatabase {
    val password = System.getenv("ATLAS_CLUSTER_PW")
    return KMongo.createClient("mongodb+srv://IPSUM_AMET:$password@bikeplaceapp.91afp.mongodb.net/$DB_NAME?retryWrites=true&w=majority").coroutine
        .getDatabase(DB_NAME)
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


data class DummyOrder(
    val orderDate: String = "14th Jul 2020, 20:16",
    val dropOffLocation: String = "Ilkemi Triangle, Turkana",
    val orderId: String = "QFK8ZQIEG4",
    val leaseActivation: String = "05 Aug 20, 10:00",
    val leaseExpiry: String = "08: Aug 20, 10:00",
    val userName:String = "Aquinas Dojo",
    val product:String = "Orange Hue",
    val price: String = "KES 333",
    val returnStatus: String = "Pending"
)





