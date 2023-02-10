package com.nbcamp.gamematching.matchingservice.member.controller;

import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import com.nbcamp.gamematching.matchingservice.member.dto.BoardPageDto.BoardContent;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyDto;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyRequestDto;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileRequest;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import com.nbcamp.gamematching.matchingservice.member.service.MemberService;
import com.nbcamp.gamematching.matchingservice.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    @ResponseBody
    public ProfileDto getMyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return memberService.getMyProfile(member);
    }

    @GetMapping("/boards")
    @ResponseBody
    public List<BoardContent> getMyBoardList(Model model, Pageable pageable,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        Pageable newPageable = toPageable(pageable.getPageNumber(),
                pageable.getPageSize());
//        model.addAttribute("boardList", memberService.getMyBoards(member.getId(), newPageable));
//        return "boardList";
        return memberService.getMyBoards(member.getId(), newPageable).getContents();
    }

    @GetMapping("/matchings")
    public String getMyMatchingList(Model model, Pageable pageable) {
        // TODO: 커밋할 때 아래 member 객체 지울 것
        Member member = Member.builder().profile(
                        Profile.builder().profileImage("localhost:/")
                                .nickname("sh")
                                .game(GameType.LOL)
                                .tier(Tier.CHALLENGE)
                                .build())
                .build();
        Pageable newPageable = toPageable(pageable.getPageNumber(),
                pageable.getPageSize());
        model.addAttribute("matchingList", memberService.getMyMatchingList(member, newPageable));
        return "matchingList";
    }

    @GetMapping("/buddy")
    public String getMyBuddyList(Model model,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        List<BuddyDto> myBuddies = memberService.getMyBuddies(member.getId());
        model.addAttribute("buddyList", myBuddies);
        return "buddyList";
    }

    @GetMapping("/notyetbuddy")
    public String getBuddyRequest(Model model) {
        // TODO: 커밋할 때 아래 member 객체 지울 것
        Member member = Member.builder().profile(
                        Profile.builder().profileImage("localhost:/")
                                .nickname("sh")
                                .game(GameType.STAR)
                                .tier(Tier.CHALLENGE)
                                .build())
                .build();
        List<BuddyRequestDto> myBuddies = memberService.getBuddyRequests(member.getId());
        model.addAttribute("buddyList", myBuddies);
        return "buddyRequestList";
    }

    @PatchMapping("/")
    public ResponseEntity<String> changeMyProfile(@RequestParam ProfileRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Member member = userDetails.getMember();
        return memberService.changeMyProfile(member, request);
    }

    @GetMapping("/{userId}")
    public ProfileDto getOtherProfile(@PathVariable Long userId) {
        return memberService.getOtherProfile(userId);
    }

    public static Pageable toPageable(Integer currentPage, Integer size) {
        return PageRequest.of((currentPage - 1), size);
    }
}
