package ipsum_amet.me.service

import ipsum_amet.me.data.models.toDTO
import ipsum_amet.me.data.remote.dtos.responses.BookingsInfoDTO
import ipsum_amet.me.data.repository.BookingInfoDataSource
import me.ipsum_amet.bikeplace.data.model.ReturnStatus
import kotlin.reflect.KProperty1

class BookingsInfoServiceImpl(
    private val repository: BookingInfoDataSource
) : BookingsInfoService {
    override suspend fun getBookingInfoByUser(userId: String): List<BookingsInfoDTO> {
        return repository.getBookingInfoByUser(userId).map { it.toDTO() }
    }

    override suspend fun getBookingInfoByMpesaReceiptNumber(mpesaReceiptNumber: String): BookingsInfoDTO? {
        return repository.getBookingInfoByMpesaReceiptNumber(mpesaReceiptNumber)?.toDTO()
    }

    override suspend fun getAllBookingsInfo(): List<BookingsInfoDTO> {
        return repository.getAllBookingsInfo().map { it.toDTO() }
    }

    override suspend fun updateReturnStatusOfBookingInfo(mpesaReceiptNumber: String, returnStatus: ReturnStatus): Boolean {
        return repository.updateReturnStatusOfBookingInfo(mpesaReceiptNumber,returnStatus)
    }
}
