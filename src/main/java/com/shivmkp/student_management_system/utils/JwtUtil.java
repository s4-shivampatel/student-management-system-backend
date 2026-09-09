package com.shivmkp.student_management_system.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class JwtUtil {

    private String SECRET_KEY="vv4)]>>Toxc8T1M!71r?}bR%%1K-t?]<vD#9(6OeS4&";

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    public String generateToken(String studentId){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,studentId);
    }

    private String createToken(Map<String, Object> claims, String studentId) {
        return Jwts.builder()
                //<-----HEADER----->
                .header().empty().add("typ","JWT")
                .and()
                //<-----PAYLOAD----->
                .claims(claims)
                .subject(studentId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+(1000*60*60*24)))
                //<-----SIGNATURE----->
                .signWith(getSigningKey())
                .compact();
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String extractStudentId(String token){
        Claims claims=extractAllClaims(token);
        return claims.getSubject();
    }
    public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }
    public Boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }
    public Boolean validateToken(String token){
        return !isTokenExpired(token);
    }
}
