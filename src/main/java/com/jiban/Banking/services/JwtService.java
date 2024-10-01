package com.jiban.Banking.services;

import java.util.*;
import org.springframework.stereotype.Component;

import java.security.Key;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    private static final String SECRET = "ahsffbcbfguyrfyueoqrquwcbfggbglfqalfsgfbafbhafbgfaskfasajksfqwertyujhbgfdsazx";

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().
                setClaims(claims).
                setSubject(username).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)).
                signWith(getSignInKey(),SignatureAlgorithm.HS256).
                compact();
            
    }

    private Key getSignInKey(){
        byte [] bytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(bytes);
    }
    
    

    
}
