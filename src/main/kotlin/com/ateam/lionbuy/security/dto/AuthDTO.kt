package com.ateam.lionbuy.security.dto

import org.springframework.security.core.Authentication

data class AuthDTO(
    val username : String = "",
    val password : String = ""
)

fun AuthDTO.toAuthentication() : Authentication =
    JWTAuthToken(username, password, mutableListOf(), true)

