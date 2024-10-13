package com.blogapp.controller;

import com.blogapp.entity.User;
import com.blogapp.payload.LoginDto;
import com.blogapp.payload.Singup;
import com.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //http://localhost:8080/api/auth/sign-up
    @PostMapping("/sign-up")
    public ResponseEntity<String> createUser(@RequestBody Singup signUp){
        if(userRepository.existsByEmail(signUp.getEmail())){
            return new ResponseEntity<>("Email id is already registered", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        if(userRepository.existsByUsername(signUp.getEmail())){
            return new ResponseEntity<>("User name is already registered",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User user = new User();
        user.setName(signUp.getName());
        user.setUsername(signUp.getUsername());
        user.setEmail(signUp.getEmail());
        user.setPassword(passwordEncoder.encode(signUp.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("User is registered",HttpStatus.CREATED);


    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody LoginDto loginDto){
        //what this will do?
        //1)supply logindto.getUsername()-username to loadByUser method in customUserDetails
        //2)It will compare
        //Expected Credentials-loginDto.getUsername().loginDto.getPassword()
        //with Actual credential given by loadByUser methhod
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
       //similar to session variable
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return  new ResponseEntity<>("Sign in successfull",HttpStatus.OK);

    }
}
