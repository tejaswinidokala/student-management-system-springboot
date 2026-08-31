package com.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//Linked to your existing Student entity - Student.java is not modified.
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	private LocalDate attendanceDate;

	@Enumerated(EnumType.STRING)
	private AttendanceStatus status;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public AttendanceStatus getStatus() {
		return status;
	}
	public void setStatus(AttendanceStatus status) {
		this.status = status;
	}
}
