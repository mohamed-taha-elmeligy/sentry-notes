package com.sentry.notes.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@Slf4j
public class JwtService {

    @Value("${security.jwt.secret.key}")
    private String secretKey;
    @Value("${security.jwt.expiration.token}")
    private int expirationToken;
    @Value("${security.jwt.refresh.token}")
    private int refreshToken;

    public String generateAccessToken(UserDetails userDetails){
        return null ;
    }

    private String generateToken(Map<String, GrantedAuthority> clims, String username,int expiration){
        String token =  Jwts.Builder().
    }
}
