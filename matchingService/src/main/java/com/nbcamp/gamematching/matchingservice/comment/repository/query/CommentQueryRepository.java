package com.nbcamp.gamematching.matchingservice.comment.repository.query;

import com.nbcamp.gamematching.matchingservice.comment.dto.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentQueryRepository {

    Page<CommentResponse> findComments(Long boardId, Pageable pageable);

    List<CommentResponse> findComments(Long boardId);

}
