package org.qmained.todoproject.models.dto

import jakarta.validation.constraints.NotEmpty

data class TodoDto(
    @NotEmpty(message = "title must not be empty")
    val title: String,
    val completed: Boolean? = false,
)
