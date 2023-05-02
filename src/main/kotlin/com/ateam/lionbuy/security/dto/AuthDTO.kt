package com.ateam.lionbuy.security.dto

import org.springframework.security.core.Authentication

data class AuthDTO(
    val principal : String = "",
    val credential : String = ""
)

open class ResponseAuthDTO (
    val username : String,
    val principal : String
)

fun AuthDTO.toAuthentication() : Authentication =
    JWTAuthToken("", principal, credential, mutableListOf(), true)

data class CreateUserDTO(
    val username : String = "",
    val principal: String = "",
    val credential: String = ""
)