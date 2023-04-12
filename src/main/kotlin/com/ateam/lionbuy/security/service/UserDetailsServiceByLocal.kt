package com.ateam.lionbuy.security.service

import com.ateam.lionbuy.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Component


@Component
class UserDetailsServiceByLocal
@Autowired constructor(
    val authRepository : UserRepository
): UserDetailsService {

    // Mocking
    val testRepository = mutableListOf<UserDetails>(User("yahvz01@gmail.com", "1234", emptyList()))
    override fun loadUserByUsername(username: String): UserDetails {
        val queryResult = testRepository.findLast { it.username == username }
        return queryResult?.let { queryResult } ?: throw UsernameNotFoundException("User Not Found")
    }
}