package com.nbcamp.gamematching.matchingservice.member.service;

import com.nbcamp.gamematching.matchingservice.member.dto.BoardPageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyRequestDto;
import com.nbcamp.gamematching.matchingservice.member.dto.MannerPointsRequest;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.dto.UpdateProfileRequest;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {

    ProfileDto getMyProfile(Member member);

    BoardPageDto getMyBoards(Long memberId, Pageable pageable);

    List<ProfileDto> getMyBuddies(Long memberId);

    List<BuddyRequestDto> getBuddyRequests(Long memberId);

    ResponseEntity<String> changeMyProfile(Member member, UpdateProfileRequest request,
            MultipartFile image) throws IOException;

    ProfileDto getOtherProfile(Long memberId);

    ResponseEntity<String> requestBuddy(Long memberId, Long targetUserId);

    ResponseEntity<String> answerBuddyRequest(Long memberId, Long requestUserId, Boolean answer);

    ResponseEntity<String> changeMannerPoints(MannerPointsRequest request);

    Member responseMemberByMemberId(Long memberId);
}
