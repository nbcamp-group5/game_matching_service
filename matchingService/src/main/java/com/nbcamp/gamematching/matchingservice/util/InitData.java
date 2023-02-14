package com.nbcamp.gamematching.matchingservice.util;



import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class InitData implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {


        Member sparta = Member.builder()
                .email("sparta@aa.aa")
                .password(passwordEncoder.encode("sparta"))
                .role(MemberRoleEnum.ADMIN)
                .profile(Profile.builder()
                        .nickname("안녕dks")
                        .game(GameType.LOL)
                        .tier(Tier.DIAMOND)
                        .profileImage("sdasd")
                        .build())
                .build();

        Member ssda = Member.builder()
                .email("user@aa.aa")
                .password(passwordEncoder.encode("user"))
                .role(MemberRoleEnum.ADMIN)
                .profile(Profile.builder()
                        .nickname("아아아아아")
                        .game(GameType.LOL)
                        .tier(Tier.DIAMOND)
                        .profileImage("sdasd")
                        .build())
                .build();
        memberRepository.save(sparta);
        memberRepository.save(ssda);


    }

}