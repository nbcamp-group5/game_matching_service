package com.nbcamp.gamematching.matchingservice.like.service;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.service.AnonymousBoardService;
import com.nbcamp.gamematching.matchingservice.board.service.BoardService;
import com.nbcamp.gamematching.matchingservice.like.entity.AnonymousLike;
import com.nbcamp.gamematching.matchingservice.like.entity.Like;
import com.nbcamp.gamematching.matchingservice.like.repository.AnonymousLikeRepository;
import com.nbcamp.gamematching.matchingservice.like.repository.LikeRepository;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final BoardService boardService;
    private final AnonymousBoardService anonymousBoardService;
    private final LikeRepository likeRepository;
    private final AnonymousLikeRepository anonymousLikeRepository;


    //일반 게시글 좋아요
    @Transactional
    public ResponseEntity<String> likeBoard(Long boardId, Member member) {
        Board board = boardService.findBoard(boardId);
        if(!likeRepository.existsByMemberAndBoard(member,board)){
            Like like = new Like(board,member);
            likeRepository.save(like);
            return new ResponseEntity<>("좋아요", HttpStatus.OK);
        }
        else{
            likeRepository.deleteByMemberAndBoard(member,board);
            return new ResponseEntity<>("좋아요 취소",HttpStatus.OK);
        }
    }


    //익명 게시글 좋아요
    @Transactional
    public ResponseEntity<String> likeAnonymousBoard(Long boardId, Member member) {
        AnonymousBoard board = anonymousBoardService.findBoard(boardId);
        if(!anonymousLikeRepository.existsByMemberAndAnonymousBoard(member,board)){
            AnonymousLike like = new AnonymousLike(board,member);
            anonymousLikeRepository.save(like);
            return new ResponseEntity<>("좋아요", HttpStatus.OK);
        }
        else{
            anonymousLikeRepository.deleteByMemberAndAnonymousBoard(member,board);
            return new ResponseEntity<>("좋아요 취소",HttpStatus.OK);
        }
    }
}
