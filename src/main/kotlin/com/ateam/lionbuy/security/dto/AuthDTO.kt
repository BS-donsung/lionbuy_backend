package com.ateam.lionbuy.security.dto

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate

data class AuthDTO(
    val principal : String = "",
    val credential : String = ""
)

data class UsernameAndPrincipal(
    val username : String,
    val principal: String
)

fun AuthDTO.toAuthentication() : Authentication =
    JWTAuthToken("", principal, credential, mutableListOf(), true)

data class CreateUserDTO(
    val username : String,
    val principal: String,
    val credential: String,
    val gender : Boolean
)