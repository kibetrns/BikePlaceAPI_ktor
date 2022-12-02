package ipsum_amet.me.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ipsum_amet.me.Util.generateMpesaExpressPassword
import ipsum_amet.me.Util.generateTimeStamp
import ipsum_amet.me.data.models.AcknowledgementResponse
import ipsum_amet.me.data.models.Status
import ipsum_amet.me.data.remote.dtos.requests.mpesa.MpesaExternalSTKPushRequest
import ipsum_amet.me.data.remote.dtos.requests.mpesa.MpesaRegisterUrlRequest
import ipsum_amet.me.data.remote.dtos.requests.mpesa.MpesaSTKPushRequest
import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaSTKPushAsyncResponse
import ipsum_amet.me.service.MpesaService

var externalSTKPushRequest1: MpesaExternalSTKPushRequest? = null
suspend fun updateExternalSTKPushRequest1(externalSTKPushRequest: MpesaExternalSTKPushRequest) {
    externalSTKPushRequest1 = externalSTKPushRequest
}

fun Route.retrieveAuthToken(mPesaService: MpesaService, application: Application) {

    get("api/v1/mobile-payment/saf/retrieve-auth-token") {
        println("******** Mpesa Retrieve Auth. Token ********")

        try {
            call.respond(HttpStatusCode.OK, mPesaService.retrieveMpesaAuthToken())
        } catch (ex: Exception) {
            application.log.error(ex.localizedMessage)
        }

        println("******** Mpesa Retrieve Auth. Token ********")
    }


    get("posts") {
        call.respond(HttpStatusCode.OK, mPesaService.getPosts())
    }
}

fun Route.retrieveRegistrationUrl(application: Application, mPesaService: MpesaService) {
    post("api/v1/mobile-payment/saf/register-URL") {
        println("******** Mpesa Retrieve Register URL ********")

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

        println("******** Mpesa Retrieve Register URL ********")
    }
}

//fun Route.validateTransaction

fun Route.mpesaExpressTransactionRequest(application: Application, mPesaService: MpesaService) {
    post("api/v1/mobile-payment/saf/stk-transaction-request") {
        println("******** Mpesa Express Transaction Request URL ********")

        val externalMpesaRequest = call.receive<MpesaExternalSTKPushRequest>()

        updateExternalSTKPushRequest1(externalMpesaRequest)

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
            partyA = externalMpesaRequest.userPhoneNumber,
            partyB = application.environment.config.property("mpesa.daraja.mpesa-express.business-short-code").getString().toInt(),
            password = mpesaExpressPassword,
            phoneNumber = externalMpesaRequest.userPhoneNumber,
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

        println("******** Mpesa Express Transaction Request URL ********")
    }
}

fun Route.acknowledgeMpesaExpressResponse(application: Application, mPesaService: MpesaService) {
    post("api/v1/mobile-payment/saf/stk-transaction-result") {
        println("******** Mpesa Express Async. Response ********")
        try {

            val mpesaExpressAsyncResponse = call.receive<MpesaSTKPushAsyncResponse>()

            val saved = externalSTKPushRequest1?.let { mpesaExternalSTKPushRequest: MpesaExternalSTKPushRequest ->
                mPesaService.insertAsyncResponse(mpesaExpressAsyncResponse, mpesaExternalSTKPushRequest)
            }

            if (saved == true) {
                call.respond(
                    HttpStatusCode.Created,
                    AcknowledgementResponse(Status.SUCCESS, "Transaction Results saved To Database")
                )
            } else {
                call.respond(
                    HttpStatusCode.Conflict,
                    AcknowledgementResponse(Status.FAILED, "Transaction Result  NOT Saved To Database")
                )
                return@post
            }
            //call.respond(HttpStatusCode.OK, AcknowledgementResponse("Success"))

        } catch (ex: Exception) {
            application.log.error(ex.localizedMessage)
            application.log.error(ex.stackTraceToString())
        }
    }
    println("******** Mpesa Express Async. Response ********")

}


fun Route.trial() {
        get("api/v1/mobile-payment/validation") {
        call.respond("RECEIVED")
    }
}

