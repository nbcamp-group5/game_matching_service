package com.nbcamp.gamematching.matchingservice.comment.repository;

import com.nbcamp.gamematching.matchingservice.comment.entity.Comment;
import com.nbcamp.gamematching.matchingservice.comment.repository.query.CommentQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long>, CommentQueryRepository {

    Page<Comment> findAllByBoardId(Long boardId, Pageable pageable);

    List<Comment> findAllByBoardId(Long boardId);

    void deleteAllByBoardId(Long boardId);


}
