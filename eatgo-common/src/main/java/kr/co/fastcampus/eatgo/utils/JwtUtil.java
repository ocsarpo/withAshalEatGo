package kr.co.fastcampus.eatgo.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {
    public String createToken(Long userId, String name) {
//      키는  256 / 8 = 32 글자 이상..
        String secret = "12345678901234567890123456789012";

        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        String token = Jwts.builder()
                .claim("userId", userId)
                .claim("name", name)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }
}
