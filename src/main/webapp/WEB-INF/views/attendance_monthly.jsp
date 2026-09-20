<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Monthly Attendance Report</title>
<link rel="stylesheet" href="/css/style.css">
<script src="/js/theme.js" defer></script>
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<button class="theme-toggle" type="button">Dark mode</button>
			<a href="/teacherDashboard">Dashboard</a>
			<a href="/attendance/list">All Attendance</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<div class="card">
			<h2>Monthly Attendance Report</h2>
			<form class="styled-form">
				<div>
					<label for="studentId">Student</label>
					<select id="studentId" name="studentId" onchange="location.href='/attendance/monthly?studentId=' + this.value">
						<option value="">-- select student --</option>
						<c:forEach var="student" items="${students}">
							<option value="${student.id}" ${selectedStudentId == student.id ? 'selected' : ''}>${student.name} (${student.email})</option>
						</c:forEach>
					</select>
				</div>
			</form>
		</div>

		<c:if test="${not empty selectedStudentId}">
			<c:choose>
				<c:when test="${empty summary}">
					<div class="card">
						<p>No attendance has been marked for this student yet.</p>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="month" items="${summary}">
						<div class="card">
							<h3>${month.monthLabel}</h3>
							<p>
								Present: ${month.presentCount} &nbsp;|&nbsp;
								Absent: ${month.absentCount} &nbsp;|&nbsp;
								Total marked (till date): ${month.totalMarked} &nbsp;|&nbsp;
								Attendance: ${month.percentage}%
							</p>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
</body>
</html>
