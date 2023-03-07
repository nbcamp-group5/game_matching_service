package com.nbcamp.gamematching.matchingservice.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.auth.dto.SigninRequest;
import com.nbcamp.gamematching.matchingservice.auth.dto.SignupRequest;
import com.nbcamp.gamematching.matchingservice.auth.service.AuthService;
import com.nbcamp.gamematching.matchingservice.auth.service.KakaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    private final KakaoService kakaoService;

    @PostMapping("/signup")
    @ResponseBody
    public void signUp(@RequestBody SignupRequest signupRequest) {
        authService.signUp(signupRequest);
    }

    @PostMapping("/signin")
    @ResponseBody
    public void signIn(@RequestBody SigninRequest signinRequest, HttpServletResponse response)
            throws UnsupportedEncodingException {
        authService.signIn(signinRequest, response);
    }

    @PostMapping("/signout")
    @ResponseBody
    public void signOut(HttpServletRequest request) {
        authService.signOut(request);
    }

    @GetMapping("/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws UnsupportedEncodingException, JsonProcessingException {

        String token = kakaoService.kakaoLogin(code, response);

        response.setHeader("Authorization", token);

        return "redirect:http://game-matching.s3-website.ap-northeast-2.amazonaws.com/kakao.html?token="+ token;
    }


}