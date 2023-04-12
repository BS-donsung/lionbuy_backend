package com.ateam.lionbuy.security.util

import com.ateam.lionbuy.security.dto.AuthDTO
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ObjectMapperTests
@Autowired constructor(
    val objectMapper: ObjectMapper
){
    @Test
    fun `parsing fit data by AuthDTO`() {
        val fitStringData = """
            {
                "username" : "james",
                "password" : "1234"
            }
        """.trimIndent()

        val result = objectMapper.readValue<AuthDTO>(fitStringData)
        assert(result.username == "james")
        assert(result.password == "1234")
    }

    @Test
    fun `parsing over data by AuthDTO`() {
        val fitStringData = """
            {
                "username" : "james",
                "password" : "1234",
                "mocking" : "mock"
            }
        """.trimIndent()

        val result = objectMapper.readValue<AuthDTO>(fitStringData)
        assert(result.username == "james")
        assert(result.password == "1234")
    }

    @Test
    fun `parsing invalid data by AuthDTO`() {
        val fitStringData = """
            {
                "password" : "1234",
                "mocking" : "mock"
            }
        """.trimIndent()

        val result = objectMapper.readValue<AuthDTO>(fitStringData)
        assert(result.username == "")
        assert(result.password == "1234")
    }
}