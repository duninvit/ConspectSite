package com.example.ConspectSite.controller;

import com.example.ConspectSite.exception.CredentialsNotUniqueException;
import com.example.ConspectSite.exception.VerificationTokenException;
import com.example.ConspectSite.model.User;
import com.example.ConspectSite.services.AuthenticationService;
import com.example.ConspectSite.services.UserService;
import com.example.ConspectSite.services.dto.CredentialsUniqueDTO;
import com.example.ConspectSite.services.dto.LoginRequestDTO;
import com.example.ConspectSite.services.dto.LoginResponseDTO;
import com.example.ConspectSite.services.dto.RegisterRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){
        return authenticationService.login(loginRequestDTO);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public CredentialsUniqueDTO register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) throws CredentialsNotUniqueException{
        CredentialsUniqueDTO credentialsUniqueDTO = authenticationService.isCredentialsUnique(registerRequestDTO);
        if(credentialsUniqueDTO.isCredentialsUnique()) {
            User user = userService.createUserAccount(registerRequestDTO);
            authenticationService.sendConfirmationMail(user);
        }
        return credentialsUniqueDTO;
    }

    @GetMapping("/register/confirm")
    public RedirectView confirmRegistration(@RequestParam("token") String token) throws VerificationTokenException{
        authenticationService.confirmUserAccount(token);
        return new RedirectView("http://localhost:4200");
    }

    @PostMapping("/isCredentialsUnique")
    public CredentialsUniqueDTO isCredentialsUnique(@RequestBody RegisterRequestDTO registerRequestDTO){
        return authenticationService.isCredentialsUnique(registerRequestDTO);
    }
}
