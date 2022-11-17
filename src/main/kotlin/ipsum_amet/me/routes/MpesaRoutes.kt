package ipsum_amet.me.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ipsum_amet.me.Util.generateMpesaExpressPassword
import ipsum_amet.me.Util.generateTimeStamp
import ipsum_amet.me.data.model.AcknowledgementResponse
import ipsum_amet.me.data.remote.dtos.requests.mpesa.MpesaExternalSTKPushRequest
import ipsum_amet.me.data.remote.dtos.requests.mpesa.MpesaRegisterUrlRequest
import ipsum_amet.me.data.remote.dtos.requests.mpesa.MpesaSTKPushRequest
import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaSTKPushAsyncResponse
import ipsum_amet.me.service.MpesaService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

val scope = CoroutineScope(Dispatchers.IO)

fun Route.retrieveAuthToken(mPesaService: MpesaService, application: Application) {

    get("api/v1/mobile-payment/saf/retrieve-auth-token") {

        try {
            call.respond(HttpStatusCode.OK, mPesaService.retrieveMpesaAuthToken())
        } catch (ex: Exception) {
            application.log.error(ex.localizedMessage)
        }
    }

    get("posts") {
        call.respond(HttpStatusCode.OK, mPesaService.getPosts())
    }
}

fun Route.retrieveRegistrationUrl(application: Application, mPesaService: MpesaService) {
    post("api/v1/mobile-payment/saf/register-URL") {
        val registerUrlRequest = MpesaRegisterUrlRequest(
            confirmationURL = application.environment.config.property("mpesa.daraja.confirmation-URL").getString(),
            responseType = application.environment.config.property("mpesa.daraja.response-type").getString(),
            shortCode = application.environment.config.property("mpesa.daraja.short-code").getString(),
            validationURL = application.environment.config.property("mpesa.daraja.validation-URL").getString()
        )
        try {
            call.respond(HttpStatusCode.OK, mPesaService.retrieveC2BURL(registerUrlRequest))
        } catch (ex: Exception) {
            application.log.error(ex.stackTraceToString())
        }
    }
}

//fun Route.validateTransaction

fun Route.mpesaExpressTransactionResults(application: Application, mPesaService: MpesaService) {
    post("api/v1/mobile-payment/saf/stk-transaction-request") {

        val externalMpesaRequest = call.receive<MpesaExternalSTKPushRequest>()

        val timeStamp = generateTimeStamp()
        val mpesaExpressPassword = generateMpesaExpressPassword(
            shortKey = application.environment.config.property("mpesa.daraja.mpesa-express.business-short-code").getString(),
            passKey = System.getenv("MPESA_EXPRESS_PASS_KEY"),
            timeStamp = timeStamp
        )
        application.log.debug(externalMpesaRequest.toString())

        val stkPushRequest = MpesaSTKPushRequest(
            accountReference = application.environment.config.property("mpesa.daraja.mpesa-express.account-reference").getString(),
            amount = externalMpesaRequest.amount,
            businessShortCode = application.environment.config.property("mpesa.daraja.mpesa-express.business-short-code").getString().toInt(),
            callBackURL = application.environment.config.property("mpesa.daraja.mpesa-express.callback-URL").getString(),
            partyA = externalMpesaRequest.phoneNumber,
            partyB = application.environment.config.property("mpesa.daraja.mpesa-express.business-short-code").getString().toInt(),
            password = mpesaExpressPassword,
            phoneNumber = externalMpesaRequest.phoneNumber,
            timestamp = timeStamp,
            transactionDesc = application.environment.config.property("mpesa.daraja.mpesa-express.transaction-desc").getString(),
            transactionType = application.environment.config.property("mpesa.daraja.mpesa-express.transaction-type").getString()
        )
        application.log.debug(stkPushRequest.toString())
        try {
            call.respond(HttpStatusCode.OK, mPesaService.initiateMpesaExpress(stkPushRequest))
        } catch (ex: Exception) {
            application.log.error(ex.localizedMessage)
            application.log.error(ex.stackTraceToString())
        }
    }
}

fun Route.acknowledgeMpesaExpressResponse(application: Application, mPesaService: MpesaService) {
    post("api/v1/mobile-payment/saf/stk-transaction-result") {
        println("******** Mpesa Express Async. Response ********")
        try {
            call.respond(HttpStatusCode.OK, AcknowledgementResponse("Success"))
            call.receive<MpesaSTKPushAsyncResponse>()
        } catch (ex: Exception) {
            application.log.error(ex.localizedMessage)
            application.log.error(ex.stackTraceToString())
        }
    }
}


fun Route.trial() {
        get("api/v1/mobile-payment/validation") {
        call.respond("RECEIVED")
    }
}

