package com.tabdelbari.jwtdemo.controller;

import com.tabdelbari.jwtdemo.dto.AuthenticationRequest;
import com.tabdelbari.jwtdemo.dto.AuthenticationResponse;
import com.tabdelbari.jwtdemo.entity.Role;
import com.tabdelbari.jwtdemo.entity.User;
import com.tabdelbari.jwtdemo.service.UserService;
import com.tabdelbari.jwtdemo.service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {


    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtUtil jwtUtil;
    private PasswordEncoder passEncoder;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passEncoder = passEncoder;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }catch(BadCredentialsException e) {
            throw new Exception("Invalid Username or Password !!");
        }catch( Exception e){
            e.printStackTrace();
        }
        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody AuthenticationRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passEncoder.encode(request.getPassword()));

        // user.setEnabled(true);
        // user.setAccountNonExpired(true);
        // user.setAccountNonLocked(true);
        // user.setCredentialsNonExpired(true);
        user.setRoles(Arrays.asList(new Role("ADMIN"), new Role("USER"))); // for test only

        return ResponseEntity.ok(userService.save(user));
    }

    @RequestMapping(value = "/who", method = RequestMethod.GET)
    public ResponseEntity<User> who(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth!=null?ResponseEntity.ok(userService.loadUserByUsername(auth.getName())):ResponseEntity.ok(null);
    }
}
