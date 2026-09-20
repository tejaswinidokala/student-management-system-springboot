package com.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	List<Attendance> findByStudentId(long studentId);

	List<Attendance> findByStudent_EmailOrStudent_Mobile(String email, String mobile);

	List<Attendance> findAllByOrderByAttendanceDateDesc();

	Optional<Attendance> findByStudentIdAndAttendanceDate(long studentId, LocalDate attendanceDate);

	List<Attendance> findByAttendanceDate(LocalDate attendanceDate);
}
