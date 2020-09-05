package com.justa.challenge.config.security;

import com.justa.challenge.model.UserAuthor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TokenService {


    @Value("${challenge.jwt.expiration}")
    private String expiration;
    @Value("${challenge.jwt.secret}")
    private String secret;

    public String generationToken(Authentication authentication) {
        UserAuthor userLog = (UserAuthor) authentication.getPrincipal();
        Date today = new Date();
        Date expirationTime = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("Challenge Justa")
                .setSubject(userLog.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
