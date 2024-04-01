package com.wanderhungerbuhler.microwh.controller;

import com.wanderhungerbuhler.microwh.domain.user.User;
import com.wanderhungerbuhler.microwh.dto.LoginRequestDTO;
import com.wanderhungerbuhler.microwh.dto.RegisterRequestDTO;
import com.wanderhungerbuhler.microwh.dto.ResponseDTO;
import com.wanderhungerbuhler.microwh.infra.security.TokenService;
import com.wanderhungerbuhler.microwh.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping(path = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity login(@RequestParam MultiValueMap<String, String> formData, LoginRequestDTO body) {
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);

            if (token != null) {
                ResponseDTO responseDTO = new ResponseDTO(user.getName(), token);
                String redirectUrl = "/create";

                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(URI.create(redirectUrl));

                return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(responseDTO);
            } else {
                return ResponseEntity.badRequest().header(HttpHeaders.LOCATION, "/signup").build();
            }
        }
        return ResponseEntity.badRequest().header(HttpHeaders.LOCATION, "/").build();
    }

    @PostMapping(path = "/register", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity register(@RequestParam MultiValueMap<String, String> formData, RegisterRequestDTO body) throws Exception {
        Optional<User> user = this.repository.findByEmail(body.email());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            if (token != null) {
                ResponseDTO responseDTO = new ResponseDTO(newUser.getName(), token);
                String redirectUrl = "/signin";

                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(URI.create(redirectUrl));

                return ResponseEntity.status(HttpStatus.FOUND).headers(headers).body(responseDTO);
            } else {
                return ResponseEntity.badRequest().header(HttpHeaders.LOCATION, "/signup").build();
            }
        }
        return ResponseEntity.badRequest().header(HttpHeaders.LOCATION, "/signup").build();
    }

}
