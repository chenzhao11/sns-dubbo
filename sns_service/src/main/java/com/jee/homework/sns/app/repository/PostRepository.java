package com.jee.homework.sns.app.repository;

import com.jee.homework.sns.app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
