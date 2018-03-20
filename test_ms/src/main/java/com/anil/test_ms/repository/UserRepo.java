package com.anil.test_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anil.test_ms.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByUserId(Long userId);
}
