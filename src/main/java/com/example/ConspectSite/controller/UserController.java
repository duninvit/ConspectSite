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
    public UserAccountDTO getMe(@RequestParam(value = "id", required = false) String id){
        if(id == null){
            return userService.getMe();
        } else {
            return userService.getUserById(id);
        }
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public UserAccountDTO getUser(@RequestParam(value = "id", required = false) String id){
        if(id == null){
            return userService.getMe();
        } else {
            return userService.getUserById(id);
        }
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserAccount(@RequestBody UserAccountDTO user){
        userService.updateUserAccount(user);
    }


    @PostMapping("/me/update")
    @ResponseStatus(HttpStatus.OK)
    public CredentialsUniqueDTO updateMyAccount(@RequestBody UserAccountDTO userAccountDTO){
        CredentialsUniqueDTO credentialsUniqueDTO = authenticationService.isCredentialsUnique(userAccountDTO);
        if(credentialsUniqueDTO.isUsernameUnique()) {
            userService.updateUserAccount(userAccountDTO);
        }
        return credentialsUniqueDTO;
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserAccounts(@RequestBody UserAccountDTO user){
        userService.deleteUserAccount(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserAccountDTO> getAll(){
        return userService.getAllUsers();
}

}
