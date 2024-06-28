package pers.jamestang.springrbac.system.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.util.*


object JWTUtil {

    private const val SECRET_KEY = "FC93FD316952B4519CB11EA30467A90BE52035ACA41EED1A600A3F27892583D1"

    fun generateToken(subject: String, claims: Map<String, Any>): String {

        val now = Date()

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(now)
            .signWith(getSignKey())
            .compact()
    }


    fun validateToken(token: String): Boolean {
        return Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .body
            .expiration
            .after(Date())
    }


    fun getSubject(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }


    fun getClaims(token: String): Map<String, Any> {
        return Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun getSignKey(): Key{
        return Keys.hmacShaKeyFor(SECRET_KEY.toByteArray())
    }

}