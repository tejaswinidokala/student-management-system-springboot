<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Dashboard</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<span>Signed in as ${username} (Student)</span>
			<a href="logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<h2>Student Dashboard</h2>
		<p>You can register a new student record and search existing records.</p>
		<div class="dashboard-grid">
			<a class="dashboard-tile" href="view">
				<h3>Register</h3>
				<p>Create a new student registration.</p>
			</a>
			<a class="dashboard-tile" href="findReg">
				<h3>Search Students</h3>
				<p>Look up students by name, course, email or mobile.</p>
			</a>
			<a class="dashboard-tile" href="attendance/search">
				<h3>Check Attendance</h3>
				<p>Search attendance records by your email or mobile.</p>
			</a>
		</div>
	</div>
</body>
</html>
