package com.nbcamp.gamematching.matchingservice.member.service;

import com.nbcamp.gamematching.matchingservice.board.dto.AnonymousBoardAdminDto;
import com.nbcamp.gamematching.matchingservice.board.dto.BoardAdminDto;
import com.nbcamp.gamematching.matchingservice.board.service.AnonymousBoardServiceImpl;
import com.nbcamp.gamematching.matchingservice.board.service.BoardService;
import com.nbcamp.gamematching.matchingservice.comment.dto.AnonymousCommentResponse;
import com.nbcamp.gamematching.matchingservice.comment.dto.CommentResponse;
import com.nbcamp.gamematching.matchingservice.comment.service.CommentServiceImpl;
import com.nbcamp.gamematching.matchingservice.member.dto.MemberAdminDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final MemberService memberService;
    private final BoardService boardService;
    private final AnonymousBoardServiceImpl anonymousBoardService;
    private final CommentServiceImpl commentService;

    @Override
    public List<MemberAdminDto> getAllMembers(Integer page) {
        return memberService.findAllByAdmin(page);

    }

    @Override
    public ResponseEntity<String> deleteMember(Long memberId) {
        memberService.deleteByAdmin(memberId);
        return new ResponseEntity<>("해당 유저가 삭제되었습니다.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteBoard(Long boardId) {
        boardService.deleteBoardByAdmin(boardId);
        return new ResponseEntity<>("게시글이 삭제되었습니다.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteComment(Long commentId) {
        commentService.deleteCommentByAdmin(commentId);
        return new ResponseEntity<>("댓글이 삭제되었습니다.", HttpStatus.OK);
    }

    @Override
    public List<BoardAdminDto> getAllNormalBoards(Integer page) {
        return boardService.getBoardsByAdmin(page);
    }

    @Override
    public List<AnonymousBoardAdminDto> getAllAnonymousBoards(Integer page) {
        return anonymousBoardService.getAnonymousBoardsByAdmin(page);
    }

    @Override
    public ResponseEntity<String> deleteAnonymousBoard(Long boardId) {
        anonymousBoardService.deleteAnonymousBoardByAdmin(boardId);
        return new ResponseEntity<>("게시글이 삭제되었습니다.", HttpStatus.OK);
    }

    @Override
    public List<CommentResponse> getAllCommentsInBoard(Long boardId) {
        return commentService.showComment(boardId);
    }

    @Override
    public List<AnonymousCommentResponse> getAllCommentsInAnonymousBoard(Long boardId) {
        return commentService.showAnonymousComment(boardId);
    }

}
