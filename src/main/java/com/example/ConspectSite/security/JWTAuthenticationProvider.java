package com.example.ConspectSite.security;

import com.example.ConspectSite.exception.InvalidTokenAuthenticationException;
import com.example.ConspectSite.model.User;
import com.example.ConspectSite.repository.UserRepository;
import com.example.ConspectSite.security.models.JWTAuthentication;
import com.example.ConspectSite.security.models.UserAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JWTHelper jwtHelper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authRequest) throws AuthenticationException {
        String token = (String) authRequest.getCredentials();
        Long userId = (Long) jwtHelper.decodeToken(token).get(JWTHelper.USER_ID_CLAIM);
        User user = userRepository.findById(userId).orElse(new User());
        if(user == null) {
            throw new InvalidTokenAuthenticationException("Token does not contain existed user id.");
        }
        UserAccountDetails userDetails = new UserAccountDetails(user);
        if(!userDetails.isAccountNonLocked()) {
            throw new LockedException("User account is locked");
        }
        return new JWTAuthentication(userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthentication.class.isAssignableFrom(authentication);
    }
}
