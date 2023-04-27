package com.ateam.lionbuy.security

import com.ateam.lionbuy.security.util.JWTUtil
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SecurityUtilConfig {

    @Value("\${jwt.secretkey}")
    lateinit var APPLICATION_JWT_SECRET_KEY : String

    @Bean
    fun `inject ObjectMaper`() : ObjectMapper = ObjectMapper().also {
        it  .registerModule(JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    @Bean
    fun `inject JWTUtil`() : JWTUtil = JWTUtil(APPLICATION_JWT_SECRET_KEY)
}