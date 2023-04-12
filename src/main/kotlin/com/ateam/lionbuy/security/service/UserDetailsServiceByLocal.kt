package com.ateam.lionbuy.security.service

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


//@Component
//class UserDetailsServiceByLocal : UserDetailsService {
//    val testRepository = listOf(User("james", "1234", emptyList()))
//
//    override fun loadUserByUsername(username: String): UserDetails {
//        val queryResult = testRepository.findLast { it.username == username }
//        return queryResult?.let { queryResult } ?: throw UsernameNotFoundException("User Not Found")
//    }
//
//    @Bean
//    fun passwordEncoder() : PasswordEncoder = NoOpPasswordEncoder.getInstance()
//}


@Component
class UserDetailsServiceByLocal
@Autowired constructor(
    val passwordEncoder : PasswordEncoder
): UserDetailsManager {

    val testRepository = mutableListOf<UserDetails>(User("james", "1234", emptyList()))

    override fun loadUserByUsername(username: String): UserDetails {
        val queryResult = testRepository.findLast { it.username == username }
        return queryResult?.let { queryResult } ?: throw UsernameNotFoundException("User Not Found")
    }

    override fun createUser(user: UserDetails) {
        testRepository.add(user)
    }

    override fun updateUser(user: UserDetails) {
        val idx = testRepository.indexOfLast { it.username == user.username }
        if(idx != -1)
            testRepository.set(idx, user)
    }

    override fun deleteUser(username: String) {
        val idx = testRepository.indexOfLast { it.username == username }
        if(idx != -1)
            testRepository.removeAt(idx)
    }

    override fun changePassword(oldPassword: String, newPassword: String) {
        val targetUserName = SecurityContextHolder.getContext().authentication.name
        testRepository.findLast { it.username == targetUserName }?.let {
            if(passwordEncoder.matches(it.password, oldPassword)) {
                val idx = testRepository.indexOfLast { it.username == targetUserName }
                testRepository.set(idx, User(it.username, passwordEncoder.encode(oldPassword), emptyList()))
            }
        }
    }

    override fun userExists(username: String): Boolean {
        val idx = testRepository.indexOfLast { it.username == username }
        return (idx != -1)
    }

}