package com.ateam.lionbuy.controller

import com.ateam.lionbuy.security.dto.AuthDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class AuthController {

    @PostMapping("/register")
    fun `register account`( authDTO: AuthDTO ) {

    }
    @GetMapping("/test")
    fun `test`() = "test"

    @PutMapping
    @PatchMapping
    fun `example`() = ResponseEntity.ok().build<String>()
}