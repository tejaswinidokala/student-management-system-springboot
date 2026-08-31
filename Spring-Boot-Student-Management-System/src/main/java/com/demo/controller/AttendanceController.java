package com.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entity.Attendance;
import com.demo.entity.AttendanceStatus;
import com.demo.service.AttendanceService;

@Controller
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	//---------- Teacher: mark attendance ----------
	@GetMapping("/attendance/mark")
	public String markAttendanceForm(Model model) {
		model.addAttribute("students", attendanceService.getAllStudents());
		return "attendance_mark";
	}

	@PostMapping("/attendance/save")
	public String saveAttendance(@RequestParam long studentId,
			@RequestParam String attendanceDate,
			@RequestParam AttendanceStatus status,
			Model model) {
		attendanceService.markAttendance(studentId, LocalDate.parse(attendanceDate), status);
		model.addAttribute("students", attendanceService.getAllStudents());
		model.addAttribute("msg", "Attendance recorded");
		return "attendance_mark";
	}

	//---------- Teacher: list / edit / delete ----------
	@GetMapping("/attendance/list")
	public String listAttendance(Model model) {
		model.addAttribute("records", attendanceService.getAllAttendance());
		return "attendance_list";
	}

	@GetMapping("/attendance/edit")
	public String editAttendanceForm(@RequestParam long id, Model model) {
		model.addAttribute("record", attendanceService.getAttendanceById(id));
		return "attendance_edit";
	}

	@PostMapping("/attendance/update")
	public String updateAttendance(@RequestParam long id,
			@RequestParam String attendanceDate,
			@RequestParam AttendanceStatus status,
			Model model) {
		attendanceService.updateAttendance(id, LocalDate.parse(attendanceDate), status);
		model.addAttribute("records", attendanceService.getAllAttendance());
		return "attendance_list";
	}

	@GetMapping("/attendance/delete")
	public String deleteAttendance(@RequestParam long id, Model model) {
		attendanceService.deleteAttendance(id);
		model.addAttribute("records", attendanceService.getAllAttendance());
		return "attendance_list";
	}

	//---------- Student: search own attendance (read only) ----------
	@GetMapping("/attendance/search")
	public String searchAttendanceForm(
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String mobile,
			Model model) {
		if ((email != null && !email.isBlank()) || (mobile != null && !mobile.isBlank())) {
			List<Attendance> records = attendanceService.searchAttendanceByEmailOrMobile(
					email == null ? "" : email, mobile == null ? "" : mobile);
			model.addAttribute("records", records);
			model.addAttribute("searched", true);
		}
		return "attendance_search";
	}
}
