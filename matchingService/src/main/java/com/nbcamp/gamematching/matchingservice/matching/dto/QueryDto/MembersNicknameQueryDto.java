package com.nbcamp.gamematching.matchingservice.matching.dto.QueryDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MembersNicknameQueryDto {
    private List<String> membersNicknames;
}