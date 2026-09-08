package org.qmained.todoproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories(basePackages = ["org.qmained.todoproject.repository"])
class TodoProjectApplication

fun main(args: Array<String>) {
    runApplication<TodoProjectApplication>(*args)
}
