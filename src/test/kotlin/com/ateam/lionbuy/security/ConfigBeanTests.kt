package com.ateam.lionbuy.security

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

@SpringBootTest
class ConfigBeanTests
@Autowired constructor(
    val securityBuildConfig: SecurityBuildConfig
){
    @Test
    fun print_cookie() {
        val cookie = securityBuildConfig.generateSetCookieString(UsernamePasswordAuthenticationToken("james", "1234"))
        println(cookie)
    }
}