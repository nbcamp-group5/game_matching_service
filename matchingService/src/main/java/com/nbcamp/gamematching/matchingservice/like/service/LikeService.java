package com.nbcamp.gamematching.matchingservice.like.service;

import com.nbcamp.gamematching.matchingservice.board.entity.AnonymousBoard;
import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.repository.AnonymousBoardRepository;
import com.nbcamp.gamematching.matchingservice.board.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.like.entity.AnonymousLikes;
import com.nbcamp.gamematching.matchingservice.like.entity.Like;
import com.nbcamp.gamematching.matchingservice.like.repository.AnonymousLikeRepository;
import com.nbcamp.gamematching.matchingservice.like.repository.LikeRepository;
import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final BoardRepository boardRepository;
    private final AnonymousBoardRepository anonymousBoardRepository;
    private final LikeRepository likeRepository;
    private final AnonymousLikeRepository anonymousLikeRepository;

    //게시글 좋아요
    @Transactional
    public void likeBoard(Long boardId, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        Optional<Like> optionalLike = likeRepository.findById(boardId);
        if (optionalLike.isPresent()) {
            throw new IllegalArgumentException("이미 좋아요를 누르셨습니다.");
        }
        Like like = new Like(board,member);
        likeRepository.save(like);
    }

    //익명 게시글 좋아요
    @Transactional
    public void likeAnonymousBoard(Long boardId, Member member) {
        AnonymousBoard board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        Optional<AnonymousLikes> optionalLike = anonymousLikeRepository.findById(boardId);
        if (optionalLike.isPresent()) {
            throw new IllegalArgumentException("이미 좋아요를 누르셨습니다.");
        }
        AnonymousLikes likes = new AnonymousLikes(board,member);
        anonymousLikeRepository.save(likes);
    }


    //게시글
    @Transactional
    public void hateBoard(Long boardId,Member member){
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        Like like = likeRepository.findById(member.getId()).orElseThrow(()-> new IllegalArgumentException(""));
        like.checkUser(like,member); //좋아요를 누른사람이 맞는지 확인하는 로직
        Optional<Like> optionalLike = likeRepository.findById(board.getId());
        if (!optionalLike.isPresent()) {
            throw new IllegalArgumentException("좋아요를 누르신 적이 없습니다.");
        }
        likeRepository.delete(optionalLike.get());
    }

    //익명 게시글 좋아요 취소
    @Transactional
    public void hateAnonymousBoard(Long boardId,Member member){
        AnonymousBoard board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        AnonymousLikes anonymousLikes = anonymousLikeRepository.findById(member.getId()).orElseThrow(()-> new IllegalArgumentException(""));
        anonymousLikes.checkUser(anonymousLikes,member);
        Optional<AnonymousLikes> optionalLike = anonymousLikeRepository.findById(board.getId());
        if (!optionalLike.isPresent()) {
            throw new IllegalArgumentException("좋아요를 누르신 적이 없습니다.");
        }
        anonymousLikeRepository.delete(optionalLike.get());
    }
}
