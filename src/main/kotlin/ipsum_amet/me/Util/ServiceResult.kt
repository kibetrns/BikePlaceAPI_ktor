package ipsum_amet.me.Util

sealed class ServiceResult {
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val error: Throwable) : RequestState<Nothing>()
}