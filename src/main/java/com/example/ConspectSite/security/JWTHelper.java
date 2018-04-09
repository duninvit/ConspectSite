package com.example.ConspectSite.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.ConspectSite.exception.InvalidTokenAuthenticationException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

import static com.example.ConspectSite.model.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.example.ConspectSite.model.Constants.SIGNING_KEY;

@Component
public class JWTHelper {

    static final String USER_ID_CLAIM = "user_id";

    public String createToken(Long userId){
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SIGNING_KEY);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim(USER_ID_CLAIM, userId)
                    .withClaim("expires_at", new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                    .sign(algorithm);
        } catch (UnsupportedEncodingException exception){
            System.out.println(exception.getMessage());
        }
        return token;
    }

    public void verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SIGNING_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (UnsupportedEncodingException exception){
            System.out.println(exception.getMessage());
        } catch (JWTVerificationException exception){
            throw new InvalidTokenAuthenticationException("Token signature verification failed.", exception);
        }
    }

    public HashMap<String, Object> decodeToken(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return getClaims(jwt);
        } catch (JWTDecodeException exception){
            throw new InvalidTokenAuthenticationException("Token decode failed.", exception);
        }
    }

    private HashMap<String, Object> getClaims(DecodedJWT jwt){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(USER_ID_CLAIM, jwt.getClaim(USER_ID_CLAIM).asLong());
        return claims;
    }
}
