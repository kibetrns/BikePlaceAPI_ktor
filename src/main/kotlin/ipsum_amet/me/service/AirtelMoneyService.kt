package ipsum_amet.me.service

import ipsum_amet.me.data.remote.dtos.requests.airtelMoney.AirtelMoneyAuthRequest
import ipsum_amet.me.data.remote.dtos.responses.airtelMoney.AirtelMAuthResponse

interface AirtelMoneyService {
    suspend fun retrieveAirtelMoneyAuthToken(authRequest: AirtelMoneyAuthRequest): AirtelMAuthResponse
}