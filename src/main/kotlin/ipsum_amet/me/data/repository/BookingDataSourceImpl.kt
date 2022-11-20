package ipsum_amet.me.data.repository

import ipsum_amet.me.data.models.BookingsInfo

class BookingDataSourceImpl : BookingInfoDataSource {
    override fun getBookingInfoByUser(userNumber: Long): BookingsInfo {
        TODO("Not yet implemented")
    }

    override fun getBookingInfoByMpesaReceiptNumber(mpesaReceiptNumber: String): BookingsInfo {
        TODO("Not yet implemented")
    }
}