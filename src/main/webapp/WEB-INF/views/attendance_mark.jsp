<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mark Attendance</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<a href="/teacherDashboard">Dashboard</a>
			<a href="/attendance/list">View Attendance</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<div class="card">
			<h2>Mark Attendance</h2>

			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

			<form class="styled-form" action="/attendance/save" method="post">
				<div>
					<label for="studentId">Student</label>
					<select id="studentId" name="studentId" required>
						<option value="">-- select student --</option>
						<c:forEach var="student" items="${students}">
							<option value="${student.id}">${student.name} (${student.email})</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label for="attendanceDate">Date</label>
					<input id="attendanceDate" type="date" name="attendanceDate" required />
				</div>
				<div>
					<label for="status">Status</label>
					<select id="status" name="status" required>
						<option value="PRESENT">Present</option>
						<option value="ABSENT">Absent</option>
					</select>
				</div>
				<button type="submit">Save</button>
			</form>
		</div>
	</div>
</body>
</html>
