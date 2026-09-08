package org.qmained.todoproject.models.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Table
class TodoModel(
    @Id
    var id: UUID? = UUID.randomUUID(),
    var title: String,
    var completed: Boolean = false,
    var createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now(),
)
