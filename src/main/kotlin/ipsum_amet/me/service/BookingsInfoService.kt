package ipsum_amet.me.service

import ipsum_amet.me.data.remote.dtos.responses.BookingsInfoDTO

interface BookingsInfoService {
    suspend fun getBookingInfoByUser(userId: String): List<BookingsInfoDTO>

    suspend fun getBookingInfoByMpesaReceiptNumber(mpesaReceiptNumber: String): BookingsInfoDTO?
}