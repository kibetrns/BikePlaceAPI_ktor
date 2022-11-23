package ipsum_amet.me.Util

import org.apache.commons.lang3.RandomStringUtils
import java.text.SimpleDateFormat
import java.util.*

const val STRING_LENGTH = 12

sealed class RequestState<out T> {
    //object Idle : RequestState<Nothing>()
    //object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val error: Throwable? = null, ) : RequestState<Nothing>()
}

fun encodeToBase64(input: String): String = Base64.getEncoder().encodeToString(input.toByteArray())

fun generateUniqueTransactionNumber() = RandomStringUtils.randomAlphanumeric(STRING_LENGTH).uppercase()

fun generateMpesaExpressPassword(shortKey: String, passKey: String, timeStamp: String) =
    encodeToBase64(String.format("%s%s%s", shortKey, passKey, timeStamp))

fun generateTimeStamp(): String {
    return SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
}

//"https://4afd-41-89-227-170.eu.ngrok.io/api/v1/mobile-payment/saf/stk-transaction-result"



