<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Attendance</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<a href="/teacherDashboard">Dashboard</a>
			<a href="/attendance/list">Back to list</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<div class="card">
			<h2>Edit Attendance - ${record.student.name}</h2>
			<form class="styled-form" action="/attendance/update" method="post">
				<input type="hidden" name="id" value="${record.id}" />
				<div>
					<label for="attendanceDate">Date</label>
					<input id="attendanceDate" type="date" name="attendanceDate" value="${record.attendanceDate}" required />
				</div>
				<div>
					<label for="status">Status</label>
					<select id="status" name="status" required>
						<option value="PRESENT" ${record.status == 'PRESENT' ? 'selected' : ''}>Present</option>
						<option value="ABSENT" ${record.status == 'ABSENT' ? 'selected' : ''}>Absent</option>
					</select>
				</div>
				<button type="submit">Update</button>
			</form>
		</div>
	</div>
</body>
</html>
