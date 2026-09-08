package org.qmained.todoproject.config

import com.github.benmanes.caffeine.cache.AsyncCache
import com.github.benmanes.caffeine.cache.Caffeine
import org.qmained.todoproject.models.entity.TodoModel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.UUID
import java.util.concurrent.TimeUnit

@Configuration
class CacheConfig {

    @Bean
    fun todoAsyncCache(): AsyncCache<UUID, TodoModel> {
        return Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(10_000)
            .buildAsync() // Generates an AsyncCache
    }
}
