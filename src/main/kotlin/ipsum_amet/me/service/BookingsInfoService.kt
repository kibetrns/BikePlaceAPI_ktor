package ipsum_amet.me.service

import ipsum_amet.me.data.remote.dtos.responses.BookingsInfoDTO
import me.ipsum_amet.bikeplace.data.model.ReturnStatus

interface BookingsInfoService {
    suspend fun getBookingInfoByUser(userId: String): List<BookingsInfoDTO>

    suspend fun getBookingInfoByMpesaReceiptNumber(mpesaReceiptNumber: String): BookingsInfoDTO?

    suspend fun getAllBookingsInfo(): List<BookingsInfoDTO>

    suspend fun updateReturnStatusOfBookingInfo(mpesaReceiptNumber: String, returnStatus: ReturnStatus): Boolean

}