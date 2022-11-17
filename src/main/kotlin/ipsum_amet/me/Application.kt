package ipsum_amet.me

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import ipsum_amet.me.di.configureKoin
import ipsum_amet.me.plugins.*
import kotlinx.serialization.ExperimentalSerializationApi


fun main(args: Array<String>): Unit =
    io.ktor.server.cio.EngineMain.main(args)

@OptIn(ExperimentalSerializationApi::class)
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureKoin()
    configureMonitoring()
    configureSerialization()
    configureSecurity()
    configureRouting()

}

