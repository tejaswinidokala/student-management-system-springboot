package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.util.PasswordUtil;

//Creates two default login accounts the first time the app runs against an empty database,
//so you can log in immediately without registering a teacher manually.
//Default Teacher login  -> username: teacher | password: teacher123
//Default Student login  -> username: student | password: student123
//(New students can also self sign-up from the /signup page.)
@Component
public class DataSeeder implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) {
		if (!userRepository.existsByUsername("teacher")) {
			User teacher = new User();
			teacher.setUsername("teacher");
			teacher.setPassword(PasswordUtil.hash("teacher123"));
			teacher.setRole(Role.TEACHER);
			userRepository.save(teacher);
		}

		if (!userRepository.existsByUsername("student")) {
			User student = new User();
			student.setUsername("student");
			student.setPassword(PasswordUtil.hash("student123"));
			student.setRole(Role.STUDENT);
			userRepository.save(student);
		}
	}
}
