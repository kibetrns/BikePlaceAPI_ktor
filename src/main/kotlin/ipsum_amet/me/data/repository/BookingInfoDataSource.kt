package ipsum_amet.me.data.repository

import ipsum_amet.me.data.models.BookingsInfo

interface BookingInfoDataSource {
    suspend fun getBookingInfoByUser(userId: String): List<BookingsInfo>
    suspend fun getBookingInfoByMpesaReceiptNumber(mpesaReceiptNumber: String): BookingsInfo?
}