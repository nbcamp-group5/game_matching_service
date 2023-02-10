package com.nbcamp.gamematching.matchingservice.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.matchinglog.entity.MatchingLog;
import com.nbcamp.gamematching.matchingservice.matchinglog.repository.MatchingLogRepository;
import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyDto;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MatchingLogRepository matchingLogRepository;

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
            Board board = new Board("sh" + i, "..", "test" + i, member);
            member.addBoards(board);
        }
        memberRepository.save(member);

        // when
        Page<Board> findBoards = boardRepository.findAllByMemberId(1L, PageRequest.of(1, 2));

        // then
        assertThat(findBoards.getContent().size()).isEqualTo(
                2); //TODO: boardRepository에서 page 객체를 받아 다시 test하기
    }

    @Test
    public void getMyMatchingListTest()
            throws Exception { //TODO: matchingLog 조회 쿼리 수정 후 다시 테스트할 것(참고: MatchingLogRepository 의 todo)
        // given
        List<Member> members = new ArrayList<>();
        for (long i = 0; i < 2; i++) {
            Member member = memberRepository.findById(i).orElseThrow();
            members.add(member);
        }
        MatchingLog matchingLog = new MatchingLog(members);
        matchingLogRepository.save(matchingLog);

        List<Member> members2 = new ArrayList<>();
        for (long i = 2; i < 4; i++) {
            Member member = memberRepository.findById(i).orElseThrow();
            members2.add(member);
        }
        MatchingLog matchingLog2 = new MatchingLog(members2);
        matchingLogRepository.save(matchingLog2);

        // when
        Member findMember = memberRepository.findById(1L).orElseThrow();
//        MatchingLogPageDto myMatchingList = memberService.getMyMatchingList(findMember,
//                PageRequest.of(1, 1));
//
//        // then
//        assertThat(myMatchingList.getContents().size()).isEqualTo(1);
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

    @Test
    public void buddyRequestTest() throws Exception {
        // given

        // when

        // then

    }

}