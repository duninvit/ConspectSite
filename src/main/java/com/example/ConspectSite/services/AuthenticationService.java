package com.example.ConspectSite.services;

import com.example.ConspectSite.exception.VerificationTokenException;
import com.example.ConspectSite.model.User;
import com.example.ConspectSite.model.VerificationToken;
import com.example.ConspectSite.repository.UserRepository;
import com.example.ConspectSite.repository.VerificationTokenRepository;
import com.example.ConspectSite.security.JWTHelper;
import com.example.ConspectSite.security.models.UserAccountDetails;
import com.example.ConspectSite.services.dto.CredentialsUniqueDTO;
import com.example.ConspectSite.services.dto.LoginRequestDTO;
import com.example.ConspectSite.services.dto.LoginResponseDTO;
import com.example.ConspectSite.services.dto.UserAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Component
public class AuthenticationService {

    @Value("${application.domain}")
    private String applicationDomain;

    @Autowired
    private JWTHelper jwtHelper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        Authentication authResult = authenticationManager.authenticate(authRequest);
        if(authResult.isAuthenticated()){
            UserAccountDetails userDetails = (UserAccountDetails) authResult.getPrincipal();
            return new LoginResponseDTO(jwtHelper.createToken(userDetails.getId()));
        } else {
            throw new AuthenticationCredentialsNotFoundException("Athentication failed.");
        }
    }

    public CredentialsUniqueDTO isCredentialsUnique(UserAccountDTO userAccountDTO){
        CredentialsUniqueDTO credentialsUniqueDTO = new CredentialsUniqueDTO();
        credentialsUniqueDTO.setEmailUnique(!emailExists(userAccountDTO.getEmail()));
        credentialsUniqueDTO.setUsernameUnique(!usernameExists(userAccountDTO.getUsername()));
        credentialsUniqueDTO.setCredentialsUnique(credentialsUniqueDTO.isEmailUnique() && credentialsUniqueDTO.isUsernameUnique());
        return credentialsUniqueDTO;
    }

    private boolean emailExists(String email){
        return userRepository.findByEmail(email) != null;
    }

    private boolean usernameExists(String username){
        return userRepository.findByUsername(username) != null;
    }

    public void sendConfirmationMail(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("${spring.mail.username}");
        mail.setTo(user.getEmail());
        mail.setSubject("Registration confirmation");
        mail.setText(applicationDomain + "register/confirm?token=" + token);
        mailSender.send(mail);
    }

    public void confirmUserAccount(String token) throws VerificationTokenException {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken == null)
            throw new VerificationTokenException("Token not found");
        if(verificationToken.getExpiryDate().getTime() < new Date().getTime())
            throw new VerificationTokenException("Token expired");
        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        verificationTokenRepository.delete(verificationToken);
    }
}
