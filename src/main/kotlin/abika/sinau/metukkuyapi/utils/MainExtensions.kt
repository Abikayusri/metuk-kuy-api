package abika.sinau.metukkuyapi.utils

import abika.sinau.metukkuyapi.exception.MetukException

inline fun <reified T> T?.orThrow(
    message: String = "${T::class.simpleName} is Null"
): T {
    return this ?: throw MetukException(message)
}

//fun <T> T?.orThrow(): T {
//    return this ?: throw MetukException("Is Null")
//}

//fun <T> T?.toResult(): Result<T> {
//    return if (this != null) {
//        Result.success(this)
//    } else {
//        Result.failure(IllegalStateException("Value Null!!!"))
//    }
//}

inline fun <reified T> T?.toResult(
        message: String = "${T::class.simpleName} is null"
): Result<T> {
    return if (this != null) {
        Result.success(this)
    } else {
        Result.failure(MetukException(message))
    }
}

fun <T>Result<T>.toResponses(): BaseResponse<T> {
    return if (this.isFailure) {
        throw MetukException(this.exceptionOrNull()?.message ?: "Failure")
    } else {
        BaseResponse.success(this.getOrNull())
    }
}