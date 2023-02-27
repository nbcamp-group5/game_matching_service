package com.nbcamp.gamematching.matchingservice.member.service;

import com.nbcamp.gamematching.matchingservice.board.service.BoardService;
import com.nbcamp.gamematching.matchingservice.comment.service.CommentService;
import com.nbcamp.gamematching.matchingservice.member.dto.ProfileDto;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final MemberService memberService;
    private final BoardService boardService;
    private final CommentService commentService;

    @Override
    public List<ProfileDto> getAllMembers() {
        List<Member> memberList = memberService.findAllByAdmin();
        return ProfileDto.of(memberList);

    }

    @Override
    public ResponseEntity<String> deleteMember(Long memberId) {
        memberService.deleteByAdmin(memberId);
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteBoard(Long boardId) {
        boardService.deleteBoardByAdmin(boardId);
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteComment(Long commentId) {
        commentService.deleteCommentByAdmin(commentId);
        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }

}
