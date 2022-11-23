package ipsum_amet.me.data.repository
import ipsum_amet.me.data.models.BookingsInfo
import ipsum_amet.me.data.remote.dtos.requests.mpesa.MpesaExternalSTKPushRequest
import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaSTKPushAsyncResponse
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MpesaPaymentDataSourceImpl(
    db: CoroutineDatabase
) : MpesaPaymentDataSource {

    private val mpesaSTKPushAsyncResponseCollection = db.getCollection<MpesaSTKPushAsyncResponse>()
    private val bookingsInfoCollection = db.getCollection<BookingsInfo>()
    val log = LoggerFactory.getLogger(MpesaPaymentDataSourceImpl::class.java)

    override suspend fun insertMpesaPaymentInfo(
        mpesaSTKPushAsyncResponse: MpesaSTKPushAsyncResponse,
        mpesaExternalSTKPushRequest: MpesaExternalSTKPushRequest
    ): Boolean {
        log.debug(
            BookingsInfo(
                bookingId = mpesaSTKPushAsyncResponse.body.stkCallback.callbackMetadata.item[1].value!!,
                dateBookingMade = LocalDateTime.parse(
                    mpesaSTKPushAsyncResponse.body.stkCallback.callbackMetadata.item[3].value!!,
                    DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
                ),
                bikeDropOffLocation = mpesaExternalSTKPushRequest.bikeDropOffLocation,
                bikeLeaseActivation = LocalDateTime.parse(
                    mpesaExternalSTKPushRequest.bikeLeaseActivation,
                    DateTimeFormatter.ofPattern("yyyyMMddHHmm")
                ),
                bikeLeaseExpiry = LocalDateTime.parse(
                    mpesaExternalSTKPushRequest.bikeLeaseExpiry,
                    DateTimeFormatter.ofPattern("yyyyMMddHHmm")
                ),
                userName = mpesaExternalSTKPushRequest.userName,
                userId = mpesaExternalSTKPushRequest.userId,
                bikeId = mpesaExternalSTKPushRequest.bikeId,
                bikeName = mpesaExternalSTKPushRequest.bikeName,
                amount = mpesaSTKPushAsyncResponse.body.stkCallback.callbackMetadata.item[0].value!!.toDouble(),
                bikeReturnStatus = mpesaExternalSTKPushRequest.bikeReturnStatus,
                userPhoneNumber = mpesaExternalSTKPushRequest.userPhoneNumber
            ).toString()
        )
        return try {
            bookingsInfoCollection.insertOne(
                BookingsInfo(
                    bookingId = mpesaSTKPushAsyncResponse.body.stkCallback.callbackMetadata.item[1].value!!,
                    dateBookingMade = LocalDateTime.parse(
                            mpesaSTKPushAsyncResponse.body.stkCallback.callbackMetadata.item[3].value!!,
                            DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
                        ),
                    bikeDropOffLocation = mpesaExternalSTKPushRequest.bikeDropOffLocation,
                    bikeLeaseActivation = LocalDateTime.parse(
                        mpesaExternalSTKPushRequest.bikeLeaseActivation,
                        DateTimeFormatter.ofPattern("yyyyMMddHHmm")
                    ),
                    bikeLeaseExpiry = LocalDateTime.parse(
                        mpesaExternalSTKPushRequest.bikeLeaseExpiry,
                        DateTimeFormatter.ofPattern("yyyyMMddHHmm")
                    ),
                    userName = mpesaExternalSTKPushRequest.userName,
                    userId = mpesaExternalSTKPushRequest.userId,
                    bikeId = mpesaExternalSTKPushRequest.bikeId,
                    bikeName = mpesaExternalSTKPushRequest.bikeName,
                    amount = mpesaSTKPushAsyncResponse.body.stkCallback.callbackMetadata.item[0].value!!.toDouble(),
                    bikeReturnStatus = mpesaExternalSTKPushRequest.bikeReturnStatus,
                    userPhoneNumber = mpesaExternalSTKPushRequest.userPhoneNumber
                )
                /*
             MpesaSTKPushAsyncResponse(
                 body = mpesaSTKPushAsyncResponse.body
             )

                 */
            ).wasAcknowledged()
        } catch (ex: Exception) {
            false
        }
    }

    override suspend fun getByMerchantRequestOrCheckOutId(merchantRequestID: String, checkoutRequestID: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getByTransactionId(transactionId: String) {
        TODO("Not yet implemented")
    }
}