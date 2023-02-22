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

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@RequiredArgsConstructor
@Transactional
public class InitData implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void run(ApplicationArguments args) throws Exception {

    lock.writeLock().lock();
        Member sparta = Member.builder()
                .email("sparta@aa.aa")
                .password(passwordEncoder.encode("qwerqwer"))
                .role(MemberRoleEnum.ADMIN)
                .profile(Profile.builder()
                        .nickname("sparta")
                        .game(GameType.LOL)
                        .tier(Tier.DIAMOND)
                        .profileImage("sparta")
                        .build())
                .build();
        memberRepository.saveAndFlush(sparta);


        Member user = Member.builder()
                .email("user@aa.aa")
                .password(passwordEncoder.encode("qwerqwer"))
                .role(MemberRoleEnum.ADMIN)
                .profile(Profile.builder()
                        .nickname("user")
                        .game(GameType.LOL)
                        .tier(Tier.DIAMOND)
                        .profileImage("user")
                        .build())
                .build();
        memberRepository.saveAndFlush(user);
        Member userb = Member.builder()
                .email("user@bb.bb")
                .password(passwordEncoder.encode("qwerqwer"))
                .role(MemberRoleEnum.ADMIN)
                .profile(Profile.builder()
                        .nickname("bbbb")
                        .game(GameType.LOL)
                        .tier(Tier.DIAMOND)
                        .profileImage("bbbb")
                        .build())
                .build();
        memberRepository.saveAndFlush(userb);
        Member userc = Member.builder()
                .email("user@cc.cc")
                .password(passwordEncoder.encode("qwerqwer"))
                .role(MemberRoleEnum.ADMIN)
                .profile(Profile.builder()
                        .nickname("cccc")
                        .game(GameType.LOL)
                        .tier(Tier.DIAMOND)
                        .profileImage("cccc")
                        .build())
                .build();
        memberRepository.saveAndFlush(userc);
        Member userd = Member.builder()
                .email("user@dd.dd")
                .password(passwordEncoder.encode("qwerqwer"))
                .role(MemberRoleEnum.ADMIN)
                .profile(Profile.builder()
                        .nickname("dddd")
                        .game(GameType.LOL)
                        .tier(Tier.DIAMOND)
                        .profileImage("dddd")
                        .build())
                .build();
        memberRepository.saveAndFlush(userd);

        //--------------------------------------
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