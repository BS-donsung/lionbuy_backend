package com.ateam.lionbuy.controller

import com.ateam.lionbuy.security.dto.AuthDTO
import com.ateam.lionbuy.security.dto.CreateUserDTO
import com.ateam.lionbuy.security.service.UserServiceByLocal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class AuthController
@Autowired constructor(
    val userService : UserServiceByLocal
) {
    @GetMapping("/test")
    fun `test`() = "test"


    @PostMapping("/register")
    fun `register account`( createUserDTO: CreateUserDTO ) : ResponseEntity<String> {
        val result = userService.createUser(createUserDTO)
        if(result.isPresent) {
            return ResponseEntity.status(400).build<String>()
        } else {
            return ResponseEntity.status(201).build<String>()
        }


    }
    @PutMapping
    @PatchMapping
    fun `update user`( authentication : Authentication, createUserDTO: CreateUserDTO ) : ResponseEntity<String> =
        // fun `update user`( authentication : Authentication, createUserDTO: CreateUserDTO )  for product
        // checking auth info with dto
        if(!authentication.match(createUserDTO)) {
            ResponseEntity.status(400).build<String>()
        } else {
            val result = userService.updateUser(createUserDTO);
            if(result.isPresent)
                ResponseEntity.status(400).build<String>()
            else
                ResponseEntity.ok().build<String>()
        }

    @DeleteMapping
    fun `delete user`( authDTO: AuthDTO ) : ResponseEntity<String> {
        // token checking in filter 401
        val result = userService.deactivate(authDTO.principal, authDTO.credential)
        return if(result) {
                    ResponseEntity.ok().build<String>()
                } else {
                    ResponseEntity.status(401).build<String>()
                }

    }
}

fun Authentication.match( createUserDTO: CreateUserDTO ) : Boolean =
    if( this.principal == createUserDTO.principal )
        true
    else
        false