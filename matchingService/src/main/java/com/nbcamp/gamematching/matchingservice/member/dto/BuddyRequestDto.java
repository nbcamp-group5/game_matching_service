package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.member.entity.NotYetBuddy;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class BuddyRequestDto {

    private String profileImage;
    private String nickname;

    public BuddyRequestDto(NotYetBuddy notYetBuddy) {
        this.profileImage = notYetBuddy.getProfileImage();
        this.nickname = notYetBuddy.getNickname();
    }

    public static List<BuddyRequestDto> of(List<NotYetBuddy> notYetBuddyList) {
        return notYetBuddyList.stream().map(BuddyRequestDto::new).collect(Collectors.toList());
    }
}
