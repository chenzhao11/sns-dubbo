package com.jee.homework.sns.app.repository;

import com.jee.homework.sns.app.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
}
