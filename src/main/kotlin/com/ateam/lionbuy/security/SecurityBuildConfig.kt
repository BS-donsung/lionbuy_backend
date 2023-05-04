package com.ateam.lionbuy.security

import com.ateam.lionbuy.security.filter.JWTAuthenticationFilter
import com.ateam.lionbuy.security.filter.JWTParsingFilter
import com.ateam.lionbuy.security.service.AuthenticationProviderByLocal
import com.ateam.lionbuy.security.util.JWTUtil
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.Cookie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseCookie.ResponseCookieBuilder
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityBuildConfig
@Autowired constructor(
    val jwtUtil: JWTUtil
){
    @Bean
    fun `inject AuthenticationManager`(
        authenticationProviderByLocal: AuthenticationProviderByLocal
    ) : AuthenticationManager =
        with(ProviderManager(
            listOf(authenticationProviderByLocal)
        )) {
            this
        }

    @Bean
    fun `inject passwordEncoder`() : PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun `inject TokenFilter`(jwtUtil: JWTUtil, objectMapper: ObjectMapper) : JWTParsingFilter =
        JWTParsingFilter(jwtUtil, objectMapper)

    @Bean
    fun `inject JWTAuthFIlter`( authenticationManager : AuthenticationManager, objectMapper: ObjectMapper ) : JWTAuthenticationFilter =
        with(JWTAuthenticationFilter(objectMapper)) {
            this.setAuthenticationManager(authenticationManager)
            this.setAuthenticationSuccessHandler { _, response, authentication ->
                response.status = 200
                val generatedJWTCookie = generateSetCookieString(authentication)
                response.addCookie(generatedJWTCookie)
                val body : Map<String, String> = mapOf( "status" to "success", "message" to "Authentication successful : ${authentication.name}")
                objectMapper.writeValue(response.writer, body)
            }
            this.setAuthenticationFailureHandler { _, response, exception ->
                response.status = 401
                val body : Map<String, String> = mapOf( "status" to "failure", "message" to "Authentication failure : ${exception.message}")
                objectMapper.writeValue(response.writer, body)
            }
            this
        }

    fun generateSetCookieString( authentication : Authentication ) : Cookie {
//        val bearerJWTToken = jwtUtil.createBearerJWT( mapOf( "username" to authentication.name ) )
        val jwtToken = jwtUtil.createJWT( mapOf( "username" to authentication.name ) )
        return with(Cookie("access_token", jwtToken)) {
            this.maxAge = (jwtUtil.defaultExpiredMils / 100).toInt()
            this.isHttpOnly = true
            this.secure= true
            this
        }
    }

    fun generateResponseCookie( username : String ) : ResponseCookie {
        val jwtToken = jwtUtil.createJWT( mapOf( "username" to username ) )
        return ResponseCookie.from("access_token", jwtToken)
            .maxAge( (jwtUtil.defaultExpiredMils / 100) )
            .httpOnly(true)
            .secure(true)
            .build()
    }
}