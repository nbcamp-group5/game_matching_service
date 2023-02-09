package com.nbcamp.gamematching.matchingservice.auth.service;

import com.nbcamp.gamematching.matchingservice.auth.dto.SigninRequest;
import com.nbcamp.gamematching.matchingservice.auth.dto.SignupRequest;
import com.nbcamp.gamematching.matchingservice.exception.ExistsException;
import com.nbcamp.gamematching.matchingservice.exception.SignException;
import com.nbcamp.gamematching.matchingservice.jwt.JwtUtil;
import com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

        if (memberRepository.existsByEmail(email)) {
            throw new ExistsException.DuplicatedEmail();
        }

        if (memberRepository.existsByEmail(email)) {
            throw new ExistsException.DuplicatedEmail();
        }

        if (memberRepository.existsByEmail(email)) {
            throw new ExistsException.DuplicatedEmail();
        }

        String encodedPassword = passwordEncoder.encode(password);
        Member member = Member.builder()
                .email(email)
                .password(encodedPassword)
                .profile(Profile.builder()
                        .nickname(signupRequest.getNickname())
                        .profileImage(signupRequest.getMemberImageUrl())
                        .build())
                .role(MemberRoleEnum.USER)
                .build();
        memberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public void signIn(SigninRequest signinRequest, HttpServletResponse response) {
        String email = signinRequest.getEmail();
        String password = signinRequest.getPassword();

        Member member = memberRepository.findByEmail(email).orElseThrow(SignException::new);
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new SignException();
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER,
                jwtUtil.createToken(member.getEmail(), member.getRole()));
    }
}