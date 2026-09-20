package com.demo.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entity.Attendance;
import com.demo.entity.AttendanceStatus;
import com.demo.entity.Student;
import com.demo.service.AttendanceService;

@Controller
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	//---------- Teacher: mark attendance for one student at a time ----------
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

	//---------- Teacher: mark the WHOLE CLASS at once for a single date ----------
	@GetMapping("/attendance/mark-all")
	public String markAllForm(@RequestParam(required = false) String attendanceDate, Model model) {
		LocalDate date = (attendanceDate != null && !attendanceDate.isBlank())
				? LocalDate.parse(attendanceDate) : LocalDate.now();
		model.addAttribute("students", attendanceService.getAllStudents());
		model.addAttribute("selectedDate", date.toString());
		model.addAttribute("existing", attendanceService.getStatusMapForDate(date));
		return "attendance_mark_all";
	}

	@PostMapping("/attendance/mark-all/save")
	public String saveAll(@RequestParam String attendanceDate,
			@RequestParam Map<String, String> allParams,
			Model model) {
		LocalDate date = LocalDate.parse(attendanceDate);

		//Every student row on the page posts a field named "status_<studentId>".
		//Only students that actually had a status chosen get recorded.
		Map<Long, AttendanceStatus> statusByStudentId = new HashMap<>();
		for (Student student : attendanceService.getAllStudents()) {
			String value = allParams.get("status_" + student.getId());
			if (value != null && !value.isBlank()) {
				statusByStudentId.put(student.getId(), AttendanceStatus.valueOf(value));
			}
		}
		int count = attendanceService.markBulkAttendance(date, statusByStudentId);

		model.addAttribute("students", attendanceService.getAllStudents());
		model.addAttribute("selectedDate", date.toString());
		model.addAttribute("existing", attendanceService.getStatusMapForDate(date));
		model.addAttribute("msg", "Attendance saved for " + count + " student(s) on " + date);
		return "attendance_mark_all";
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

	//---------- Teacher: monthly attendance report for a chosen student ----------
	@GetMapping("/attendance/monthly")
	public String monthlyAttendanceForTeacher(@RequestParam(required = false) Long studentId, Model model) {
		model.addAttribute("students", attendanceService.getAllStudents());
		if (studentId != null) {
			model.addAttribute("summary", attendanceService.getMonthlySummaryForStudent(studentId));
			model.addAttribute("selectedStudentId", studentId);
		}
		return "attendance_monthly";
	}

	//---------- Student: monthly attendance report for themselves (by email/mobile) ----------
	@GetMapping("/attendance/monthly-search")
	public String monthlyAttendanceForStudent(
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String mobile,
			Model model) {
		if ((email != null && !email.isBlank()) || (mobile != null && !mobile.isBlank())) {
			model.addAttribute("summary", attendanceService.getMonthlySummaryByEmailOrMobile(
					email == null ? "" : email, mobile == null ? "" : mobile));
			model.addAttribute("searched", true);
		}
		return "attendance_monthly_search";
	}
}
