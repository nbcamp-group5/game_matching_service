package com.nbcamp.gamematching.matchingservice.auth.controller;

import com.nbcamp.gamematching.matchingservice.auth.dto.SigninRequest;
import com.nbcamp.gamematching.matchingservice.auth.dto.SignupRequest;
import com.nbcamp.gamematching.matchingservice.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signUp(@RequestBody SignupRequest signupRequest) {
        authService.signUp(signupRequest);
    }

    @PostMapping("/signin")
    public void signIn(@RequestBody SigninRequest signinRequest, HttpServletResponse response)
            throws UnsupportedEncodingException {
        authService.signIn(signinRequest, response);
    }

    @PostMapping("/signout")
    public void signOut(HttpServletRequest request) {
        authService.signOut(request);
    }


}