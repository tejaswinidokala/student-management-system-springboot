package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/doLogin")
	public String doLogin(@RequestParam String username, @RequestParam String password,
			HttpSession session, Model model) {
		User user = userService.validateLogin(username, password);
		if (user == null) {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
		session.setAttribute("username", user.getUsername());
		session.setAttribute("role", user.getRole().name());
		return user.getRole() == Role.TEACHER ? "redirect:/teacherDashboard" : "redirect:/studentDashboard";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}

	//Self sign-up always creates a STUDENT account (see UserService).
	@PostMapping("/doSignup")
	public String doSignup(@RequestParam String username, @RequestParam String password, Model model) {
		boolean created = userService.registerStudent(username, password);
		if (!created) {
			model.addAttribute("error", "That username is already taken");
			return "signup";
		}
		model.addAttribute("msg", "Account created! Please log in.");
		return "login";
	}
}
