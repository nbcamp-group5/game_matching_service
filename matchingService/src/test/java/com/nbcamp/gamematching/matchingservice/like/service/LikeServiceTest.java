package com.nbcamp.gamematching.matchingservice.like.service;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.repository.AnonymousBoardRepository;
import com.nbcamp.gamematching.matchingservice.board.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.like.entity.AnonymousLike;
import com.nbcamp.gamematching.matchingservice.like.entity.Like;
import com.nbcamp.gamematching.matchingservice.like.repository.AnonymousLikeRepository;
import com.nbcamp.gamematching.matchingservice.like.repository.LikeRepository;
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
class LikeServiceTest {
    @Autowired
    private LikeService likeService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AnonymousBoardRepository anonymousBoardRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private AnonymousLikeRepository anonymousLikeRepository;

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
    @DisplayName("게시글 좋아요 테스트")
    void likeBoard() throws IOException {
        Member findMember = memberRepository.findById(3L).orElseThrow();


        likeService.likeBoard(1L,findMember);
//        likeService.likeBoard(1L,member);

        Like like = likeRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException(""));

        assertEquals(1L,like.getBoard().getId());
        assertEquals("dddd@ddd.com",like.getMember().getEmail());

    }

    @Test
    @DisplayName("익명 게시글 좋아요 테스트")
    void likeAnonymousBoard() throws IOException {
        Member findMember = memberRepository.findById(3L).orElseThrow();


        likeService.likeAnonymousBoard(1L,findMember);
//        likeService.likeAnonymousBoard(1L,member);

        AnonymousLike like = anonymousLikeRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException(""));

        assertEquals(1L,like.getAnonymousBoard().getId());
        assertEquals("dddd@ddd.com",like.getMember().getEmail());
    }

    @Test
    @DisplayName("게시글 좋아요 취소 테스트")
    void hateBoard() throws IOException {
        Member findMember = memberRepository.findById(3L).orElseThrow();

        //likeService.hateBoard(1L,findMember);
//        likeService.hateBoard(1L,member);

        Like like = likeRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException(""));

        assertEquals(1L,like.getBoard().getId());
    }

    @Test
    @DisplayName("익명 게시글 좋아요 취소 테스트")
    void hateAnonymousBoard() throws IOException {
        Member findMember = memberRepository.findById(3L).orElseThrow();

        //likeService.hateAnonymousBoard(1L,findMember);
//        likeService.hateAnonymousBoard(1L,member);

        AnonymousLike like = anonymousLikeRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException(""));

        assertEquals(1L,like.getAnonymousBoard().getId());
    }
}