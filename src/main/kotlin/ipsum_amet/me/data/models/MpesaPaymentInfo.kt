package ipsum_amet.me.data.models

import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaSTKPushAsyncResponse
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.Date

@Serializable
data class MpesaPaymentInfo(
    @BsonId
    val internalId: String = ObjectId().toString(),
    val transactionId: String,
    val transactionType: String,
    val msisdn: String,
    val amount: Long,
    val merchantRequestID: String,
    val checkoutRequestID: String,
    val entryDate: String,
    val resultCode: String,
    val rawCallbackPayloadResponse: MpesaSTKPushAsyncResponse
)