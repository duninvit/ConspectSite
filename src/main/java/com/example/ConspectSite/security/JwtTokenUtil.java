package com.devglan.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.impl.JWTParser;
import com.devglan.model.Role;
import com.devglan.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import static com.devglan.model.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.devglan.model.Constants.SIGNING_KEY;

@Component
public class JwtTokenUtil implements Serializable {

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getUsernameFromAuth0Token(String token) {
        return decodeToken(token).get("sub").toString();
    }

    private HashMap decodeToken(String token){
        Jwt jwtToken = JwtHelper.decode(token);
        String claims = jwtToken.getClaims();
        HashMap claimsMap = null;
        try {
            claimsMap = new ObjectMapper().readValue(claims, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return claimsMap;
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        String expiration = decodeToken(token).get("exp").toString();
        String curentDate = new Date().toString();
        return expiration.compareTo(curentDate) > 0;
    }

    public String generateToken(User user) {
        return doGenerateToken(user.getUsername(), user.getRole());
    }

    private String doGenerateToken(String subject, Role role) {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority(role.name())));
        System.out.println("__________++++++++++"+claims.get("scopes"));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("ConspectSite")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromAuth0Token(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }

}
