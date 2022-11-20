package ipsum_amet.me.service

import ipsum_amet.me.data.remote.dtos.requests.mpesa.MpesaExternalSTKPushRequest
import ipsum_amet.me.data.remote.dtos.requests.mpesa.MpesaRegisterUrlRequest
import ipsum_amet.me.data.remote.dtos.requests.mpesa.MpesaSTKPushRequest
import ipsum_amet.me.data.remote.dtos.responses.*
import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaAuthorisationResponse
import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaC2BRegisterURLResponse
import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaSTKPushAsyncResponse
import ipsum_amet.me.data.remote.dtos.responses.mpesa.MpesaSTKPushSyncResponse

interface MpesaService {

    suspend fun retrieveMpesaAuthToken(): MpesaAuthorisationResponse
    suspend fun retrieveC2BURL(registerUrlRequest: MpesaRegisterUrlRequest): MpesaC2BRegisterURLResponse

    suspend fun initiateMpesaExpress(sTKPushRequest: MpesaSTKPushRequest): MpesaSTKPushSyncResponse

    //suspend fun receiveMpesaExpressAsyncResponse(stkPushSyncResponse: STKPushSyncResponse) : STKPushAsyncResponse

    suspend fun insertAsyncResponse(
        mpesaSTKPushAsyncResponse: MpesaSTKPushAsyncResponse,
        mpesaExternalSTKPushRequest: MpesaExternalSTKPushRequest
    ): Boolean

    suspend fun getPosts(): List<Post>
}