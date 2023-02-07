package com.nbcamp.gamematching.matchingservice.member.controller;

import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import com.nbcamp.gamematching.matchingservice.member.dto.PageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import com.nbcamp.gamematching.matchingservice.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public ProfileDto getMyProfile() {
        Member member = Member.builder().profile(
                        Profile.builder().profileImage("localhost:/")
                                .nickname("sh")
                                .game(GameType.MAPLE)
                                .tier(Tier.CHALLENGE)
                                .mannerPoints(10)
                                .build())
                .build();
        return memberService.getMyProfile(member);
    }

    @GetMapping("/boards")
    public String getMyBoardList(Model model, Pageable pageable) {
        // TODO: 커밋할 때 아래 member 객체 지울 것
        Member member = Member.builder().profile(
                        Profile.builder().profileImage("localhost:/")
                                .nickname("sh")
                                .game(GameType.MAPLE)
                                .tier(Tier.CHALLENGE)
                                .mannerPoints(10)
                                .build())
                .build();
        Pageable newPageable = PageDto.toPageable(pageable.getPageNumber(), pageable.getPageSize());
        model.addAttribute("boardList", memberService.getMyBoards(member.getId(), newPageable));
        return "boardList";
    }
}
