package com.ateam.lionbuy.security.filter

import com.ateam.lionbuy.security.dto.JWTAuthToken
import com.ateam.lionbuy.security.util.JWTUtil
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter


class JWTParsingFilter(
    val jwtUtil : JWTUtil,
    val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val cookie = request.getCookieFromRequest("access_token")
        if(cookie != null) {
            val parsingResultFromJWT = jwtUtil.parsingJWT(cookie.value)
            parsingResultFromJWT.ifPresent {
                it["username"]?.let {
                    SecurityContextHolder.getContext().authentication = JWTAuthToken(it, "", mutableListOf(), true)
                }
            }
        }
        filterChain.doFilter(request, response)
    }

    fun HttpServletRequest.getCookieFromRequest(cookieName : String ) : Cookie? =
        if(this.cookies == null)
            null
        else
            this.cookies.findLast { it.name == cookieName }
}