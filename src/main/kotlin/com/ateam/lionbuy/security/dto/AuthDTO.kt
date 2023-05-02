package com.ateam.lionbuy.security.dto

import org.springframework.security.core.Authentication

open class AuthDTO(
    val principal : String = "",
    val credential : String = ""
)

open class ResponseAuthDTO (
    val username : String,
    val principal : String
)

fun AuthDTO.toAuthentication() : Authentication =
    JWTAuthToken("", principal, credential, mutableListOf(), true)

open class CreateUserDTO(
    val username : String,
    principal: String,
    credential: String
) : AuthDTO(principal, credential)