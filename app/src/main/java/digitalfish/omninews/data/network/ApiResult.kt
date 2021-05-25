package digitalfish.omninews.data.network

sealed class ApiResult<out T: Any> {

    //Sealed classes are used for representing restricted class hierarchies,
    // when a value can have one of the types from a limited set,
    // but cannot have any other type.
    // They are, in a sense, an extension of enum classes: the set of values for an enum type is also restricted,
    // but each enum constant exists only as a single instance,
    // whereas a subclass of a sealed class can have multiple instances which can contain state.

    data class Success<out T: Any>(val data: T): ApiResult<T>()
    data class Error(val exception: String): ApiResult<Nothing>()

    fun getMessage(): String {
        return when(this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> ":$exception"
        }
    }
}
