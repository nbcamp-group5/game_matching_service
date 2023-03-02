package com.nbcamp.gamematching.matchingservice.member.service;

import com.nbcamp.gamematching.matchingservice.board.dto.AnonymousBoardAdminDto;
import com.nbcamp.gamematching.matchingservice.board.dto.BoardAdminDto;
import com.nbcamp.gamematching.matchingservice.comment.dto.CommentResponse;
import com.nbcamp.gamematching.matchingservice.member.dto.MemberAdminDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    List<MemberAdminDto> getAllMembers(Integer page);

    ResponseEntity<String> deleteMember(Long memberId);

    ResponseEntity<String> deleteBoard(Long boardId);

    ResponseEntity<String> deleteComment(Long commentId);

    List<BoardAdminDto> getAllNormalBoards(Integer page);

    List<AnonymousBoardAdminDto> getAllAnonymousBoards(Integer page);

    ResponseEntity<String> deleteAnonymousBoard(Long boardId);

    List<CommentResponse> getAllCommentsInBoard(Long boardId);
}
