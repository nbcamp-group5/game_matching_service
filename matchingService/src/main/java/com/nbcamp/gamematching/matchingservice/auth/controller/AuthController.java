package com.nbcamp.gamematching.matchingservice.auth.controller;

import com.nbcamp.gamematching.matchingservice.auth.dto.SigninRequest;
import com.nbcamp.gamematching.matchingservice.auth.dto.SignupRequest;
import com.nbcamp.gamematching.matchingservice.auth.service.AuthService;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest signupRequest) {
        authService.signUp(signupRequest);
    }

    @PostMapping("/signin")
    public void signIn(@RequestBody SigninRequest signinRequest, HttpServletResponse response) {
        authService.signIn(signinRequest, response);
    }

    @GetMapping({"", "/"})
    public @ResponseBody String index(@AuthenticationPrincipal UserDetailsImpl principalDetails) {
//    System.out.println("1 : "+principalDetails.getUser());
//    System.out.println("2 : "+principalDetails.getAttributes());

        return "회원가입 완료";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }


}