package com.ateam.lionbuy.security.service

import com.ateam.lionbuy.repository.UserRepository
import com.ateam.lionbuy.security.dto.AuthDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*


@Component
class UserDetailsServiceByLocal
@Autowired constructor(
    val authRepository : UserRepository
): UserDetailsService {

    // Mocking
    val testRepository = mutableListOf<UserDetails>(User("yahvz01@gmail.com", "1234", emptyList()))
    override fun loadUserByUsername(username: String): UserDetails =
        with(authRepository.getInfo(username)) {
            if(this.isEmpty)
                throw UsernameNotFoundException("User Not Found")
            else {
                val userInfoDTO = this.get()
                return User(userInfoDTO.useremail, userInfoDTO.userpw, emptyList())
            }
        }
}