package com.jee.homework.sns.app.repository;

import com.jee.homework.sns.app.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from message where from_user_id = ?1 or to_user_id = ?1",nativeQuery = true)
    public void deleteByUserId(Long id);
}
