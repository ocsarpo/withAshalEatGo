package kr.co.fastcampus.eatgo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    private Key key;
    public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(Long userId, String name) {

        return Jwts.builder()
                .claim("userId", userId)
                .claim("name", name)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
//        Jws: sign이 포함된 jwt
        return jwtParser.parseClaimsJws(token).getBody();
    }
}
