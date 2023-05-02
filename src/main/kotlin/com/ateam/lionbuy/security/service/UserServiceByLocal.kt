package com.ateam.lionbuy.security.service;

import com.ateam.lionbuy.entity.UserInfo
import com.ateam.lionbuy.repository.UserRepository
import com.ateam.lionbuy.security.dto.CreateUserDTO
import com.ateam.lionbuy.security.dto.ResponseAuthDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceByLocal
@Autowired constructor( val userRepository: UserRepository ){

    fun register( createUserDTO: CreateUserDTO ) : Optional<ResponseAuthDTO> =
        with(userRepository.getInfo(createUserDTO.principal)) {
            if(this.isPresent)
                Optional.empty()
            else {
                val savedResult = userRepository.save(createUserDTO.toEntity());
                Optional.of(savedResult.toResponseAuthDTO())
            }
        }
}

fun CreateUserDTO.toEntity(): UserInfo {
    return UserInfo(null, this.username, this.principal, this.credential)
}

fun UserInfo.toResponseAuthDTO() : ResponseAuthDTO =
    ResponseAuthDTO(this.username, this.useremail)
