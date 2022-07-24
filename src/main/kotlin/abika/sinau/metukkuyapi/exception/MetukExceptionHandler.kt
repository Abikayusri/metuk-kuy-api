package abika.sinau.metukkuyapi.exception

import abika.sinau.metukkuyapi.utils.BaseResponse
import abika.sinau.metukkuyapi.utils.Empty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class MetukExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [MetukException::class])
    fun handleThrowable(throwable: MetukException): ResponseEntity<BaseResponse<Empty>> {
        return ResponseEntity(BaseResponse.failure(throwable.message ?: "Failure"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}