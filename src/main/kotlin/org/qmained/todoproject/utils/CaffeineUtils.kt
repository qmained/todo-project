package org.qmained.todoproject.utils

import com.github.benmanes.caffeine.cache.AsyncCache
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.future.asDeferred
import kotlinx.coroutines.future.future

suspend fun <K : Any, V : Any> AsyncCache<K, V>.getOrPut(
    key: K,
    load: suspend (K) -> V
): V {
    // Fast path: synchronous lookup, no Future/coroutine overhead
    synchronous().getIfPresent(key)?.let { return it }

    // Slow path: load through AsyncCache (stampede protection preserved)
    return coroutineScope {
        val future = get(key) { k, _ ->
            this.future { load(k) }
        }
        future.asDeferred().await()
    }
}

