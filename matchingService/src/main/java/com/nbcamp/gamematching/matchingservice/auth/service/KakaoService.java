package com.nbcamp.gamematching.matchingservice.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbcamp.gamematching.matchingservice.auth.dto.SocialUserInfoDto;
import com.nbcamp.gamematching.matchingservice.jwt.JwtUtil;
import com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import com.nbcamp.gamematching.matchingservice.redis.RedisService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    private static final long REFRESH_TOKEN_TIME = 24 * 60 * 60 * 1000L;

    private final RedisService redisService;


    public String kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException, UnsupportedEncodingException {
        //1. "인가 코드"로 "액세스 토큰" 요청
        String Token = getToken(code);

        // 2. 토큰으로 카카오 API 호출
        SocialUserInfoDto kakaoUserInfoDto = getKakaoUserInfo(Token);

        //3. 카카오정보로 회원가입
        Member kakaoUser = registerKakaoUserIfNeeded(kakaoUserInfoDto);


        // 5. response Header에 JWT 토큰 추가, 쿠키에 refreshToken 추가
        Cookie cookie = jwtUtil.createCookie(kakaoUser.getEmail());
        String refreshToken = cookie.getValue();
        String accessToken = jwtUtil.createAccessToken(kakaoUser.getEmail(), kakaoUser.getRole());
        response.addCookie(cookie);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
        redisService.addRefreshTokenByRedis(kakaoUser.getEmail(), refreshToken,
                Duration.ofMillis(REFRESH_TOKEN_TIME));

        return accessToken;
    }
    // 1. "인가 코드"로 "액세스 토큰" 요청
    private String getToken(String code) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "8c70ca1b49ada3a6a00aafdf2b0f125a");
        body.add("redirect_uri", "http://localhost:8080/api/auth/kakao/callback");
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }
    // 2. 토큰으로 카카오 API 호출 : "액세스 토큰"으로 "카카오 사용자 정보" 가져오기
    private SocialUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        // responseBody에 있는 정보를 꺼냄
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);


        Long id = jsonNode.get("id").asLong();
        String email = jsonNode.get("kakao_account").get("email").asText();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();

        return new SocialUserInfoDto(id, nickname, email);
    }
    // 3. 카카오로 회원가입
    private Member registerKakaoUserIfNeeded(SocialUserInfoDto kakaoUserInfo) {
        // DB 에 중복된 Kakao email이 있는지 확인
        String checkEmail = kakaoUserInfo.getEmail();
        String checkProvider = "kakao";
        Member kakaoUser = memberRepository.findByEmailAndProvider(checkEmail,checkProvider);
        String password = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(password);
        String email = kakaoUserInfo.getEmail();
        String providerId = kakaoUserInfo.getId().toString();
        if (kakaoUser == null) {
//            kakaoUser = new Member(email.substring(2),encodedPassword,new Profile(kakaoUserInfo.getNickname(),null,null,null), MemberRoleEnum.USER,"kakao",providerId);
//            memberRepository.save(kakaoUser);
//            // 카카오 사용자 email 동일한 email 가진 회원이 있는지 확인
//            } else {
                kakaoUser = new Member(email,encodedPassword,new Profile(kakaoUserInfo.getNickname(),null,null,null), MemberRoleEnum.USER,"kakao",providerId);
            memberRepository.save(kakaoUser);
            }

        return kakaoUser;
        }

    }

