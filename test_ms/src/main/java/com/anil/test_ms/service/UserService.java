package com.anil.test_ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anil.test_ms.entity.User;
import com.anil.test_ms.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public User addUser(String name) {
		User user = new User();
		user.setName(name);
		return userRepo.save(user);
	}

	public User getUser(Long userId) {
		return userRepo.findByUserId(userId);
	}

	public void deleteUser(User user) {
		userRepo.delete(user);
	}

	public User updateUser(User user, String name) {
		user.setName(name);
		return userRepo.save(user);
	}

	public List<User> listUser() {
		return userRepo.findAll();
	}
}