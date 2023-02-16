package com.nbcamp.gamematching.matchingservice.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nbcamp.gamematching.matchingservice.auth.dto.SigninRequest;
import com.nbcamp.gamematching.matchingservice.auth.dto.SignupRequest;
import com.nbcamp.gamematching.matchingservice.exception.ExistsException;
import com.nbcamp.gamematching.matchingservice.exception.SignException.InvalidNickname;
import com.nbcamp.gamematching.matchingservice.exception.SignException.InvalidPassword;
import com.nbcamp.gamematching.matchingservice.jwt.JwtUtil;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class AuthServiceImplTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthService authService;


    @BeforeEach
    public void setup() {
        SignupRequest signupRequest = new SignupRequest("test@naver.com", "password",
                "testName");
        authService.signUp(signupRequest);

    }

    @AfterEach
    public void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @Transactional
    @DisplayName("회원가입 성공")
    public void signUpTest() throws Exception {
        // given
        SignupRequest signupRequest = new SignupRequest("test1@naver.com", "password1",
                "test1");

        // when
        authService.signUp(signupRequest);
        List<Member> memberList = memberRepository.findAll();

        // then
        assertThat(memberList).extracting("email")
                .containsExactly("test@naver.com", "test1@naver.com");

    }

    @Test
    @Transactional
    @DisplayName("회원가입 실패(이메일 중복)")
    public void emailDuplicateTest() throws Exception {
        // given
        SignupRequest signupRequest = new SignupRequest("test@naver.com", "password",
                "testName");

        // then
        assertThatThrownBy(() -> authService.signUp(signupRequest)).isInstanceOf(
                ExistsException.class);
    }

    @Test
    @Transactional
    @DisplayName("회원가입 실패(정규식보다 짧은 비밀번호)")
    public void shortPasswordTest() throws Exception {
        // given
        SignupRequest signupRequest = new SignupRequest("test1@naver.com", "pass",
                "testName");

        // then
        assertThatThrownBy(() -> authService.signUp(signupRequest)).isInstanceOf(
                InvalidPassword.class);
    }

    @Test
    @Transactional
    @DisplayName("회원가입 실패(정규식보다 긴 닉네임)")
    public void shortNicknameTest() throws Exception {
        // given
        SignupRequest signupRequest = new SignupRequest("test1@naver.com", "password",
                "testName11");

        // then
        assertThatThrownBy(() -> authService.signUp(signupRequest)).isInstanceOf(
                InvalidNickname.class);
    }

    @Test
    public void logInTest() throws Exception {
        // given
        SigninRequest signinRequest = new SigninRequest("test@naver.com", "password");
        MockHttpServletResponse response = new MockHttpServletResponse();

        // when
        authService.signIn(signinRequest, response);

        // then
        System.out.println(response.getHeaders(JwtUtil.AUTHORIZATION_HEADER));
        assertThat(response.getHeaders(JwtUtil.AUTHORIZATION_HEADER)).isNotNull();
    }

}