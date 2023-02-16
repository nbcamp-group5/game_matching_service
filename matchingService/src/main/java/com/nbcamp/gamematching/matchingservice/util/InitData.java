package com.nbcamp.gamematching.matchingservice.util;

import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@RequiredArgsConstructor
@Transactional
public class InitData implements ApplicationRunner {

    ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//
//    lock.writeLock().lock();
//        Member sparta = Member.builder()
//                .email("sparta@aa.aa")
//                .password(passwordEncoder.encode("qwerqwer"))
//                .role(MemberRoleEnum.ADMIN)
//                .profile(Profile.builder()
//                        .nickname("sparta")
//                        .game(GameType.LOL)
//                        .tier(Tier.DIAMOND)
//                        .profileImage("sparta")
//                        .build())
//                .build();
//        memberRepository.saveAndFlush(sparta);
//
//
//        Member user = Member.builder()
//                .email("user@aa.aa")
//                .password(passwordEncoder.encode("qwerqwer"))
//                .role(MemberRoleEnum.ADMIN)
//                .profile(Profile.builder()
//                        .nickname("user")
//                        .game(GameType.LOL)
//                        .tier(Tier.DIAMOND)
//                        .profileImage("user")
//                        .build())
//                .build();
//        memberRepository.saveAndFlush(user);
//
//        //--------------------------------------
//
//        for (int i=0; i < 1000; i++ ){
//            Member member = Member.builder()
//                    .email("toto"+i+"@aa.aa")
//                    .password(passwordEncoder.encode("qwerqwer"))
//                    .role(MemberRoleEnum.ADMIN)
//                    .profile(Profile.builder()
//                            .nickname("toto"+i)
//                            .game(GameType.LOL)
//                            .tier(Tier.DIAMOND)
//                            .profileImage("toto"+i)
//                            .build())
//                    .build();
//            memberRepository.saveAndFlush(member);
//        }
//        for (int i=0; i < 1000; i++ ){
//            Member member = Member.builder()
//                    .email("coco"+i+"@bb.bb")
//                    .password(passwordEncoder.encode("qwerqwer"))
//                    .role(MemberRoleEnum.USER)
//                    .profile(Profile.builder()
//                            .nickname("coco"+i)
//                            .game(GameType.LOL)
//                            .tier(Tier.SILVER)
//                            .profileImage("coco"+i)
//                            .build())
//                    .build();
//            memberRepository.saveAndFlush(member);
//
//        }
//        for (int i=0; i < 1000; i++ ){
//            Member member = Member.builder()
//                    .email("yaya"+i+"@cc.cc")
//                    .password(passwordEncoder.encode("qwerqwer"))
//                    .role(MemberRoleEnum.ADMIN)
//                    .profile(Profile.builder()
//                            .nickname("yaya"+i)
//                            .game(GameType.STAR)
//                            .tier(Tier.GOLD)
//                            .profileImage("yaya"+i)
//                            .build())
//                    .build();
//            memberRepository.saveAndFlush(member);
//        }
//        for (int i=0; i < 1000; i++ ){
//            Member member = Member.builder()
//                    .email("dodo"+i+"@dd.dd")
//                    .password(passwordEncoder.encode("qwerqwer"))
//                    .role(MemberRoleEnum.USER)
//                    .profile(Profile.builder()
//                            .nickname("dodo"+i)
//                            .game(GameType.STAR)
//                            .tier(Tier.BRONZE)
//                            .profileImage("dodo"+i)
//                            .build())
//                    .build();
//            memberRepository.saveAndFlush(member);
//    lock.writeLock().unlock();
//        }
//    }
    }
}