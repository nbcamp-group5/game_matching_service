package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class BuddyDto {

    private Profile profile;
    private String email;

    public BuddyDto(Member member) {
        this.profile = member.getProfile();
        this.email = member.getEmail();
    }

    public static List<BuddyDto> of(List<Member> members) {
        return members.stream().map(BuddyDto::new).collect(Collectors.toList());
    }
}
