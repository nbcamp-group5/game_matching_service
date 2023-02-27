package com.nbcamp.gamematching.matchingservice.like.service;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.repository.AnonymousBoardRepository;
import com.nbcamp.gamematching.matchingservice.board.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.exception.NotFoundException;
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
public class LikeService {

    private final BoardRepository boardRepository;
    private final AnonymousBoardRepository anonymousBoardRepository;
    private final LikeRepository likeRepository;
    private final AnonymousLikeRepository anonymousLikeRepository;

    //게시글 좋아요
//    @Transactional
//    public void likeBoard(Long boardId, Member member) {
//        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NotFoundException());
//        Optional<Like> optionalLike = likeRepository.findById(boardId);
//        if (optionalLike.isPresent()) {
//            throw new IllegalArgumentException("이미 좋아요를 누르셨습니다.");
//        }
//        Like like = new Like(board,member);
//        likeRepository.save(like);
//    }

    //일반 게시글 좋아요
    @Transactional
    public ResponseEntity<String> likeBoard(Long boardId, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NotFoundException());
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
        AnonymousBoard board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new NotFoundException());
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


//    //게시글
//    @Transactional
//    public void hateBoard(Long boardId,Member member){
//        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NotFoundException());
//        Like like = likeRepository.findById(member.getId()).orElseThrow(()-> new NotFoundException());
//        like.checkUser(like,member); //좋아요를 누른사람이 맞는지 확인하는 로직
//        Optional<Like> optionalLike = likeRepository.findById(board.getId());
//        if (!optionalLike.isPresent()) {
//            throw new IllegalArgumentException("좋아요를 누르신 적이 없습니다.");
//        }
//        likeRepository.delete(optionalLike.get());
//    }
//
//    //익명 게시글 좋아요 취소
//    @Transactional
//    public void hateAnonymousBoard(Long boardId,Member member){
//        AnonymousBoard board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
//        AnonymousLike anonymousLike = anonymousLikeRepository.findById(member.getId()).orElseThrow(()-> new IllegalArgumentException(""));
//        anonymousLike.checkUser(anonymousLike,member);
//        Optional<AnonymousLike> optionalLike = anonymousLikeRepository.findById(board.getId());
//        if (!optionalLike.isPresent()) {
//            throw new IllegalArgumentException("좋아요를 누르신 적이 없습니다.");
//        }
//        anonymousLikeRepository.delete(optionalLike.get());
//    }
}
