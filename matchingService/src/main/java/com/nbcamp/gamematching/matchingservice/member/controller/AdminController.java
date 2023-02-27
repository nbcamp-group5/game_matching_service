package com.nbcamp.gamematching.matchingservice.member.controller;

import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.service.AdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/members")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProfileDto> getAllMembers() {
        return adminService.getAllMembers();
    }

    @DeleteMapping("/members/{memberId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteAMember(@PathVariable Long memberId) {
        return adminService.deleteMember(memberId);
    }

    @DeleteMapping("/boards/{boardId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteABoard(@PathVariable Long boardId) {
        return adminService.deleteBoard(boardId);
    }

    @DeleteMapping("/comments/{commentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteAComment(@PathVariable Long commentId) {
        return adminService.deleteComment(commentId);
    }

}
