package com.jee.homework.sns.app.repository;

import com.jee.homework.sns.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
