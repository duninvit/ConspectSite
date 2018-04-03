package com.example.ConspectSite.controller;

import com.example.ConspectSite.services.AuthenticationService;
import com.example.ConspectSite.services.UserService;
import com.example.ConspectSite.services.dto.CredentialsUniqueDTO;
import com.example.ConspectSite.services.dto.UserAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserAccountDTO getMe(@RequestParam(value = "email", required = false) String email){
        if(email == null){
            return userService.getMe();
        } else {
            return userService.getUserByEmail(email);
        }
    }

    @PostMapping("/me/update")
    @ResponseStatus(HttpStatus.OK)
    public CredentialsUniqueDTO updateUserAccount(@RequestBody UserAccountDTO userAccountDTO){
        CredentialsUniqueDTO credentialsUniqueDTO = authenticationService.isCredentialsUnique(userAccountDTO);
        if(credentialsUniqueDTO.isUsernameUnique()) {
            userService.updateUserAccounts(userAccountDTO);
        }
        return credentialsUniqueDTO;
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserAccounts(@RequestBody UserAccountDTO[] users){
        userService.updateUserAccounts(users);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserAccounts(@RequestBody UserAccountDTO[] users){
        userService.deleteUserAccounts(users);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserAccountDTO> getAll(){
        return userService.getAllUsers();
}

}
