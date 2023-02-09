package com.nbcamp.gamematching.matchingservice.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyDto;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.entity.Board;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import com.nbcamp.gamematching.matchingservice.member.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    public void beforeEach() {
        for (int i = 0; i < 4; i++) {
            Member member = Member.builder()
                    .email("test" + i + "@gmail.com")
                    .password("pass" + i)
                    .profile(Profile.builder().profileImage("localhost:/pic" + i)
                            .nickname("sh" + i)
                            .game(GameType.STAR)
                            .tier(Tier.CHALLENGE)
                            .mannerPoints(i)
                            .build())
                    .build();
            memberRepository.save(member);
        }
    }

    @Test
    public void myProfileTest() throws Exception {
        // given
        Member member = memberRepository.findById(1L).orElseThrow();
        // when
        ProfileDto myProfile = memberService.getMyProfile(member);

        // then
        assertThat(myProfile.getNickname()).isEqualTo("sh");
    }

    @Test
    @Transactional
    public void myBoardsTest() throws Exception {

        // given
        Member member = memberRepository.findById(1L).orElseThrow();

        for (int i = 0; i < 3; i++) {
            Board board = Board.builder()
                    .memberId(member.getId())
                    .boardImageUrl("..")
                    .content("test" + i)
                    .nickname("sh" + i)
                    .build();
            member.addBoards(board);
        }
        memberRepository.save(member);

        // when
        Member findMember = memberRepository.findById(1L).orElseThrow();

        // then
        assertThat(findMember.getBoards().size()).isEqualTo(
                3); //TODO: boardRepository에서 page 객체를 받아 다시 test하기
    }

    @Test
    public void getMyMatchingListTest() throws Exception {
        // given

        // when

        // then

    }


    @Test
    @Transactional
    public void myBuddiesTest() throws Exception {
        // given
        Member member = memberRepository.findById(1L).orElseThrow();
        for (long i = 2; i <= 4; i++) {
            Member m = memberRepository.findById(i).orElseThrow();
            member.addBuddies(m);
        }

        // when
        List<BuddyDto> myBuddies = memberService.getMyBuddies(1L);

        // then
        for (BuddyDto myBuddy : myBuddies) {
            System.out.println("myBuddy = " + myBuddy.getEmail());
        }
        assertThat(myBuddies.size()).isEqualTo(3);
    }


}