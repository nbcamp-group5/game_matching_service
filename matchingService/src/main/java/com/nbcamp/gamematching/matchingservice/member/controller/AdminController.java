package com.nbcamp.gamematching.matchingservice.member.controller;

import com.nbcamp.gamematching.matchingservice.board.dto.AnonymousBoardAdminDto;
import com.nbcamp.gamematching.matchingservice.board.dto.BoardAdminDto;
import com.nbcamp.gamematching.matchingservice.comment.dto.AnonymousCommentResponse;
import com.nbcamp.gamematching.matchingservice.comment.dto.CommentResponse;
import com.nbcamp.gamematching.matchingservice.member.dto.MemberAdminDto;
import com.nbcamp.gamematching.matchingservice.member.service.AdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/members")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MemberAdminDto> getAllMembers(@RequestParam Integer page) {
        return adminService.getAllMembers(page);
    }

    @GetMapping("/boards")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<BoardAdminDto> getAllNormalBoards(@RequestParam Integer page) {
        return adminService.getAllNormalBoards(page);
    }

    @GetMapping("/anonymousBoards")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AnonymousBoardAdminDto> getAllAnonymousBoards(@RequestParam Integer page) {
        return adminService.getAllAnonymousBoards(page);
    }

    @GetMapping("/boards/{boardId}/comments")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<CommentResponse> getAllCommentsInBoard(@PathVariable Long boardId) {
        return adminService.getAllCommentsInBoard(boardId);
    }

    @GetMapping("/anonymousBoards/{boardId}/AnonymousComments")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AnonymousCommentResponse> getAllCommentsInAnonymousBoard(@PathVariable Long boardId) {
        return adminService.getAllCommentsInAnonymousBoard(boardId);
    }

    @DeleteMapping("/members/{memberId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteMember(@PathVariable Long memberId) {
        return adminService.deleteMember(memberId);
    }

    @DeleteMapping("/normalBoards/{boardId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        return adminService.deleteBoard(boardId);
    }

    @DeleteMapping("/anonymousBoards/{boardId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteAnonymousBoard(@PathVariable Long boardId) {
        return adminService.deleteAnonymousBoard(boardId);
    }

    @DeleteMapping("/comments/{commentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        return adminService.deleteComment(commentId);
    }

}
