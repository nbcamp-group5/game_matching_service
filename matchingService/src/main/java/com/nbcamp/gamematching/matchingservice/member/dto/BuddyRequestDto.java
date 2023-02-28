package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class BuddyRequestDto {

    private Long id;
    private String profileImage;
    private String nickname;

    public BuddyRequestDto(Member notYetBuddy) {
        this.id = notYetBuddy.getId();
        this.profileImage = notYetBuddy.getProfile().getProfileImage();
        this.nickname = notYetBuddy.getProfile().getNickname();
    }

    public static List<BuddyRequestDto> of(List<Member> notYetBuddyList) {
        return notYetBuddyList.stream().map(BuddyRequestDto::new).collect(Collectors.toList());
    }
}
