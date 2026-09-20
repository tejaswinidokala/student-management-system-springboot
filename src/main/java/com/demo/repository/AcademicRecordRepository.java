package com.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.AcademicRecord;

public interface AcademicRecordRepository extends JpaRepository<AcademicRecord, Long> {

	List<AcademicRecord> findByStudentIdOrderBySemesterAsc(long studentId);

	List<AcademicRecord> findByStudent_EmailOrStudent_MobileOrderBySemesterAsc(String email, String mobile);

	Optional<AcademicRecord> findByStudentIdAndSemester(long studentId, int semester);
}
