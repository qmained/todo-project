package org.qmained.todoproject.repository

import org.qmained.todoproject.models.entity.TodoModel
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TodoRepository : CoroutineCrudRepository<TodoModel, UUID>

