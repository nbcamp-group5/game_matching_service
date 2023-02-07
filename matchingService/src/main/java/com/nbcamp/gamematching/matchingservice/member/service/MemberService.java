package com.nbcamp.gamematching.matchingservice.member.service;

import com.nbcamp.gamematching.matchingservice.member.dto.PageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    public ProfileDto getMyProfile(Member member);

    PageDto getMyBoards(Long memberId, Pageable pageable);
}
