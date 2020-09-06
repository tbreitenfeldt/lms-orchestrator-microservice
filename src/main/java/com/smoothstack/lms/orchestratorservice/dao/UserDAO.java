package com.smoothstack.lms.orchestratorservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.lms.orchestratorservice.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByEmail(String email);
}