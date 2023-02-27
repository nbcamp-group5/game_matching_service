package com.nbcamp.gamematching.matchingservice.comment.repository;

import com.nbcamp.gamematching.matchingservice.comment.entity.AnonymousComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnonymousCommentRepository extends JpaRepository<AnonymousComment,Long> {

    Page<AnonymousComment> findAllByAnonymousBoardId(Long boardId, Pageable pageable);

    List<AnonymousComment> findAllByAnonymousBoardId(Long boardId);


}
