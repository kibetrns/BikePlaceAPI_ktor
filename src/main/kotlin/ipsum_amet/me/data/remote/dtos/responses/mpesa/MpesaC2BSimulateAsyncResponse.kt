package ipsum_amet.me.data.remote.dtos.responses.mpesa


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MpesaC2BSimulateAsyncResponse(
    @SerialName("BillRefNumber")
    val billRefNumber: String,
    @SerialName("BusinessShortCode")
    val businessShortCode: String,
    @SerialName("FirstName")
    val firstName: String,
    @SerialName("LastName")
    val lastName: String,
    @SerialName("MSISDN")
    val mSISDN: Long,
    @SerialName("MiddleName")
    val middleName: String,
    @SerialName("OrgAccountBalance")
    val orgAccountBalance: Double,
    @SerialName("ThirdPartyTransID")
    val thirdPartyTransID: String,
    @SerialName(" TransAmount")
    val transAmount: Int,
    @SerialName("TransID")
    val transID: String,
    @SerialName("TransTime")
    val transTime: String,
    @SerialName("TransactionType")
    val transactionType: String
)