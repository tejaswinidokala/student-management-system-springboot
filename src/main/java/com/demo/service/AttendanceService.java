package com.demo.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Attendance;
import com.demo.entity.AttendanceStatus;
import com.demo.entity.Student;
import com.demo.exception.ResourceNotfoundException;
import com.demo.payload.MonthlyAttendanceSummary;
import com.demo.repository.AttendanceRepository;
import com.demo.repository.StudentRepository;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepo;

	@Autowired
	private StudentRepository studentRepo;

	//Upsert: if this student already has an entry for this date, it is overwritten
	//instead of creating a duplicate row.
	public Attendance markAttendance(long studentId, LocalDate date, AttendanceStatus status) {
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotfoundException("Record not found with id: " + studentId));
		Attendance attendance = attendanceRepo.findByStudentIdAndAttendanceDate(studentId, date)
				.orElseGet(Attendance::new);
		attendance.setStudent(student);
		attendance.setAttendanceDate(date);
		attendance.setStatus(status);
		return attendanceRepo.save(attendance);
	}

	//---------- Mark the whole class at once for a single date ----------
	//statusByStudentId only needs to contain entries for students that were actually marked;
	//any student left unmarked on the page is simply skipped.
	public int markBulkAttendance(LocalDate date, Map<Long, AttendanceStatus> statusByStudentId) {
		int count = 0;
		for (Map.Entry<Long, AttendanceStatus> entry : statusByStudentId.entrySet()) {
			markAttendance(entry.getKey(), date, entry.getValue());
			count++;
		}
		return count;
	}

	//Used to pre-fill the bulk-mark page with whatever was already recorded for that date,
	//so re-opening the page (e.g. to fix a mistake) shows the current state, not a blank form.
	public Map<Long, AttendanceStatus> getStatusMapForDate(LocalDate date) {
		Map<Long, AttendanceStatus> map = new HashMap<>();
		for (Attendance a : attendanceRepo.findByAttendanceDate(date)) {
			map.put(a.getStudent().getId(), a.getStatus());
		}
		return map;
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

	//---------- Monthly attendance summary (present/absent tally up to today) ----------
	public List<MonthlyAttendanceSummary> getMonthlySummaryForStudent(long studentId) {
		return buildMonthlySummary(attendanceRepo.findByStudentId(studentId));
	}

	public List<MonthlyAttendanceSummary> getMonthlySummaryByEmailOrMobile(String email, String mobile) {
		return buildMonthlySummary(attendanceRepo.findByStudent_EmailOrStudent_Mobile(email, mobile));
	}

	private List<MonthlyAttendanceSummary> buildMonthlySummary(List<Attendance> records) {
		LocalDate today = LocalDate.now();
		//TreeMap keeps months in chronological order automatically.
		Map<YearMonth, long[]> tally = new TreeMap<>();

		for (Attendance a : records) {
			if (a.getAttendanceDate() == null || a.getAttendanceDate().isAfter(today)) {
				continue; // only count attendance up to today
			}
			YearMonth ym = YearMonth.from(a.getAttendanceDate());
			long[] counts = tally.computeIfAbsent(ym, k -> new long[2]); // [present, absent]
			if (a.getStatus() == AttendanceStatus.PRESENT) {
				counts[0]++;
			} else {
				counts[1]++;
			}
		}

		List<MonthlyAttendanceSummary> result = new ArrayList<>();
		for (Map.Entry<YearMonth, long[]> entry : tally.entrySet()) {
			long present = entry.getValue()[0];
			long absent = entry.getValue()[1];
			long total = present + absent;
			double percentage = total == 0 ? 0.0 : Math.round((present * 10000.0 / total)) / 100.0;
			result.add(new MonthlyAttendanceSummary(entry.getKey(), present, absent, total, percentage));
		}

		Collections.reverse(result); // most recent month first
		return result;
	}
}
