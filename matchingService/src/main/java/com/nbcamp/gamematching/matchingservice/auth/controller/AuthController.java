package com.nbcamp.gamematching.matchingservice.auth.controller;

import com.nbcamp.gamematching.matchingservice.auth.dto.SigninRequest;
import com.nbcamp.gamematching.matchingservice.auth.dto.SignupRequest;
import com.nbcamp.gamematching.matchingservice.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

//    @GetMapping("/signup")
//    public String signupForm(@ModelAttribute("signupRequest") SignupRequest signupRequest) {
//        return "auth/signup";
//    }

    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest signupRequest) {
        authService.signUp(signupRequest);
    }

    @PostMapping("/signin")
    public void signIn(@RequestBody SigninRequest signinRequest, HttpServletResponse response) {
        authService.signIn(signinRequest, response);
    }

}