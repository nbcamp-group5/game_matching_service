package com.nbcamp.gamematching.matchingservice.matching.dto;

import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class NicknameDto {
    private Long id;
    private String nickname;

    public NicknameDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getProfile().getNickname();
    }

    public static List<NicknameDto> of(List<Member> members) {
        return members.stream().map(NicknameDto::new).collect(Collectors.toList());
    }
}
