package com.example.ConspectSite.services;

import com.example.ConspectSite.exception.CredentialsNotUniqueException;
import com.example.ConspectSite.model.Role;
import com.example.ConspectSite.model.User;
import com.example.ConspectSite.repository.UserRepository;
import com.example.ConspectSite.security.models.UserAccountDetails;
import com.example.ConspectSite.services.dto.LoginRequestDTO;
import com.example.ConspectSite.services.dto.RegisterRequestDTO;
import com.example.ConspectSite.services.dto.UserAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public UserAccountDTO getMe() {
        UserAccountDetails userDetails = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userDetails.getId()).orElse(new User());
        return new UserAccountDTO(user);
    }

    public UserAccountDTO getUserById(String id) {
        User user = userRepository.findById(Long.parseLong(id)).orElse(new User());
        return new LoginRequestDTO(user);
    }

    public User createUserAccount(RegisterRequestDTO registerRequestDTO) throws CredentialsNotUniqueException {
        if (!authenticationService.isCredentialsUnique(registerRequestDTO).isCredentialsUnique())
            throw new CredentialsNotUniqueException("Credentials not unique");
        User user = new User();
        user.setEmail(registerRequestDTO.getEmail());
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setUserRole(Role.ROLE_USER);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void updateUserAccount(UserAccountDTO userAccountDTO) {
        User user = userRepository.findByEmail(userAccountDTO.getEmail());
        Optional.ofNullable(userAccountDTO.getUsername()).ifPresent(user::setUsername);
        Optional.ofNullable(userAccountDTO.getRole()).ifPresent(user::setUserRole);
        user.setNonBlocked(!userAccountDTO.isBlocked());
        userRepository.save(user);
    }

    public List<UserAccountDTO> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(UserAccountDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserAccount(UserAccountDTO userAccountDTO) {
        User user = userRepository.findByUsername(userAccountDTO.getUsername());
        userRepository.delete(user);
    }

    User findUser(String email) {
        if (email == null) {
            Long id = ((UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            return userRepository.findById(id).orElse(new User());
        }
        return userRepository.findByEmail(email);
    }
}
