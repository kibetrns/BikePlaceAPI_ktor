package ipsum_amet.me.data.repository

import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaSTKPushAsyncResponse

interface MpesaPaymentDataSource {
    suspend fun insertMpesaPaymentInfo(mpesaSTKPushAsyncResponse: MpesaSTKPushAsyncResponse): Boolean
    suspend fun getByMerchantRequestOrCheckOutId(merchantRequestID: String, checkoutRequestID: String)
    suspend fun getByTransactionId(transactionId: String)
}