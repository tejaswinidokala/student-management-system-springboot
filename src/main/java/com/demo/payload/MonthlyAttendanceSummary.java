package com.demo.payload;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class MonthlyAttendanceSummary {

	private final YearMonth yearMonth;
	private final long presentCount;
	private final long absentCount;
	private final long totalMarked;
	private final double percentage;

	public MonthlyAttendanceSummary(YearMonth yearMonth, long presentCount, long absentCount,
			long totalMarked, double percentage) {
		this.yearMonth = yearMonth;
		this.presentCount = presentCount;
		this.absentCount = absentCount;
		this.totalMarked = totalMarked;
		this.percentage = percentage;
	}

	public String getMonthLabel() {
		return yearMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
	}
	public long getPresentCount() {
		return presentCount;
	}
	public long getAbsentCount() {
		return absentCount;
	}
	public long getTotalMarked() {
		return totalMarked;
	}
	public double getPercentage() {
		return percentage;
	}
}
