package com.nbcamp.gamematching.matchingservice.like.service;

import com.nbcamp.gamematching.matchingservice.member.entity.Member;
import org.springframework.http.ResponseEntity;

public interface LikeService {

    ResponseEntity<String> likeBoard(Long boardId, Member member);

    ResponseEntity<String> likeAnonymousBoard(Long boardId, Member member);
}
