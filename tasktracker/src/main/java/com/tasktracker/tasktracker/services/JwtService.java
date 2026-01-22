package com.tasktracker.tasktracker.services;

import com.tasktracker.tasktracker.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY = "Y3Jvd2RvdXJzZWx2ZXN3aGljaHplYnJhbG9zdHRoaXN3aGVyZXJlcG9ydGZpZXJjZXc=";

    public String generateToken(CustomUserDetails userDetails){

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String role = authorities.stream().map(GrantedAuthority::getAuthority).findFirst().orElse(null);


        return Jwts
                .builder()
                .subject(userDetails.getUsername())
                .signWith(getSignKey(SECRET_KEY))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .claim("role",role)
                .compact();
    }


    public SecretKey getSignKey(String secretKey){
        byte[] decode = Decoders.BASE64.decode(secretKey);
        SecretKey secretKey1 = Keys.hmacShaKeyFor(decode);
        return secretKey1;
    }

    public boolean validateToken(String token, CustomUserDetails userDetails){
        try {
            Claims claims = Jwts
                    .parser()
                    .verifyWith(getSignKey(SECRET_KEY))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return userDetails.getUsername().equalsIgnoreCase(claims.getSubject()) && !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserNameFromToken(String token){
        return Jwts
                .parser()
                .verifyWith(getSignKey(SECRET_KEY))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


}
