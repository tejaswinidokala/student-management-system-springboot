package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	List<Attendance> findByStudentId(long studentId);

	List<Attendance> findByStudent_EmailOrStudent_Mobile(String email, String mobile);

	List<Attendance> findAllByOrderByAttendanceDateDesc();
}
