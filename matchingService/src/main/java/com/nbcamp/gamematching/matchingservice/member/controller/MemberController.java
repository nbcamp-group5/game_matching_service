package com.nbcamp.gamematching.matchingservice.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nbcamp.gamematching.matchingservice.member.dto.AnswerBuddyRequestDto;
import com.nbcamp.gamematching.matchingservice.member.dto.BoardPageDto.BoardContent;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyRequestDto;
import com.nbcamp.gamematching.matchingservice.member.dto.EvaluationRequest;
import com.nbcamp.gamematching.matchingservice.member.dto.MatchingLog2Dto;
import com.nbcamp.gamematching.matchingservice.member.dto.MatchingLog5Dto;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.dto.RequestAdmin;
import com.nbcamp.gamematching.matchingservice.member.dto.UpdateProfileRequest;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.service.FileUploadService;
import com.nbcamp.gamematching.matchingservice.member.service.MemberService;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Value("${file.dir}")
    private String fileDir;

    private final FileUploadService fileUploadService;

    @GetMapping("/id")
    public Long getMyId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return member.getId();
    }

    @GetMapping("/")
    public ProfileDto getMyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return memberService.getMyProfile(member);
    }

    @GetMapping("/boards")
    public List<BoardContent> getMyBoardList(@PageableDefault Pageable pageable,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return memberService.getMyBoards(member.getId(), pageable).getContents();
    }

    @GetMapping("/buddies")
    public List<ProfileDto> getMyBuddyList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return memberService.getMyBuddies(member.getId());
    }

    @GetMapping("/notYetBuddies")
    public List<BuddyRequestDto> getBuddyRequest(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return memberService.getBuddyRequests(member.getId());
    }

    // 2인 매칭 조회
    @GetMapping("/matching2")
    public List<MatchingLog2Dto> getMyMatching2List(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return memberService.getMyMatching2List(member.getId());
    }

    // 5인 매칭 조회
    @GetMapping("/matching5")
    public List<MatchingLog5Dto> getMyMatching5List(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return memberService.getMyMatching5List(member.getId());
    }

    // 친구 신청
    @PatchMapping("/notYetBuddies/{userId}")
    public ResponseEntity<String> requestBuddy(@PathVariable Long userId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return memberService.requestBuddy(member.getId(), userId);
    }

    // 친구 수락/거절
    @PostMapping("/notYetBuddies")
    public ResponseEntity<String> answerBuddyRequest(@RequestBody AnswerBuddyRequestDto request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return memberService.answerBuddyRequest(member.getId(), request.getRequestMemberId(),
                request.getAnswer());
    }

    @PostMapping("/")
    public ResponseEntity<String> changeMyProfile(
            @RequestPart("requestDto") UpdateProfileRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        Member member = userDetails.getMember();
        log.info("multipartFile={}", image);
        return memberService.changeMyProfile(member, request, image);
    }


    @GetMapping("/{memberId}")
    @ResponseBody
    public ProfileDto getOtherProfile(@PathVariable Long memberId) {
        return memberService.getOtherProfile(memberId);
    }

    @PostMapping("/mannerPoints/evaluation")
    public ResponseEntity<String> changeMannerPoints(@RequestBody EvaluationRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) throws JsonProcessingException {
        Member member = userDetails.getMember();
        return memberService.changeMannerPoints(request, member.getId());
    }

    // 친구 삭제
    @DeleteMapping("/buddies/{buddyId}")
    public ResponseEntity<String> deleteMyBuddy(@PathVariable Long buddyId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return memberService.deleteMyBuddy(member.getId(), buddyId);
    }

    @PatchMapping("/role")
    public ResponseEntity<String> changeMyRole( //TODO: 프론트 만들기
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody RequestAdmin request) {
        Member member = userDetails.getMember();
        return memberService.changeRole(member.getId(), request.getAdminId());
    }

}