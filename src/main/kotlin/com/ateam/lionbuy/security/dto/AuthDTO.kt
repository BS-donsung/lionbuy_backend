package com.ateam.lionbuy.security.dto

import org.springframework.security.core.Authentication

data class AuthDTO(
    val principal : String = "",
    val credential : String = ""
)

fun AuthDTO.toAuthentication() : Authentication =
    JWTAuthToken("", principal, credential, mutableListOf(), true)

