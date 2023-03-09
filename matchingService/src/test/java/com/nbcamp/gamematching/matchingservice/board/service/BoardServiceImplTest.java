package com.nbcamp.gamematching.matchingservice.board.service;

import com.nbcamp.gamematching.matchingservice.board.dto.BoardResponse;
import com.nbcamp.gamematching.matchingservice.board.dto.CreateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.dto.UpdateBoardRequest;
import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.comment.dto.CreateCommentRequest;
import com.nbcamp.gamematching.matchingservice.comment.service.CommentServiceImpl;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @Autowired
    private BoardRepository boardRepository;


    @Autowired
    private MemberRepository memberRepository;


    public static MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "Spring Framework".getBytes());

    @BeforeEach
    public void beforeEach()  {
        Member member = new Member("dddd1@ddd.com", "123456789", new Profile("www11", "sdadad.jpg", Tier.BRONZE, GameType.LOL), MemberRoleEnum.USER,null,null);
        memberRepository.save(member);
        Member member1 = new Member("dddd2@ddd.com", "123456789", new Profile("www12", "sdadad2.jpg", Tier.BRONZE, GameType.LOL), MemberRoleEnum.USER,null,null);
        memberRepository.save(member1);

    }


    @Test
    @DisplayName("게시글 등록 테스트")
    void createBoard()  {
        Member findMember = memberRepository.findById(3L).orElseThrow();
        Member findMember1 = memberRepository.findById(4L).orElseThrow();

        CreateBoardRequest createBoardRequest = new CreateBoardRequest("안녕");

        boardService.createBoard(createBoardRequest,findMember,multipartFile);
        boardService.createBoard(createBoardRequest,findMember1,multipartFile);

        Board board = boardRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException(""));
        Board board1 = boardRepository.findById(2L).orElseThrow(()-> new IllegalArgumentException(""));

        assertEquals("안녕",board.getContent());
        assertEquals("www11", findMember.getProfile().getNickname());
        assertEquals("www12", findMember1.getProfile().getNickname());

    }

    @Test
    @DisplayName("게시글 조회 테스트")
    void getBoardList()  {
        Member findMember = memberRepository.findById(3L).orElseThrow();


        Board boards = new Board("ddddd.jpg","안녕하세요",findMember);

        boardRepository.save(boards);

        CreateCommentRequest createCommentRequest = new CreateCommentRequest("안녕");

        commentServiceImpl.createComment(1L,createCommentRequest.getContent(),findMember);

        List<BoardResponse> boardList = boardService.getBoardList();

        assertEquals(0,boardList.get(0).getLikeCount());
        assertEquals("안녕하세요",boardList.get(0).getContent());
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void updateBoard()  {

        Member findMember = memberRepository.findById(3L).orElseThrow();

        UpdateBoardRequest updateBoardRequest = new UpdateBoardRequest("잘가");

        boardService.updateBoard(1L,updateBoardRequest,findMember, multipartFile);

        Board board = boardRepository.findById(1L).orElseThrow();

        assertEquals("잘가",board.getContent());
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void deleteBoard()  {

        Member findMember = memberRepository.findById(3L).orElseThrow();

        CreateBoardRequest createBoardRequest = new CreateBoardRequest("안녕");

        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "Spring Framework".getBytes());

        boardService.createBoard(createBoardRequest,findMember,multipartFile);

        boardService.deleteBoard(1L,findMember);

        Board board = boardRepository.findById(1L).orElseThrow(()-> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        assertEquals(null,board);

    }
}