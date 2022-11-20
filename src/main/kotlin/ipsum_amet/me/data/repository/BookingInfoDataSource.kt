package ipsum_amet.me.data.repository

import ipsum_amet.me.data.models.BookingsInfo

interface BookingInfoDataSource {
    fun getBookingInfoByUser(userNumber: Long): BookingsInfo
    fun getBookingInfoByMpesaReceiptNumber(mpesaReceiptNumber: String): BookingsInfo
}