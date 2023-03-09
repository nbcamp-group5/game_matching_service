//package com.nbcamp.gamematching.matchingservice.util;
//
//import com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog;
//import com.nbcamp.gamematching.matchingservice.matching.entity.ResultMatching;
//import com.nbcamp.gamematching.matchingservice.matching.repository.MatchingLogRepository;
//import com.nbcamp.gamematching.matchingservice.matching.repository.ResultMatchingRepository;
//import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
//import com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum;
//import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
//import com.nbcamp.gamematching.matchingservice.member.entity.Member;
//import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
//import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
//@Component
//@RequiredArgsConstructor
//@Transactional
//public class InitData implements ApplicationRunner {
//    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final ResultMatchingRepository resultMatchingRepository;
//    private final MatchingLogRepository matchingLogRepository;
//    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
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
//        Member usera = Member.builder()
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
//        memberRepository.saveAndFlush(usera);
//
//        Member userb = Member.builder()
//                .email("user@bb.bb")
//                .password(passwordEncoder.encode("qwerqwer"))
//                .role(MemberRoleEnum.ADMIN)
//                .profile(Profile.builder()
//                        .nickname("bbbb")
//                        .game(GameType.LOL)
//                        .tier(Tier.DIAMOND)
//                        .profileImage("bbbb")
//                        .build())
//                .build();
//        memberRepository.saveAndFlush(userb);
//        Member userc = Member.builder()
//                .email("user@cc.cc")
//                .password(passwordEncoder.encode("qwerqwer"))
//                .role(MemberRoleEnum.ADMIN)
//                .profile(Profile.builder()
//                        .nickname("cccc")
//                        .game(GameType.LOL)
//                        .tier(Tier.DIAMOND)
//                        .profileImage("cccc")
//                        .build())
//                .build();
//        memberRepository.saveAndFlush(userc);
//        Member userd = Member.builder()
//                .email("user@dd.dd")
//                .password(passwordEncoder.encode("qwerqwer"))
//                .role(MemberRoleEnum.ADMIN)
//                .profile(Profile.builder()
//                        .nickname("dddd")
//                        .game(GameType.LOL)
//                        .tier(Tier.DIAMOND)
//                        .profileImage("dddd")
//                        .build())
//                .build();
//        memberRepository.saveAndFlush(userd);
//
//        var rmat1 = new ResultMatching("즐겜","LOL2","https://discord.gg/4c9KVpW");
//        var rmat2 = new ResultMatching("즐겜","LOL2","https://discord.gg/4c9KVpW");
//        var rmat3 = new ResultMatching("즐겜","LOL2","https://discord.gg/4c9KVpW");
//        var rmat4 = new ResultMatching("즐겜","LOL5","https://discord.gg/4c9KVpW");
//        var rmat5 = new ResultMatching("즐겜","LOL5","https://discord.gg/4c9KVpW");
//        var rmat6 = new ResultMatching("즐겜","LOL2","https://discord.gg/4c9KVpW");
//        resultMatchingRepository.saveAndFlush(rmat1);
//        resultMatchingRepository.saveAndFlush(rmat2);
//        resultMatchingRepository.saveAndFlush(rmat3);
//        resultMatchingRepository.saveAndFlush(rmat4);
//        resultMatchingRepository.saveAndFlush(rmat5);
//
//        matchingLogRepository.saveAndFlush(new MatchingLog(rmat1,usera));
//        matchingLogRepository.saveAndFlush(new MatchingLog(rmat1,userb));
//        matchingLogRepository.saveAndFlush(new MatchingLog(rmat2,usera));
//        matchingLogRepository.saveAndFlush(new MatchingLog(rmat2,userc));
//        matchingLogRepository.saveAndFlush(new MatchingLog(rmat3,usera));
//        matchingLogRepository.saveAndFlush(new MatchingLog(rmat3,userc));
//        matchingLogRepository.saveAndFlush(new MatchingLog(rmat4,usera));
//        matchingLogRepository.saveAndFlush(new MatchingLog(rmat4,userb));
//        matchingLogRepository.saveAndFlush(new MatchingLog(rmat4,userc));
//        matchingLogRepository.saveAndFlush(new MatchingLog(rmat4,userd));
//        matchingLogRepository.saveAndFlush(new MatchingLog(rmat4,sparta));
//        lock.writeLock().unlock();
//
//
//
//        //--------------------------------------
////
////        for (int i=0; i < 1000; i++ ){
////            Member member = Member.builder()
////                    .email("toto"+i+"@aa.aa")
////                    .password(passwordEncoder.encode("qwerqwer"))
////                    .role(MemberRoleEnum.ADMIN)
////                    .profile(Profile.builder()
////                            .nickname("toto"+i)
////                            .game(GameType.LOL)
////                            .tier(Tier.DIAMOND)
////                            .profileImage("toto"+i)
////                            .build())
////                    .build();
////            memberRepository.saveAndFlush(member);
////        }
////        for (int i=0; i < 1000; i++ ){
////            Member member = Member.builder()
////                    .email("coco"+i+"@bb.bb")
////                    .password(passwordEncoder.encode("qwerqwer"))
////                    .role(MemberRoleEnum.USER)
////                    .profile(Profile.builder()
////                            .nickname("coco"+i)
////                            .game(GameType.LOL)
////                            .tier(Tier.SILVER)
////                            .profileImage("coco"+i)
////                            .build())
////                    .build();
////            memberRepository.saveAndFlush(member);
////
////        }
////        for (int i=0; i < 1000; i++ ){
////            Member member = Member.builder()
////                    .email("yaya"+i+"@cc.cc")
////                    .password(passwordEncoder.encode("qwerqwer"))
////                    .role(MemberRoleEnum.ADMIN)
////                    .profile(Profile.builder()
////                            .nickname("yaya"+i)
////                            .game(GameType.STAR)
////                            .tier(Tier.GOLD)
////                            .profileImage("yaya"+i)
////                            .build())
////                    .build();
////            memberRepository.saveAndFlush(member);
////        }
////        for (int i=0; i < 1000; i++ ){
////            Member member = Member.builder()
////                    .email("dodo"+i+"@dd.dd")
////                    .password(passwordEncoder.encode("qwerqwer"))
////                    .role(MemberRoleEnum.USER)
////                    .profile(Profile.builder()
////                            .nickname("dodo"+i)
////                            .game(GameType.STAR)
////                            .tier(Tier.BRONZE)
////                            .profileImage("dodo"+i)
////                            .build())
////                    .build();
////            memberRepository.saveAndFlush(member);
////    lock.writeLock().unlock();
////        }
////    }
//
//    }
//}