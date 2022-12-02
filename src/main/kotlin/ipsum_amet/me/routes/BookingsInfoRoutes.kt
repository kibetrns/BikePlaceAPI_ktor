package ipsum_amet.me.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ipsum_amet.me.data.models.AcknowledgementResponse
import ipsum_amet.me.data.models.Status
import ipsum_amet.me.data.remote.dtos.requests.mpesa.BookingsInfoRequest
import ipsum_amet.me.service.BookingsInfoService



fun Route.retrieveUserBookingsInfo(application: Application, bookingsInfoService: BookingsInfoService) {
    get("api/v1/user-bookings-info") {

        //val request = call.receive<BookingsInfoRequest>()
        val userId = call.request.queryParameters["userId"] ?: ""

        //application.log.debug(request.toString())
        application.log.debug(userId)

        try {
            call.respond(
                HttpStatusCode.OK,
                //bookingsInfoService.getBookingInfoByUser(request.userId)
                bookingsInfoService.getBookingInfoByUser(userId)
            )
        } catch (ex: Exception) {
            application.log.error(ex.localizedMessage)
            application.log.error(ex.stackTraceToString())
            call.respond(
                HttpStatusCode.InternalServerError,
                AcknowledgementResponse(Status.FAILED, ex.localizedMessage)
            )
            return@get
        }
    }
}
fun Route.retrieveBookingsInfoById(application: Application, bookingsInfoService: BookingsInfoService) {
    get("api/v1/bookings-info-by-receipt-id") {

        //val request = call.receive<BookingsInfoRequest>()
        val receiptId = call.request.queryParameters["receiptId"] ?: ""

        //application.log.debug(request.toString())
        application.log.debug(receiptId)

        try {
            call.respond(
                HttpStatusCode.OK,
                //bookingsInfoService.getBookingInfoByMpesaReceiptNumber(request.mpesaReceiptNumber)!!

                /*
                Revisit the logic of the line below in regards to NPE
                */
                bookingsInfoService.getBookingInfoByMpesaReceiptNumber(receiptId)!!
            )
            //application.log.debug(bookingsInfoService.getBookingInfoByMpesaReceiptNumber(request.mpesaReceiptNumber).toString())
            application.log.debug(bookingsInfoService.getBookingInfoByMpesaReceiptNumber(receiptId).toString())
        } catch (ex: Exception) {
            application.log.error(ex.localizedMessage)
            application.log.error(ex.stackTraceToString())
            call.respond(
                HttpStatusCode.InternalServerError,
                AcknowledgementResponse(Status.FAILED, ex.localizedMessage)
            )
            return@get
        }
    }
}