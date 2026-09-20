package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.AcademicRecord;
import com.demo.entity.Student;
import com.demo.exception.ResourceNotfoundException;
import com.demo.repository.AcademicRecordRepository;
import com.demo.repository.StudentRepository;

@Service
public class AcademicService {

	@Autowired
	private AcademicRecordRepository academicRepo;

	@Autowired
	private StudentRepository studentRepo;

	//Creates the SGPA entry for that semester, or overwrites it if one already exists.
	public AcademicRecord saveSgpa(long studentId, int semester, double sgpa) {
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotfoundException("Record not found with id: " + studentId));

		Optional<AcademicRecord> existing = academicRepo.findByStudentIdAndSemester(studentId, semester);
		AcademicRecord record = existing.orElseGet(AcademicRecord::new);
		record.setStudent(student);
		record.setSemester(semester);
		record.setSgpa(sgpa);
		return academicRepo.save(record);
	}

	public List<AcademicRecord> getRecordsForStudent(long studentId) {
		return academicRepo.findByStudentIdOrderBySemesterAsc(studentId);
	}

	public List<AcademicRecord> getRecordsByEmailOrMobile(String email, String mobile) {
		return academicRepo.findByStudent_EmailOrStudent_MobileOrderBySemesterAsc(email, mobile);
	}

	//CGPA = average of all recorded semester SGPAs.
	public double calculateCgpa(List<AcademicRecord> records) {
		if (records == null || records.isEmpty()) {
			return 0.0;
		}
		double total = 0.0;
		for (AcademicRecord r : records) {
			total += r.getSgpa();
		}
		return Math.round((total / records.size()) * 100.0) / 100.0;
	}

	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}
}
