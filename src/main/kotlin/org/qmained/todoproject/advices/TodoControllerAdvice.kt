package org.qmained.todoproject.advices

import org.qmained.todoproject.exceptions.TodoNotFound
import org.qmained.todoproject.exceptions.dto.ExceptionDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class TodoControllerAdvice {
    @ExceptionHandler(TodoNotFound::class)
    suspend fun handleDtoNotFound(): ResponseEntity<ExceptionDto> {
        return ResponseEntity(
            ExceptionDto(ExceptionDto.Error("TODO_NOT_FOUND", "Todo not found")),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    suspend fun handleBadRequest(e: MethodArgumentNotValidException): ResponseEntity<ExceptionDto> {
        return ResponseEntity(
            ExceptionDto(
                ExceptionDto.Error(
                    "INVALID_REQUEST",
                    e.bindingResult.fieldErrors.joinToString(",") { it.defaultMessage ?: "" }
                )
            ),
            HttpStatus.NOT_FOUND
        )
    }
}
