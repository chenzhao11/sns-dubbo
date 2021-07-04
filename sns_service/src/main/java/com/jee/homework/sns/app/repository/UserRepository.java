package com.jee.homework.sns.app.repository;

import com.jee.homework.sns.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByName(String name);
    int countUserByName(String name);
}
