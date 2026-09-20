<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Attendance Records</title>
<link rel="stylesheet" href="/css/style.css">
<script src="/js/theme.js" defer></script>
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<button class="theme-toggle" type="button">Dark mode</button>
			<a href="/teacherDashboard">Dashboard</a>
			<a href="/attendance/mark">Mark Attendance</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<div class="card">
			<h2>All Attendance Records</h2>
			<table>
				<tr>
					<th>Student</th>
					<th>Email</th>
					<th>Date</th>
					<th>Status</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach var="record" items="${records}">
					<tr>
						<td>${record.student.name}</td>
						<td>${record.student.email}</td>
						<td>${record.attendanceDate}</td>
						<td>
							<c:choose>
								<c:when test="${record.status == 'PRESENT'}">
									<span class="badge badge-present">Present</span>
								</c:when>
								<c:otherwise>
									<span class="badge badge-absent">Absent</span>
								</c:otherwise>
							</c:choose>
						</td>
						<td><a href="/attendance/edit?id=${record.id}">edit</a></td>
						<td><a href="/attendance/delete?id=${record.id}">delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
