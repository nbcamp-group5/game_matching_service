package com.nbcamp.gamematching.matchingservice.member.dto;

import com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class MemberAdminDto {

    private Long id;
    private String image;
    private String email;
    private MemberRoleEnum role;
    private String nickname;

    public MemberAdminDto(Member member) {
        this.id = member.getId();
        this.image = member.getProfile().getProfileImage();
        this.email = member.getEmail();
        this.role = member.getRole();
        this.nickname = member.getProfile().getNickname();
    }

    public static List<MemberAdminDto> of(List<Member> members) {
        return members.stream().map(MemberAdminDto::new).collect(Collectors.toList());
    }
}
