package com.anil.test_ms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anil.test_ms.entity.User;
import com.anil.test_ms.service.UserService;

/**
 * Controller class for test-ms
 * 
 * @author eInfochips
 *
 */
@RestController
public class UserController {

	private static final String NO_USER_FOUND = "No User found";

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping(value = "addUser")
	public User addUser(@RequestParam("name") String name) {
		User user = userService.addUser(name);
		LOGGER.trace("addUser---> {}", user);
		return user;
	}

	@GetMapping(value = "getUser/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId) {
		User user = userService.getUser(userId);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "listUser")
	public ResponseEntity<List<User>> listUser() {
		List<User> listOfUser = userService.listUser();
		if (listOfUser != null) {
			return new ResponseEntity<>(listOfUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping(value = "deleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		User user = userService.getUser(userId);
		if (user != null) {
			userService.deleteUser(user);
			return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(NO_USER_FOUND, HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping(value = "updateUser/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestParam("name") String name) {
		User user = userService.addUser(name);
		if (user != null) {
			User updatedUser = userService.updateUser(user, name);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}