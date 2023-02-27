package com.nbcamp.gamematching.matchingservice.member.service;

import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    List<ProfileDto> getAllMembers();

    ResponseEntity<String> deleteMember(Long memberId);

    ResponseEntity<String> deleteBoard(Long boardId);

    ResponseEntity<String> deleteComment(Long commentId);

}
