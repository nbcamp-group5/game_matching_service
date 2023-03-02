package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ProfileDto {

    private Long id;
    private String nickname;
    private String image;
    private GameType game;
    private Tier tier;
    private int mannerPoints;
    private String email;
    private MemberRoleEnum role;

    public ProfileDto(Member member) {
        this.nickname = member.getProfile().getNickname();
        this.image = member.getProfile().getProfileImage();
        this.game = member.getProfile().getGame();
        this.tier = member.getProfile().getTier();
        this.mannerPoints = member.getProfile().getMannerPoints();
        this.email = member.getEmail();
        this.id = member.getId();
        this.role = member.getRole();
    }

    public static List<ProfileDto> of(List<Member> members) {
        return members.stream().map(ProfileDto::new).collect(Collectors.toList());
    }
}
