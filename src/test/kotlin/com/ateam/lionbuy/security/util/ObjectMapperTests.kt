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
                "principal" : "success@success.com",
                "credential" : "1234"
            }
        """.trimIndent()

        val result = objectMapper.readValue<AuthDTO>(fitStringData)
        assert(result.principal == "success@success.com")
        assert(result.credential == "1234")
    }

    @Test
    fun `parsing over data by AuthDTO`() {
        val fitStringData = """
            {
                "principal" : "success@success.com",
                "credential" : "1234",
                "mocking" : "mock"
            }
        """.trimIndent()

        val result = objectMapper.readValue<AuthDTO>(fitStringData)
        assert(result.principal == "success@success.com")
        assert(result.credential == "1234")
    }

    @Test
    fun `parsing invalid data by AuthDTO`() {
        val fitStringData = """
            {
                "credential" : "1234",
                "mocking" : "mock"
            }
        """.trimIndent()

        val result = objectMapper.readValue<AuthDTO>(fitStringData)
        assert(result.principal == "")
        assert(result.credential == "1234")
    }
}