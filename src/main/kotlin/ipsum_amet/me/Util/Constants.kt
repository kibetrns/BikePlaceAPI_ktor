package ipsum_amet.me.Util

sealed class MpesaEndpoints(val url: String) {
    object Authorisation: MpesaEndpoints(url = "https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
    object C2BRegisterURL: MpesaEndpoints(url = "https://sandbox.safaricom.co.ke/mpesa/c2b/v1/registerurl")
    object MpesaExpress: MpesaEndpoints(url = "https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest")

}

sealed class AirtelMoneyEndpoints(val url: String) {
    object Authorisation: AirtelMoneyEndpoints(url = "https://openapiuat.airtel.africa/auth/oauth2/token")
}


const val DATABASE_NAME = "BikePlace_DB"