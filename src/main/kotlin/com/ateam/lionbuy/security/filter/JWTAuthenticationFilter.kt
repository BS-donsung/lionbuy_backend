package com.ateam.lionbuy.security.filter

import com.ateam.lionbuy.security.dto.AuthDTO
import com.ateam.lionbuy.security.dto.JWTAuthToken
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher
import org.springframework.util.StreamUtils
import java.nio.charset.Charset

class JWTAuthenticationFilter(
    val objectMapper: ObjectMapper
): AbstractAuthenticationProcessingFilter(
    OrRequestMatcher(
        AntPathRequestMatcher("/login"),
        AntPathRequestMatcher("/auth/login") )
) {
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val parsedData = objectMapper.readValue<AuthDTO>(request.getBody())

        return authenticationManager.authenticate(parsedData.toAuthentication())
    }

    private fun HttpServletRequest.getBody() : String = StreamUtils.copyToString(this.inputStream, Charset.defaultCharset())

    private fun AuthDTO.toAuthentication() : Authentication =
        JWTAuthToken("", this.principal, this.credential)
}
