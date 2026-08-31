package com.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Attendance;
import com.demo.entity.AttendanceStatus;
import com.demo.entity.Student;
import com.demo.exception.ResourceNotfoundException;
import com.demo.repository.AttendanceRepository;
import com.demo.repository.StudentRepository;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepo;

	@Autowired
	private StudentRepository studentRepo;

	public Attendance markAttendance(long studentId, LocalDate date, AttendanceStatus status) {
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotfoundException("Record not found with id: " + studentId));
		Attendance attendance = new Attendance();
		attendance.setStudent(student);
		attendance.setAttendanceDate(date);
		attendance.setStatus(status);
		return attendanceRepo.save(attendance);
	}

	public List<Attendance> getAllAttendance() {
		return attendanceRepo.findAllByOrderByAttendanceDateDesc();
	}

	public Attendance getAttendanceById(long id) {
		return attendanceRepo.findById(id)
				.orElseThrow(() -> new ResourceNotfoundException("Attendance record not found with id: " + id));
	}

	public Attendance updateAttendance(long id, LocalDate date, AttendanceStatus status) {
		Attendance attendance = getAttendanceById(id);
		attendance.setAttendanceDate(date);
		attendance.setStatus(status);
		return attendanceRepo.save(attendance);
	}

	public void deleteAttendance(long id) {
		attendanceRepo.deleteById(id);
	}

	//Used by a student to search only their own attendance, by email or mobile.
	public List<Attendance> searchAttendanceByEmailOrMobile(String email, String mobile) {
		return attendanceRepo.findByStudent_EmailOrStudent_Mobile(email, mobile);
	}

	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}
}
