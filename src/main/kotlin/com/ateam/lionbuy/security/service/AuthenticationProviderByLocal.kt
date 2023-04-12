package com.ateam.lionbuy.security.service

import com.ateam.lionbuy.security.dto.JWTAuthToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AuthenticationProviderByLocal
@Autowired constructor(
    val userDetailsServiceByLocal: UserDetailsServiceByLocal,
    val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication =
        with(userDetailsServiceByLocal.loadUserByUsername(authentication.name)) {
            if(passwordEncoder.matches(authentication.credentials.toString(), this.password))
                JWTAuthToken(this.username, this.password, mutableListOf(), true)
            else
                throw BadCredentialsException("password not matched")
        }

    override fun supports(authentication: Class<*>?): Boolean =
        JWTAuthToken::class.java.isAssignableFrom(authentication)


}