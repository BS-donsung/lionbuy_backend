package com.ateam.lionbuy.security.dto

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class JWTAuthToken(
    val username : String,
    val password : String,
    val authorities : MutableList<String> = mutableListOf(),
    var hasAuthenticated : Boolean = false
) : Authentication {

    override fun getName(): String = this.username

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        this.authorities.map { SimpleGrantedAuthority(it) }.toMutableList()

    override fun getCredentials(): Any = this.password

    override fun getDetails(): Any = Unit

    override fun getPrincipal(): Any = this.username

    override fun isAuthenticated(): Boolean = this.hasAuthenticated

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.hasAuthenticated = isAuthenticated
    }
}