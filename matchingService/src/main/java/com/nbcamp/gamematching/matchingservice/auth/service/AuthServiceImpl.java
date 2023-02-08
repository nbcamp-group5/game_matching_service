package com.nbcamp.gamematching.matchingservice.auth.service;

import com.nbcamp.gamematching.matchingservice.auth.dto.SigninRequest;
import com.nbcamp.gamematching.matchingservice.auth.dto.SignupRequest;
<<<<<<< Updated upstream
import com.nbcamp.gamematching.matchingservice.jwt.JwtUtil;
import com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
=======
import com.nbcamp.gamematching.matchingservice.exception.ExistsException;
import com.nbcamp.gamematching.matchingservice.exception.NotfoundException;
import com.nbcamp.gamematching.matchingservice.exception.SignException;
import com.nbcamp.gamematching.matchingservice.jwt.JwtUtil;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.MemberRoleEnum;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
>>>>>>> Stashed changes
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signUp(SignupRequest signupRequest) {
        String email = signupRequest.getEmail();
        String password = signupRequest.getPassword();
<<<<<<< Updated upstream
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이메일 없음");
        } //커스텀 익셉션
=======

        if(memberRepository.existsByEmail(email)) {
            throw new ExistsException.DuplicatedEmail();}

>>>>>>> Stashed changes
        String encodedPassword = passwordEncoder.encode(password);
        Member member = Member.builder()
                .email(email)
                .password(encodedPassword)
                .role(MemberRoleEnum.USER)
                .build();
        memberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public void signIn(SigninRequest signinRequest, HttpServletResponse response) {
        String email = signinRequest.getEmail();
        String password = signinRequest.getPassword();
<<<<<<< Updated upstream
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
=======

        Member member = memberRepository.findByEmail(email).orElseThrow(SignException::new);
        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new SignException();
>>>>>>> Stashed changes
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER,
                jwtUtil.createToken(member.getEmail(), member.getRole()));
    }

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
}