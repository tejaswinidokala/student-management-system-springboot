package com.demo.entity;

import jakarta.persistence.*;

//Stores one SGPA entry per (student, semester).
//CGPA is not stored here - it is calculated on the fly as the average of all
//SGPA entries for a student, so it's always consistent with the latest data.
@Entity
@Table(name = "academic_record", uniqueConstraints = @UniqueConstraint(columnNames = { "student_id", "semester" }))
public class AcademicRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	private int semester;

	private double sgpa;

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
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public double getSgpa() {
		return sgpa;
	}
	public void setSgpa(double sgpa) {
		this.sgpa = sgpa;
	}
}
