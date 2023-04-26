package com.ateam.lionbuy.security.util

import io.jsonwebtoken.*
import java.security.Key
import java.util.*
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import kotlin.coroutines.EmptyCoroutineContext.fold

enum class JWTStatus {
    VALID, EXPIRED, INVALID
}

class JWTUtil(
    val secretKey : String,
    val algorithm : String = "HS512",
    val defaultExpiredMils : Long = 100 * 60 * 36
) {
    val parser : JwtParser by lazy { Jwts.parserBuilder().setSigningKey(this.secretKey.toByteArray()).build() }
    val jwtKey : SecretKey by lazy { SecretKeySpec(this.secretKey.toByteArray(),SignatureAlgorithm.HS512.jcaName ) }
    val BEARER_PREFIX = "Bearer"
    val BEARER_PREFIX_WITH_SPACE = BEARER_PREFIX + " "

    fun createJWT(claims: Map<String, String>, expireMils: Long = this.defaultExpiredMils ) : String =
        with(Jwts.builder()) {
            val header = Jwts.header()
            header["typ"] = "jwt"
            header["alg"] = algorithm

            this.setHeader(header)
                .setClaims(claims)
                .setSubject("access_token")
                .setIssuedAt( Date(System.currentTimeMillis()) )
                .setExpiration( Date(System.currentTimeMillis() + expireMils ) )


            this.signWith(jwtKey)
                .compact()
        }

    fun createBearerJWT(claims: Map<String, String>, expireMils: Long = this.defaultExpiredMils ) : String =
        BEARER_PREFIX_WITH_SPACE + this.createJWT(claims, expireMils)

    fun removeBearerPrefix( jwtString : String ) : Optional<String> =
        if(jwtString.startsWith("Bearer ")) {
            val splitToken = jwtString.split(BEARER_PREFIX_WITH_SPACE);
            if(splitToken.size != 2)
                Optional.empty()
            else
                Optional.of(jwtString.split(BEARER_PREFIX_WITH_SPACE)[1])
        } else
            Optional.empty()

    fun parsingJWT( jwtString : String ) : Optional<Map<String, String>> =
        with(this@JWTUtil.parser) {
            when(this@JWTUtil.isCheckState(jwtString)) {
                JWTStatus.INVALID -> Optional.empty()
                JWTStatus.EXPIRED -> Optional.empty()
                JWTStatus.VALID -> {
                    val claims = this@JWTUtil.parser.parseClaimsJws(jwtString)
                    val map = claims.body.asSequence().fold(mutableMapOf<String, String>()){ map, entry ->
                        map.put(entry.key, entry.value.toString())
                        map
                    }
                    Optional.of(map)
                }
            }
//            val claims = this.parseClaimsJws(jwtString)
        }

    fun isCheckState( jwtString: String ) : JWTStatus =
        //ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, io.jsonwebtoken.SignatureException, java.lang.IllegalArgumentException
        try {
            val claims = this@JWTUtil.parser.parseClaimsJws(jwtString)
            if(Date().before(claims.body.expiration))
                JWTStatus.VALID
            else
                JWTStatus.EXPIRED
        } catch ( expireEx : ExpiredJwtException ) {
            JWTStatus.EXPIRED
        } catch ( otherEx : Exception ) {
            JWTStatus.INVALID
        }

}