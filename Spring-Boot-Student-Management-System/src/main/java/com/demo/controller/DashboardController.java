package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

	@GetMapping("/")
	public String home(HttpSession session) {
		String role = (String) session.getAttribute("role");
		if (role == null) {
			return "redirect:/login";
		}
		return "TEACHER".equals(role) ? "redirect:/teacherDashboard" : "redirect:/studentDashboard";
	}

	@GetMapping("/teacherDashboard")
	public String teacherDashboard(HttpSession session, Model model) {
		model.addAttribute("username", session.getAttribute("username"));
		return "teacher_dashboard";
	}

	@GetMapping("/studentDashboard")
	public String studentDashboard(HttpSession session, Model model) {
		model.addAttribute("username", session.getAttribute("username"));
		return "student_dashboard";
	}
}
