package org.qmained.todoproject.config

import kotlinx.coroutines.asCoroutineDispatcher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

@Configuration
class CoroutinesConfig {

    @Bean
    fun virtualThreadDispatcher(): CoroutineContext {
        return Executors.newVirtualThreadPerTaskExecutor().asCoroutineDispatcher()
    }

}
