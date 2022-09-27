package com.dev.clinic.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.clinic.dto.LoginDto;
import com.dev.clinic.dto.SignUpDto;
import com.dev.clinic.dto.UserDto;
import com.dev.clinic.model.User;
import com.dev.clinic.repository.UserRepository;
import com.dev.clinic.service.UserService;
import com.dev.clinic.util.CommonMethod;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("User signed-in successfully!");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        UserDto newUser = this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/curent-username")
    public ResponseEntity<String> getCurrentUserName() {
        String username = CommonMethod.getCurrentUsername();
        if (username != null) {
            return ResponseEntity.ok(username);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
