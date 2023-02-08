package com.nbcamp.gamematching.matchingservice.auth.service;

import com.nbcamp.gamematching.matchingservice.auth.dto.SigninRequest;
import com.nbcamp.gamematching.matchingservice.auth.dto.SignupRequest;
import com.nbcamp.gamematching.matchingservice.exception.ExistsException;
<<<<<<< HEAD
import com.nbcamp.gamematching.matchingservice.exception.SignException;
import com.nbcamp.gamematching.matchingservice.jwt.JwtUtil;
import com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
=======
import com.nbcamp.gamematching.matchingservice.exception.NotfoundException;
import com.nbcamp.gamematching.matchingservice.exception.SignException;
import com.nbcamp.gamematching.matchingservice.jwt.JwtUtil;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.MemberRoleEnum;
>>>>>>> 30e373315b324550ea347b1925a387acbae3b952
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
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
<<<<<<< HEAD

        if (memberRepository.existsByEmail(email)) {
            throw new ExistsException.DuplicatedEmail();
        }
=======
        if(memberRepository.existsByEmail(email)) {
            throw new ExistsException.DuplicatedEmail();}
>>>>>>> 30e373315b324550ea347b1925a387acbae3b952
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
<<<<<<< HEAD

        Member member = memberRepository.findByEmail(email).orElseThrow(SignException::new);
        if (!passwordEncoder.matches(password, member.getPassword())) {
=======
        Member member = memberRepository.findByEmail(email).orElseThrow(SignException::new);
        if(!passwordEncoder.matches(password, member.getPassword())) {
>>>>>>> 30e373315b324550ea347b1925a387acbae3b952
            throw new SignException();
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER,
                jwtUtil.createToken(member.getEmail(), member.getRole()));
    }
<<<<<<< HEAD
=======

>>>>>>> 30e373315b324550ea347b1925a387acbae3b952
}