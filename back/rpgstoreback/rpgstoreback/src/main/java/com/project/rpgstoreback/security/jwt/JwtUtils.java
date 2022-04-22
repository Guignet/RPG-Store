package com.project.rpgstoreback.security.jwt;


import io.jsonwebtoken.*;
import com.project.rpgstoreback.models.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.utils.secret}")
    private String jwtSecret;
    @Value("${jwt.utils.expiration.duration}")
    private long expirationMs;

    public String generateJwtToken(Authentication authentication){
        Account account = (Account) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(account.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+this.expirationMs))
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            System.err.println("Invalid JWT signature: {} " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token: {} " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("JWT token is expired: {} " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("JWT token is unsupported: {} " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("JWT claims string is empty: {} " + e.getMessage());
        }
        return false;

    }
    public String getUsernameFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}