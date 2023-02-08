package com.nbcamp.gamematching.matchingservice.like.service;

import com.nbcamp.gamematching.matchingservice.board.entity.Board;
import com.nbcamp.gamematching.matchingservice.board.repository.AnonymousBoardRepository;
import com.nbcamp.gamematching.matchingservice.board.repository.BoardRepository;
import com.nbcamp.gamematching.matchingservice.like.entity.Like;
import com.nbcamp.gamematching.matchingservice.like.repository.LikeRepository;
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

    //게시글 좋아요
    @Transactional
    public void likeBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        Optional<Like> optionalLike = likeRepository.findById(boardId);
        if (optionalLike.isPresent()) {
            throw new IllegalArgumentException("이미 좋아요를 누르셨습니다.");
        }
        Like like = new Like(board);
        likeRepository.save(like);
    }

    //익명 게시글 좋아요
    @Transactional
    public void likeAnonymousBoard(Long boardId) {
        Board board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        Optional<Like> optionalLike = likeRepository.findById(boardId);
        if (optionalLike.isPresent()) {
            throw new IllegalArgumentException("이미 좋아요를 누르셨습니다.");
        }
        Like like = new Like(board);
        likeRepository.save(like);
    }


    //게시글 좋아요 취소
    @Transactional
    public void hateBoard(Long boardId){
        Board board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        Optional<Like> optionalLike = likeRepository.findById(board.getId());
        if (!optionalLike.isPresent()) {
            throw new IllegalArgumentException("좋아요를 누르신 적이 없습니다.");
        }
        likeRepository.delete(optionalLike.get());
    }

    //익명 게시글 좋아요 취소
    @Transactional
    public void hateAnonymousBoard(Long boardId){
        Board board = anonymousBoardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(""));
        Optional<Like> optionalLike = likeRepository.findById(board.getId());
        if (!optionalLike.isPresent()) {
            throw new IllegalArgumentException("좋아요를 누르신 적이 없습니다.");
        }
        likeRepository.delete(optionalLike.get());
    }
}
