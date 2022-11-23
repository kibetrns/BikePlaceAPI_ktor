package ipsum_amet.me.service

import ipsum_amet.me.data.models.toDTO
import ipsum_amet.me.data.remote.dtos.responses.BookingsInfoDTO
import ipsum_amet.me.data.repository.BookingInfoDataSource

class BookingsInfoServiceImpl(
    private val repository: BookingInfoDataSource
) : BookingsInfoService {
    override suspend fun getBookingInfoByUser(userId: String): List<BookingsInfoDTO> {
        return repository.getBookingInfoByUser(userId).map { it.toDTO() }
    }

    override suspend fun getBookingInfoByMpesaReceiptNumber(mpesaReceiptNumber: String): BookingsInfoDTO? {
        return repository.getBookingInfoByMpesaReceiptNumber(mpesaReceiptNumber)?.toDTO()
    }
}
