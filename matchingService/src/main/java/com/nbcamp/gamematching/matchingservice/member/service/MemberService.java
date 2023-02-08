package com.nbcamp.gamematching.matchingservice.member.service;

import com.nbcamp.gamematching.matchingservice.member.dto.BoardPageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyPageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.MatchingLogPageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    public ProfileDto getMyProfile(Member member);

    BoardPageDto getMyBoards(Long memberId, Pageable pageable);

    MatchingLogPageDto getMyMatchingList(Member member, Pageable pageable);

    BuddyPageDto getMyBuddies(Member member, Pageable pageable);
}
