package ipsum_amet.me.plugins


import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import ipsum_amet.me.routes.*
import ipsum_amet.me.service.MpesaService
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val mPesaService by inject<MpesaService>()
    //val airtelMoneyService by inject<AirtelMoneyService>()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        retrieveAuthToken(mPesaService, application)
        retrieveRegistrationUrl(application, mPesaService)
        mpesaExpressTransactionRequest(application, mPesaService)
        acknowledgeMpesaExpressResponse(application, mPesaService)

        //retrieveAirtelMoneyAuthToken(airtelMoneyService, application)

        trial()
    }
}

