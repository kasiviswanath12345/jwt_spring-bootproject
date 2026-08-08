//package com.example.fusion.controller;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.fusion.model.User;
//import com.example.fusion.repository.UserRepository;
//import com.example.fusion.security.JwtUtil;
//import com.example.fusion.service.UserService;
//
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
// /*  @PostMapping("/register")
//    public String register(@RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userService.save(user);
//        return "User registered!";
//    }
//    */
//    
//   @PostMapping("/register")
//   public ResponseEntity<String> register(@RequestBody User user) {
//       if (userRepository.existsByUsername(user.getUsername())) {
//           return ResponseEntity.status(HttpStatus.CONFLICT)
//                   .body("Error: Username is already taken!");
//       }
//
//       user.setPassword(passwordEncoder.encode(user.getPassword()));
//       userService.save(user);
//       return ResponseEntity.ok("User registered!");
//   }
//
//
//    
///* @PostMapping("/login")
//    public String login(@RequestBody User user) {
//        authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        var dbUser = userService.loadUserByUsername(user.getUsername());
//        var role = dbUser.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
//        return jwtUtil.generateToken(user.getUsername(), role);
//    }
//    */
//    
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        try {
//            authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//            );
//
//            var dbUser = userService.loadUserByUsername(user.getUsername());
//            var role = dbUser.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
//
//            String token = jwtUtil.generateToken(user.getUsername(), role);
//            return ResponseEntity.ok().body(Map.of("token", token));
//
//        } catch (BadCredentialsException ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(Map.of("error", "Invalid username or password. If you're new, please register."));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("error", "Something went wrong during login."));
//        }
//    }
//}
//
//
//
package com.example.fusion.controller;

import java.util.Map;

import com.example.fusion.model.User;
import com.example.fusion.repository.UserRepository;
import com.example.fusion.security.JwtUtil;
import com.example.fusion.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register endpoint with validation
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: Username is already taken!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return ResponseEntity.ok("User registered!");
    }

    // Login endpoint with validation
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user) {
        try {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            var dbUser = userService.loadUserByUsername(user.getUsername());
            var role = dbUser.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

            String token = jwtUtil.generateToken(user.getUsername(), role);
            return ResponseEntity.ok().body(Map.of("token", token));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid username or password."));
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "User not found."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Something went wrong during login."));
        }
    }
}