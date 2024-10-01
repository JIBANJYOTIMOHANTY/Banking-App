package com.jiban.Banking.services;

import java.util.*;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    private static final String SECRET = "ahsffbcbfguyrfyueoqrquwcbfggbglfqalfsgfbafbhafbgfaskfasajksfqwertyujhbgfdsazx";

    public String generateToken(String username){
        Map<String, Objects> claims = new HashMap<>();
        return Jwts.builder().
                    setClaims(claims).
                    setSubject(username).
                    setIssuedAt(new Date(System.currentTimeMillis())).
                    setExpiration(new Date(System.currentTimeMillis() + 1000 * 600 * 30)).
                    signWith(getSignInKey(),SignatureAlgorithm.HS256).
                    compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().
                    setSigningKey(getSignInKey()).
                    build().
                    parseClaimsJws(token).
                    getBody();
    }
    

    private Key getSignInKey(){
        byte [] bytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(bytes);
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) );
    }


    
}
