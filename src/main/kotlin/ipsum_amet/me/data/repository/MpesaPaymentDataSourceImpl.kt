package ipsum_amet.me.data.repository
import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaSTKPushAsyncResponse
import org.litote.kmongo.coroutine.CoroutineDatabase

class MpesaPaymentDataSourceImpl(
    db: CoroutineDatabase
) : MpesaPaymentDataSource {

    private val mpesaSTKPushAsyncResponseCollection = db.getCollection<MpesaSTKPushAsyncResponse>()

    override suspend fun insertMpesaPaymentInfo(mpesaSTKPushAsyncResponse: MpesaSTKPushAsyncResponse): Boolean {
        return try {
            mpesaSTKPushAsyncResponseCollection.insertOne(
             MpesaSTKPushAsyncResponse(
                 body = mpesaSTKPushAsyncResponse.body
             )
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