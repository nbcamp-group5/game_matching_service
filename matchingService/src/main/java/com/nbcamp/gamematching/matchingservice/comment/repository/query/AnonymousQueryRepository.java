package com.nbcamp.gamematching.matchingservice.comment.repository.query;

import com.nbcamp.gamematching.matchingservice.comment.dto.AnonymousCommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnonymousQueryRepository {

    Page<AnonymousCommentResponse> findAnonymousComments(Long boardId, Pageable pageable);

    List<AnonymousCommentResponse> findAnonymousComments(Long boardId);
}
