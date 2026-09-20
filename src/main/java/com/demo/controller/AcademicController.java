package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entity.AcademicRecord;
import com.demo.service.AcademicService;

@Controller
public class AcademicController {

	@Autowired
	private AcademicService academicService;

	//---------- Teacher: add / update SGPA for a semester ----------
	@GetMapping("/academic/add")
	public String addSgpaForm(@RequestParam(required = false) Long studentId, Model model) {
		model.addAttribute("students", academicService.getAllStudents());
		if (studentId != null) {
			List<AcademicRecord> records = academicService.getRecordsForStudent(studentId);
			model.addAttribute("records", records);
			model.addAttribute("cgpa", academicService.calculateCgpa(records));
			model.addAttribute("selectedStudentId", studentId);
		}
		return "academic_add";
	}

	@PostMapping("/academic/save")
	public String saveSgpa(@RequestParam long studentId, @RequestParam int semester,
			@RequestParam double sgpa, Model model) {
		academicService.saveSgpa(studentId, semester, sgpa);
		List<AcademicRecord> records = academicService.getRecordsForStudent(studentId);
		model.addAttribute("students", academicService.getAllStudents());
		model.addAttribute("records", records);
		model.addAttribute("cgpa", academicService.calculateCgpa(records));
		model.addAttribute("selectedStudentId", studentId);
		model.addAttribute("msg", "SGPA saved");
		return "academic_add";
	}

	//---------- Student: view own SGPA/CGPA (read only), searched by email/mobile ----------
	@GetMapping("/academic/search")
	public String searchAcademicForm(
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String mobile,
			Model model) {
		if ((email != null && !email.isBlank()) || (mobile != null && !mobile.isBlank())) {
			List<AcademicRecord> records = academicService.getRecordsByEmailOrMobile(
					email == null ? "" : email, mobile == null ? "" : mobile);
			model.addAttribute("records", records);
			model.addAttribute("cgpa", academicService.calculateCgpa(records));
			model.addAttribute("searched", true);
		}
		return "academic_search";
	}
}
