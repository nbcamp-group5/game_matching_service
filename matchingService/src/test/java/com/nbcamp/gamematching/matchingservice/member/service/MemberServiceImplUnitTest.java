package com.nbcamp.gamematching.matchingservice.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.nbcamp.gamematching.matchingservice.board.dto.CreateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.service.BoardService;
import com.nbcamp.gamematching.matchingservice.member.controller.MemberController;
import com.nbcamp.gamematching.matchingservice.member.domain.FileStore;
import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import com.nbcamp.gamematching.matchingservice.member.dto.BoardPageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyDto;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyRequestDto;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.dto.UpdateProfileRequest;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

class MemberServiceImplUnitTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private BoardService boardService;

    @Mock
    private MemberRepository memberRepository;

    @Autowired
    private FileStore fileStore;

//    @BeforeEach
//    public void beforeEach() {
//        for (int i = 0; i < 4; i++) {
//            Member member = Member.builder()
//                    .email("test" + i + "@gmail.com")
//                    .password("password" + i)
//                    .profile(Profile.builder().profileImage("localhost:/pic" + i)
//                            .nickname("she" + i)
//                            .game(GameType.STAR)
//                            .tier(Tier.CHALLENGE)
//                            .build())
//                    .build();
//            memberRepository.save(member);
//        }
//    }


    @Test
    @DisplayName("내 프로필 닉네임 검증")
    public void myNicknameTest() throws Exception {
        // given
        Member member = Member.builder()
                .email("test" + "@gmail.com")
                .password("password")
                .profile(Profile.builder().profileImage("localhost:/pic")
                        .nickname("shef")
                        .game(GameType.STAR)
                        .tier(Tier.CHALLENGE)
                        .build())
                .build();
        when(memberRepository.findById().thenReturn(member);


        Member member = memberRepository.findById(1L).orElseThrow();
        // when
        ProfileDto myProfile = memberService.getMyProfile(member);
        // then
        assertThat(myProfile.getNickname()).isEqualTo("she0");

    }

    @Test
    @DisplayName("내 주 게임 검증")
    public void myGameTypeTest() throws Exception {
        // given
        Member member = memberRepository.findById(1L).orElseThrow();
        // when
        ProfileDto myProfile = memberService.getMyProfile(member);
        // then
        assertThat(myProfile.getGame()).isEqualTo(GameType.STAR);
    }

    @Test
    @DisplayName("내 티어 검증")
    public void myTierTest() throws Exception {
        // given
        Member member = memberRepository.findById(1L).orElseThrow();
        // when
        ProfileDto myProfile = memberService.getMyProfile(member);
        // then
        assertThat(myProfile.getTier()).isEqualTo(Tier.CHALLENGE);
    }


    @Test
    @DisplayName("게시글 조회 검증")
    @Transactional
    public void myBoardsTest() throws Exception {

        // given
        Member member = memberRepository.findById(1L).orElseThrow();

        for (int i = 0; i < 3; i++) {
            boardService.createBoard(new CreateBoardRequest("localhost:/..." + i, "test" + i),
                    member);
        }

        // when
        BoardPageDto myBoards = memberService.getMyBoards(1L, MemberController.toPageable(1, 2));

        // then
        assertThat(myBoards.getContents().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @DisplayName("내 친구 목록 조회 검증")
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
    @DisplayName("친구 신청")
    @Transactional
    public void buddyRequestTest() throws Exception {
        // given

        // when
        ResponseEntity<String> response = memberService.requestBuddy(2L, 1L);

        // then

        assertThat(response.getBody()).isEqualTo("친구 요청되었습니다.");
    }


    @Test
    @DisplayName("친구 신청 확인하기")
    public void getBuddyRequest() throws Exception {
        // given
        memberService.requestBuddy(2L, 1L);

        // when
        List<BuddyRequestDto> buddyRequests = memberService.getBuddyRequests(1L);

        // then
        assertThat(buddyRequests.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("친구 신청 수락하기")
    @Transactional
    public void acceptBuddyRequestTest() throws Exception {
        // given
        memberService.requestBuddy(2L, 1L);

        // when
        ResponseEntity<String> response = memberService.answerBuddyRequest(1L, 2L, true);

        // then
        assertThat(response.getBody()).isEqualTo("친구 등록되었습니다.");
    }

    @Test
    @DisplayName("친구 신청 거절하기")
    @Transactional
    public void declineBuddyRequestTest() throws Exception {
        // given
        memberService.requestBuddy(2L, 1L);

        // when
        ResponseEntity<String> response = memberService.answerBuddyRequest(1L, 2L, false);

        // then
        Member member = memberRepository.findById(1L).orElseThrow();
        List<Member> notYetBuddies = member.getNotYetBuddies();
        for (Member notYetBuddy : notYetBuddies) {
            System.out.println("notYetBuddy = " + notYetBuddy);
        }
        assertThat(response.getBody()).isEqualTo("요청이 거부되었습니다.");
    }


    @Value("${file.dir}")
    private String fileDir;

    @Test
    @DisplayName("프로필 변경")
    @Transactional
    public void changeMyProfileTest() throws Exception {
        // given
        UpdateProfileRequest request = new UpdateProfileRequest("test1", Tier.SILVER, GameType.LOL);
        Member findMember = memberRepository.findById(1L).get();
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "Spring Framework".getBytes());

        // when
        ResponseEntity<String> response = memberService.changeMyProfile(findMember, request,
                multipartFile);

        // then
        Member member = memberRepository.findById(1L).get();
        System.out.println(member.getProfile().getProfileImage());
        assertThat(response.getBody()).isEqualTo("프로필이 변경되었습니다.");
    }

    @Test
    @DisplayName("다른 회원 프로필 조회하기")
    public void getOtherProfile() throws Exception {
        // given

        // when
        ProfileDto otherProfile = memberService.getOtherProfile(2L);

        // then
        assertThat(otherProfile.getNickname()).isEqualTo("she1");
    }


}