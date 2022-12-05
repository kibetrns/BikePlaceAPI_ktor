package ipsum_amet.me.data.repository

import ipsum_amet.me.data.models.BookingsInfo
import me.ipsum_amet.bikeplace.data.model.ReturnStatus
import org.litote.kmongo.MongoOperator
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.slf4j.LoggerFactory

class BookingDataSourceImpl(
    db: CoroutineDatabase
) : BookingInfoDataSource {

    private val bookingsInfoCollection = db.getCollection<BookingsInfo>()
    val log = LoggerFactory.getLogger(BookingDataSourceImpl::class.java)

    override suspend fun getBookingInfoByUser(userId: String): List<BookingsInfo> {
        return bookingsInfoCollection.find(BookingsInfo::userId eq userId).toList()
    }

    override suspend fun getBookingInfoByMpesaReceiptNumber(mpesaReceiptNumber: String): BookingsInfo? {
        return bookingsInfoCollection.find(BookingsInfo::bookingId eq mpesaReceiptNumber).first()
    }

    override suspend fun getAllBookingsInfo(): List<BookingsInfo> {
        return bookingsInfoCollection.find().toList()
    }

    override suspend fun updateReturnStatusOfBookingInfo(mpesaReceiptNumber: String, returnStatus: ReturnStatus): Boolean {
        return bookingsInfoCollection.updateOne(
            "{bookingId:'$mpesaReceiptNumber'}",
            "{${MongoOperator.set}:{bikeReturnStatus:'$returnStatus'}}"
        ).wasAcknowledged()
    }
}
