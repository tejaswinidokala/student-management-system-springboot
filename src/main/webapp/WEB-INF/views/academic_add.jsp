<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SGPA / CGPA</title>
<link rel="stylesheet" href="/css/style.css">
<script src="/js/theme.js" defer></script>
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<button class="theme-toggle" type="button">Dark mode</button>
			<a href="/teacherDashboard">Dashboard</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<div class="card">
			<h2>Add / Update SGPA</h2>

			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

			<form class="styled-form" action="/academic/save" method="post">
				<div>
					<label for="studentId">Student</label>
					<select id="studentId" name="studentId" required onchange="location.href='/academic/add?studentId=' + this.value">
						<option value="">-- select student --</option>
						<c:forEach var="student" items="${students}">
							<option value="${student.id}" ${selectedStudentId == student.id ? 'selected' : ''}>${student.name} (${student.email})</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label for="semester">Semester</label>
					<input id="semester" type="text" name="semester" placeholder="e.g. 1" required />
				</div>
				<div>
					<label for="sgpa">SGPA</label>
					<input id="sgpa" type="text" name="sgpa" placeholder="e.g. 8.5" required />
				</div>
				<button type="submit">Save</button>
			</form>
		</div>

		<c:if test="${not empty selectedStudentId}">
			<div class="card">
				<h3>Current Results</h3>
				<p><b>CGPA: ${cgpa}</b></p>
				<table>
					<tr>
						<th>Semester</th>
						<th>SGPA</th>
					</tr>
					<c:forEach var="r" items="${records}">
						<tr>
							<td>${r.semester}</td>
							<td>${r.sgpa}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>
