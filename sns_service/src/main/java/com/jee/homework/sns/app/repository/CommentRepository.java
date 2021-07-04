package com.jee.homework.sns.app.repository;

import com.jee.homework.sns.app.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
