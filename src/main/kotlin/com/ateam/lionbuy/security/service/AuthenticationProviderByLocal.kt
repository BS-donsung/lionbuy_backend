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
    override fun authenticate(authentication: Authentication): Authentication {
        val principal = authentication.principal.toString()
        val credentials = authentication.credentials.toString()

        val queryResult = userDetailsServiceByLocal.loadUserByUsername(principal)

        return  if( passwordEncoder.matches(credentials, queryResult.password) )
                    JWTAuthToken(queryResult.username, principal, credentials, mutableListOf(), true)
                else
                    throw BadCredentialsException("password not matched")
    }

    override fun supports(authentication: Class<*>?): Boolean =
        JWTAuthToken::class.java.isAssignableFrom(authentication)


}