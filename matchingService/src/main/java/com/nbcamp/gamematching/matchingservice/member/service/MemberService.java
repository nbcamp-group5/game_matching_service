package com.nbcamp.gamematching.matchingservice.member.service;

import com.nbcamp.gamematching.matchingservice.member.dto.BoardPageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyDto;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyRequestDto;
import com.nbcamp.gamematching.matchingservice.member.dto.MatchingLogPageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    public ProfileDto getMyProfile(Member member);

    BoardPageDto getMyBoards(Long memberId, Pageable pageable);

    MatchingLogPageDto getMyMatchingList(Member member, Pageable pageable);

    List<BuddyDto> getMyBuddies(Long memberId);

    List<BuddyRequestDto> getBuddyRequests(Long memberId);
}
