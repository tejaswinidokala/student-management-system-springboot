<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher Dashboard</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<span>Signed in as ${username} (Teacher)</span>
			<a href="logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<h2>Teacher Dashboard</h2>
		<p>You have full access: register, search, update, delete students and manage attendance.</p>
		<div class="dashboard-grid">
			<a class="dashboard-tile" href="view">
				<h3>Register Student</h3>
				<p>Add a new student record.</p>
			</a>
			<a class="dashboard-tile" href="findReg">
				<h3>Search / Manage Students</h3>
				<p>Search, update or delete existing student records.</p>
			</a>
			<a class="dashboard-tile" href="attendance/mark">
				<h3>Mark Attendance</h3>
				<p>Record today's attendance for a student.</p>
			</a>
			<a class="dashboard-tile" href="attendance/list">
				<h3>View / Edit Attendance</h3>
				<p>Browse all attendance records, edit or delete entries.</p>
			</a>
		</div>
	</div>
</body>
</html>
