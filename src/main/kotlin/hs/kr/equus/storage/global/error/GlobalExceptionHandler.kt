package hs.kr.equus.storage.global.error

import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler(
    private val messageSource: MessageSource
) {
    @ExceptionHandler(IllegalArgumentException::class)
    fun validatorExceptionHandler(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                400,
                e.message
            ),
            HttpStatus.BAD_REQUEST
        )
    }
}
