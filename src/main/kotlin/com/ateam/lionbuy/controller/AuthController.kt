package com.ateam.lionbuy.controller

import com.ateam.lionbuy.dto.UserDTO
import com.ateam.lionbuy.entity.UserInfo
import com.ateam.lionbuy.repository.UserRepository
import com.ateam.lionbuy.security.SecurityBuildConfig
import com.ateam.lionbuy.security.dto.AuthDTO
import com.ateam.lionbuy.security.dto.CreateUserDTO
import com.ateam.lionbuy.security.dto.ResponseAuthDTO
import com.ateam.lionbuy.security.service.UserServiceByLocal
import com.ateam.lionbuy.security.util.JWTUtil
import com.ateam.lionbuy.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class AuthController
@Autowired constructor(
    val userServiceByLocal: UserServiceByLocal,
    val securityConfig : SecurityBuildConfig
){
    @GetMapping("/auth")
    fun `test01`() = "auth";
    @GetMapping("/auth/test")
    fun `test02`() = "test";

    @PostMapping(value = arrayOf("/register", "/auth/register"))
    fun `register account`(@RequestBody dto : CreateUserDTO ) : ResponseEntity<ResponseAuthDTO> {
        val opt = userServiceByLocal.register(dto)
        return if(opt.isEmpty) {
            ResponseEntity.status(409).build<ResponseAuthDTO>()
        } else {
            val userDTO = opt.get()
            val cookie = securityConfig.generateResponseCookie(userDTO.username)
            val header = HttpHeaders().apply {
                this.add(HttpHeaders.SET_COOKIE, cookie.toString())
            }
            ResponseEntity.status(201).headers(header).body( userDTO )
        }
    }

    @PutMapping
    @PatchMapping
    fun `example`() = ResponseEntity.ok().build<String>()
}
