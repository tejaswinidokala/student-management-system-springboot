package com.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.util.PasswordUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	//Returns the logged in User if credentials are correct, otherwise null.
	public User validateLogin(String username, String password) {
		Optional<User> opUser = repo.findByUsername(username);
		if (opUser.isPresent()) {
			User user = opUser.get();
			if (user.getPassword().equals(PasswordUtil.hash(password))) {
				return user;
			}
		}
		return null;
	}

	//Self sign-up is only allowed for STUDENT accounts.
	//Teacher accounts are seeded by DataSeeder so random users cannot grant themselves teacher access.
	public boolean registerStudent(String username, String password) {
		if (repo.existsByUsername(username)) {
			return false;
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(PasswordUtil.hash(password));
		user.setRole(Role.STUDENT);
		repo.save(user);
		return true;
	}
}
