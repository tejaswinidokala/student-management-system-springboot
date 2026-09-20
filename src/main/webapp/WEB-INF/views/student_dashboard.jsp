<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Student Dashboard</title>
<link rel="stylesheet" href="/css/style.css">
<script src="/js/theme.js" defer></script>
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<span>Signed in as ${username} (Student)</span>
			<button class="theme-toggle" type="button">Dark mode</button>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<h2>Student Dashboard</h2>
		<p>You can search student records and check your own attendance and academic results.</p>
		<div class="dashboard-grid">
			<a class="dashboard-tile" href="/findReg">
				<h3>Search Students</h3>
				<p>Look up students by name, course, email or mobile.</p>
			</a>
			<a class="dashboard-tile" href="/attendance/search">
				<h3>Check Attendance</h3>
				<p>Search attendance records by your email or mobile.</p>
			</a>
			<a class="dashboard-tile" href="/attendance/monthly-search">
				<h3>Monthly Attendance</h3>
				<p>See month-by-month present/absent counts till today.</p>
			</a>
			<a class="dashboard-tile" href="/academic/search">
				<h3>My SGPA / CGPA</h3>
				<p>View your semester-wise SGPA and overall CGPA.</p>
			</a>
		</div>
	</div>
</body>
</html>
