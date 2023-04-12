package com.ateam.lionbuy.security.dto

import org.springframework.security.core.Authentication

data class AuthDTO(
    val email : String = "",
    val password : String = ""
)

fun AuthDTO.toAuthentication() : Authentication =
    JWTAuthToken("", email, password, mutableListOf(), true)

