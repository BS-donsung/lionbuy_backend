package com.ateam.lionbuy.controller

import com.ateam.lionbuy.security.dto.AuthDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class AuthController {

//    @PostMapping("/register")
//    fun `register account`( authDTO: AuthDTO ) {
//
//    }
    @GetMapping("/test")
    fun `test`() = "test"
}