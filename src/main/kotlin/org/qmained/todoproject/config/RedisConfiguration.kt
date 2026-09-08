package org.qmained.todoproject.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import tools.jackson.module.kotlin.jacksonObjectMapper

@Configuration
class RedisConfiguration {
    @Bean
    fun reactiveRedisTemplate(
        factory: ReactiveRedisConnectionFactory,
    ): ReactiveRedisTemplate<String, Any> {
        // Ensure Jackson knows how to handle Kotlin data classes
        val serializer = GenericJacksonJsonRedisSerializer(jacksonObjectMapper())

        val serializationContext = RedisSerializationContext
            .newSerializationContext<String, Any>(StringRedisSerializer())
            .value(serializer)
            .build()

        return ReactiveRedisTemplate(factory, serializationContext)
    }
}
