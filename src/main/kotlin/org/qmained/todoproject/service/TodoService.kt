package org.qmained.todoproject.service

import com.github.benmanes.caffeine.cache.AsyncCache
import kotlinx.coroutines.flow.toList
import org.qmained.todoproject.exceptions.TodoNotFound
import org.qmained.todoproject.mapper.TodoMapper
import org.qmained.todoproject.models.dto.TodoDto
import org.qmained.todoproject.models.entity.TodoModel
import org.qmained.todoproject.repository.TodoRepository
import org.qmained.todoproject.utils.getOrPut
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.deleteAndAwait
import org.springframework.data.redis.core.getAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.data.redis.core.types.Expiration
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val todoMapper: TodoMapper,
    private val todoAsyncCache: AsyncCache<UUID, TodoModel>,
    redisTemplate: ReactiveRedisTemplate<String, Any>,

) {
    private val valueOps = redisTemplate.opsForValue()

    suspend fun getAll(): List<TodoModel> {
        return todoRepository.findAll()
            .toList()
    }

    suspend fun add(todoDto: TodoDto): TodoModel {
        val todoModel = todoMapper.todoDtoToTodoModel(todoDto).apply { this.id = UUID.randomUUID() }
        return todoRepository.save(todoModel)
    }

    suspend fun update(id: UUID, todoDto: TodoDto): TodoModel {
        val cacheKey = "todo:$id"
        val entity = todoRepository.findById(id) ?: throw TodoNotFound(id)
        todoMapper.updateTodoModelFromDto(todoDto, entity)
        valueOps.setAndAwait(cacheKey, entity, Expiration.seconds(60))
        return todoRepository.save(entity)
    }

    suspend fun getById(id: UUID): TodoModel? {
//        val cacheKey = "todo:$id"
//        val cachedModel = valueOps.getAndAwait(cacheKey) as? TodoModel
//        if (cachedModel != null) {
//            return cachedModel
//        }
        return todoAsyncCache.getOrPut(id) { uuid ->
            todoRepository.findById(uuid) ?: throw TodoNotFound(id)
        }
//        valueOps.setAndAwait(cacheKey, model, Expiration.seconds(60))
    }

    suspend fun delete(id: UUID) {
        if (!todoRepository.existsById(id)) {
            throw TodoNotFound(id)
        }
        val cacheKey = "todo:$id"
        valueOps.deleteAndAwait(cacheKey)
        todoRepository.deleteById(id)
    }
}

