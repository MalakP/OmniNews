package digitalfish.omninews.data.network

sealed class ApiResult<out T: Any> {

    data class Success<out T: Any>(val data: T): ApiResult<T>()
    data class Error(val exception: String): ApiResult<Nothing>()

    fun getMessage(): String {
        return when(this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> ":$exception"
        }
    }
}
