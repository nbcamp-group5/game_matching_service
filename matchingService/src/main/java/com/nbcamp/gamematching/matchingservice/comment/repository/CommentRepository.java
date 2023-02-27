package com.nbcamp.gamematching.matchingservice.comment.repository;

import com.nbcamp.gamematching.matchingservice.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long> {

    Page<Comment> findAllByBoardId(Long boardId, Pageable pageable);

    List<Comment> findAllByBoardId(Long boardId);

}
