package com.revature.thevault.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;

public class JWTUtility {

    private static final byte[] secret = Base64.getDecoder().decode("V1FrSlZd1CDC7XBh2f+sJaG6OW5gxC0LlTQestx8/wk");

    public static String generateJWT(int userId, String username) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("username", username)
                .claim("account", true)
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();
    }

    public static JWTInfo verifyUser(String token) {
        ObjectMapper objectMapper = new ObjectMapper();
        Jws<Claims> jws;
        try{
            String updateToken = token.replace("Bearer ", "");
            jws = Jwts.parserBuilder()
                    .require("account", true)
                    .setSigningKey(Keys.hmacShaKeyFor(secret))
                    .build()
                    .parseClaimsJws(updateToken);
            return objectMapper.convertValue(jws.getBody(), JWTInfo.class);
        }catch(JwtException e){
            e.printStackTrace();
            return null;
        }
    }
}
