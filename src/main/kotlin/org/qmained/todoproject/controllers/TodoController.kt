package org.qmained.todoproject.controllers

import org.qmained.todoproject.models.dto.TodoDto
import org.qmained.todoproject.models.entity.TodoModel
import org.qmained.todoproject.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/todos")
class TodoController(
    private val todoService: TodoService,
) {
    @GetMapping
    suspend fun todos(): ResponseEntity<List<TodoModel>> {
        return ResponseEntity.ok(todoService.getAll())
    }

    @PostMapping
    suspend fun createTodo(@Validated @RequestBody todoDto: TodoDto): ResponseEntity<TodoModel> {
        return ResponseEntity(todoService.add(todoDto), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    suspend fun update(@Validated @RequestBody todoDto: TodoDto, @PathVariable id: UUID): TodoModel {
        return todoService.update(id, todoDto)
    }

    @GetMapping("/{id}")
    suspend fun get(@PathVariable id: UUID): ResponseEntity<TodoModel> {
        return ResponseEntity.ok(todoService.getById(id))
    }

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable id: UUID): ResponseEntity<Any> {
        todoService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
