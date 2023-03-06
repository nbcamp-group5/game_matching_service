package com.nbcamp.gamematching.matchingservice.member.service;

import com.nbcamp.gamematching.matchingservice.matching.dto.NicknameDto;
import com.nbcamp.gamematching.matchingservice.matching.entity.MatchingLog;
import com.nbcamp.gamematching.matchingservice.member.dto.BoardPageDto;
import com.nbcamp.gamematching.matchingservice.member.dto.BuddyRequestDto;
import com.nbcamp.gamematching.matchingservice.member.dto.MatchingMemberDto;
import com.nbcamp.gamematching.matchingservice.member.dto.MemberAdminDto;
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

//    List<MatchingLog2Dto> getMyMatching2List(Long memberId);
//
//    List<MatchingLog5Dto> getMyMatching5List(Long memberId);

    List<MatchingMemberDto> getMyMatchingMemberList(Long memberId);

    ResponseEntity<String> changeMyProfile(Member member, UpdateProfileRequest request,
            MultipartFile image) throws IOException;

    ProfileDto getOtherProfile(Long memberId);

    ResponseEntity<String> requestBuddy(Long memberId, Long targetUserId);

    ResponseEntity<String> answerBuddyRequest(Long memberId, Long requestUserId, Boolean answer);

    Member responseMemberByMemberEmail(String memberEmail);



    ResponseEntity<String> deleteMyBuddy(Long memberId, Long buddyId);

    List<MemberAdminDto> findAllByAdmin(Integer page);

    void deleteByAdmin(Long memberId);

    ResponseEntity<String> changeRole(Long id, String adminId);

    List<NicknameDto> findNicknamesInMatching(List<MatchingLog> matchingLogs, Long memberId);


}
