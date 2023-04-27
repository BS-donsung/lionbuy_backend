package com.ateam.lionbuy.security.service

import com.ateam.lionbuy.entity.UserInfo
import com.ateam.lionbuy.repository.UserRepository
import com.ateam.lionbuy.security.dto.AuthDTO
import com.ateam.lionbuy.security.dto.CreateUserDTO
import com.ateam.lionbuy.security.dto.UsernameAndPrincipal
import jakarta.persistence.Column
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class UserServiceByLocal
@Autowired constructor(
    val repository: UserRepository
) {

    fun createUser( createUserDTO: CreateUserDTO ) : Result<UsernameAndPrincipal> {
        val isExisted = repository.existsByUseremail(createUserDTO.principal)
        if(isExisted) {
            return Result.failure(DuplicateKeyException("this email is already registered"))
        } else {
            val result = repository.save( createUserDTO.toUserInfoEntity() )
            return Result.success(result.toUsernameAndPrincipal());
        }
    }

    fun updateUser( id : Long, createUserDTO: CreateUserDTO ) : Result<UsernameAndPrincipal> =
        with(repository.findById(id)) {
            return if(this.isEmpty) {
                Result.failure<UsernameAndPrincipal>(RuntimeException("User not found with id $id"))
            } else {
                val user = this.get()
                user.updateByCreateUserDTO(createUserDTO)
                val result = repository.save(user)
                Result.success<UsernameAndPrincipal>(result.toUsernameAndPrincipal())
            }
        }
}

fun CreateUserDTO.toUserInfoEntity() =
    UserInfo.builder()
        .username(this.username)
        .useremail(this.principal)
        .userpw(this.credential)
        .build()

fun UserInfo.toUsernameAndPrincipal() =
    UsernameAndPrincipal(this.username, this.useremail)

fun UserInfo.updateByCreateUserDTO( dto : CreateUserDTO ) : UserInfo =
    with(this) {
        this.username = dto.username
        this.useremail = dto.principal
        this.userpw = dto.credential
        this
    }
