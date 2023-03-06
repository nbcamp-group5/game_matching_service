package com.nbcamp.gamematching.matchingservice.comment.service;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.repository.AnonymousBoardRepository;
import com.nbcamp.gamematching.matchingservice.board.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.board.service.BoardService;
import com.nbcamp.gamematching.matchingservice.comment.dto.CreateCommentRequest;
import com.nbcamp.gamematching.matchingservice.comment.dto.UpdateCommentRequest;
import com.nbcamp.gamematching.matchingservice.comment.entity.AnonymousComment;
import com.nbcamp.gamematching.matchingservice.comment.entity.Comment;
import com.nbcamp.gamematching.matchingservice.comment.repository.AnonymousCommentRepository;
import com.nbcamp.gamematching.matchingservice.comment.repository.CommentRepository;
import com.nbcamp.gamematching.matchingservice.member.domain.GameType;
import com.nbcamp.gamematching.matchingservice.member.domain.MemberRoleEnum;
import com.nbcamp.gamematching.matchingservice.member.domain.Tier;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import com.nbcamp.gamematching.matchingservice.member.entity.Profile;
import com.nbcamp.gamematching.matchingservice.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AnonymousBoardRepository anonymousBoardRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AnonymousCommentRepository anonymousCommentRepository;

    @BeforeEach
    public void beforeEach() throws IOException {
        Member member = new Member("dddd1@ddd.com", "123456789", new Profile("www11", "sdadad.jpg", Tier.BRONZE, GameType.LOL), MemberRoleEnum.USER);
        memberRepository.save(member);
        Member member1 = new Member("dddd2@ddd.com", "123456789", new Profile("www12", "sdadad2.jpg", Tier.BRONZE, GameType.LOL), MemberRoleEnum.USER);
        memberRepository.save(member1);

        Board board = new Board(member.getProfile().getNickname(),"ddddd.jpg","안녕하세요",member);

        boardRepository.save(board);

        AnonymousBoard anonymousBoard = new AnonymousBoard("aaaa.jpg","익명이다",member);
        anonymousBoardRepository.save(anonymousBoard);
    }

    @Test
    @DisplayName("댓글 작성 테스트")
    void createComment() throws IOException {
        Member findMember = memberRepository.findById(3L).orElseThrow();

        CreateCommentRequest createCommentRequest = new CreateCommentRequest("안녕");

        commentService.createComment(1L,createCommentRequest.getContent(),findMember);

        Comment comment = commentRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException(""));

        assertEquals("안녕",comment.getContent());
        assertEquals("www11", comment.getNickname());
        assertEquals("dddd@ddd.com", comment.getMember().getEmail());


    }

    @Test
    @DisplayName("익명 댓글 작성 테스트")
    void createAnonymousComment() throws IOException {
        Member findMember = memberRepository.findById(3L).orElseThrow();

        CreateCommentRequest createCommentRequest = new CreateCommentRequest("익명 안녕");

        commentService.createAnonymousComment(1L,createCommentRequest.getContent(),findMember);

        AnonymousComment comment = anonymousCommentRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException(""));

        System.out.println(findMember.getId());
        System.out.println(findMember.getEmail());
        System.out.println(comment.getMember().getId());

        assertEquals("익명 안녕",comment.getContent());
        assertEquals("www11",comment.getMember().getProfile().getNickname());
        assertEquals(3L,comment.getMember().getId());
        assertEquals("www11",comment.getNickname());

    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateComment() throws IOException {
        Member findMember = memberRepository.findById(3L).orElseThrow();

        CreateCommentRequest createCommentRequest = new CreateCommentRequest("안녕");

        commentService.createComment(1L,createCommentRequest.getContent(),findMember);

        UpdateCommentRequest updateCommentRequest = new UpdateCommentRequest("잘가");

        commentService.updateComment(1L,updateCommentRequest,findMember);

        Comment comment = commentRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException(""));

        assertEquals("잘가",comment.getContent());
        assertEquals("www11",comment.getMember().getProfile().getNickname());

    }

    @Test
    @DisplayName("익명 댓글 수정 테스트")
    void updateAnonymousComment() throws IOException {
        Member findMember = memberRepository.findById(3L).orElseThrow();

        CreateCommentRequest createCommentRequest = new CreateCommentRequest("안녕");

        commentService.createAnonymousComment(1L,createCommentRequest.getContent(),findMember);

        UpdateCommentRequest updateCommentRequest = new UpdateCommentRequest("익명 잘가");

        commentService.updateAnonymousComment(1L,updateCommentRequest,findMember);

        AnonymousComment comment = anonymousCommentRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException(""));

        assertEquals("익명 잘가",comment.getContent());
        assertEquals("www11",comment.getMember().getProfile().getNickname());
        assertEquals("www11",comment.getNickname());
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteComment() throws IOException {
        Member findMember = memberRepository.findById(3L).orElseThrow();

        CreateCommentRequest createCommentRequest = new CreateCommentRequest("안녕");

        commentService.createComment(1L,createCommentRequest.getContent(),findMember);

        commentService.deleteComment(1L,findMember);

        Comment comment = commentRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        assertEquals(null,comment);
    }

    @Test
    @DisplayName("익명 댓글 삭제 테스트")
    void deleteAnonymousComment() throws IOException {
        Member findMember = memberRepository.findById(3L).orElseThrow();

        CreateCommentRequest createCommentRequest = new CreateCommentRequest("익명 안녕");

        commentService.createAnonymousComment(1L,createCommentRequest.getContent(),findMember);

        commentService.deleteAnonymousComment(1L,findMember);

        AnonymousComment comment = anonymousCommentRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        assertEquals(null,comment);
    }
}