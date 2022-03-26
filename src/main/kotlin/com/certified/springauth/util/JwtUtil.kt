package com.certified.springauth.util

import com.certified.springauth.config.Config.SECRET_KEY
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.cglib.core.internal.Function
import org.springframework.security.core.userdetails.UserDetails
import java.security.Key
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object


class JwtUtil {

    fun extractAllClaims(token: String): Claims =
        Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).body

    fun <T> extractClaim(token: String, claimsResolver: Function<Claims, T>): T =
        claimsResolver.apply(extractAllClaims(token))

    fun extractUsername(token: String): String = extractClaim(token, Claims::getSubject)

    fun extractExpiration(token: String): Date = extractClaim(token, Claims::getExpiration)

    fun isTokenExpired(token: String): Boolean = extractExpiration(token).before(Date())

    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Object>()
        return createToken(claims, userDetails.username)
    }

    private fun createToken(claims: kotlin.collections.Map<String, Object>, subject: String?): String {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        val key: Key = Keys.hmacShaKeyFor(keyBytes)
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun validateToken(token: String, userDetails: UserDetails): Boolean =
        (extractUsername(token) == userDetails.username) and (!isTokenExpired(token))
}