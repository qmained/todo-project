package org.qmained.todoproject

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.qmained.todoproject.models.dto.TodoDto
import org.qmained.todoproject.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import tools.jackson.databind.ObjectMapper

@SpringBootTest
@AutoConfigureMockMvc
class TodoProjectApplicationTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var repository: TodoRepository

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun contextLoads() {
    }

    @BeforeEach
    fun setUp() {
        repository.deleteAll()
    }

    @Test
    fun `create todo should return success`() {
        mockMvc.post("/todos") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(TodoDto("Hello world!"))
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isCreated() }
            jsonPath("$.title") { value("Hello world!") }
        }
    }

}
