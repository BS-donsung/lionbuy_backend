package com.ateam.lionbuy.security.util

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class JWTUtilsTests{

    val logger: Logger = LoggerFactory.getLogger(JWTUtilsTests::class.java)

    @Autowired
    lateinit var jwtUtil : JWTUtil

    @Test
    fun `TEST createJWT()`() {
        val valid_token = jwtUtil.createJWT(
            mapOf( "principal" to "success@success.com", "roles" to "user" )
        )
        this.logger.info("created valid_token : ${valid_token}")
    }

    @Test
    fun `TEST createBearerJWT`() {
        val valid_token = jwtUtil.createBearerJWT(
            mapOf( "principal" to "success@success.com", "roles" to "user" )
        )
        assert( valid_token.startsWith("Bearer ", false) )
    }

    @Test
    fun `TEST isCheckState() valid`() {
        val valid_token = jwtUtil.createJWT(
            mapOf( "principal" to "success@success.com", "roles" to "user" )
        )
        assert(jwtUtil.isCheckState(valid_token) == JWTStatus.VALID)
    }

    @Test
    fun `TEST isCheckState() expired`() {
        val expired_token = jwtUtil.createJWT(
            mapOf( "principal" to "success@success.com", "roles" to "user" ), -1000L
        )
        assert(jwtUtil.isCheckState(expired_token) == JWTStatus.EXPIRED)
    }

    @Test
    fun `TEST isCheckState() invalid`() {
        val invalid_token = jwtUtil.createJWT(
            mapOf( "principal" to "success@success.com", "roles" to "user" )
        ) + "sort"
        assert(jwtUtil.isCheckState(invalid_token) == JWTStatus.INVALID)
    }

    @Test
    fun `TEST parsingJWT() success`() {
        val valid_token = jwtUtil.createJWT(
            mapOf( "principal" to "success@success.com", "roles" to "user" )
        )
        val parsedData = jwtUtil.parsingJWT(valid_token)
        assert(parsedData.isPresent)
        val claimsMap = parsedData.get()
        assert(claimsMap.get("principal") == "success@success.com")
        assert(claimsMap.get("roles") == "user")
    }

    @Test
    fun `TEST parsingJWT() failure`() {
        val bearer_valid_token = jwtUtil.createBearerJWT(
            mapOf( "principal" to "success@success.com", "roles" to "user" )
        )
        val parseData = jwtUtil.parsingJWT(bearer_valid_token)
        assert(parseData.isEmpty)
    }
}