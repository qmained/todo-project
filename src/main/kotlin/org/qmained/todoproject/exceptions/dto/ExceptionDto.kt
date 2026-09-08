package org.qmained.todoproject.exceptions.dto

data class ExceptionDto(
    val error: Error,
) {
    data class Error(
        val code: String,
        val message: String,
    )
}
