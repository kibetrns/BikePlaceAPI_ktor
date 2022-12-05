package ipsum_amet.me.data.repository

import ipsum_amet.me.data.models.BookingsInfo
import me.ipsum_amet.bikeplace.data.model.ReturnStatus

interface BookingInfoDataSource {
    suspend fun getBookingInfoByUser(userId: String): List<BookingsInfo>
    suspend fun getBookingInfoByMpesaReceiptNumber(mpesaReceiptNumber: String): BookingsInfo?

    suspend fun getAllBookingsInfo(): List<BookingsInfo>

    suspend fun updateReturnStatusOfBookingInfo(mpesaReceiptNumber: String, returnStatus: ReturnStatus): Boolean

}