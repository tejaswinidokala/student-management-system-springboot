<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Teacher Dashboard</title>
<link rel="stylesheet" href="/css/style.css">
<script src="/js/theme.js" defer></script>
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<span>Signed in as ${username} (Teacher)</span>
			<button class="theme-toggle" type="button">Dark mode</button>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<h2>Teacher Dashboard</h2>
		<p>You have full access: register, search, update, delete students, manage attendance and academic records.</p>
		<div class="dashboard-grid">
			<a class="dashboard-tile" href="/view">
				<h3>Register Student</h3>
				<p>Add a new student record.</p>
			</a>
			<a class="dashboard-tile" href="/findReg">
				<h3>Search / Manage Students</h3>
				<p>Search, update or delete existing student records.</p>
			</a>
			<a class="dashboard-tile" href="/attendance/mark-all">
				<h3>Mark Attendance - Whole Class</h3>
				<p>Pick one date and mark everyone present/absent in one go.</p>
			</a>
			<a class="dashboard-tile" href="/attendance/mark">
				<h3>Mark Attendance (single student)</h3>
				<p>Record or correct one student's attendance.</p>
			</a>
			<a class="dashboard-tile" href="/attendance/list">
				<h3>View / Edit Attendance</h3>
				<p>Browse all attendance records, edit or delete entries.</p>
			</a>
			<a class="dashboard-tile" href="/attendance/monthly">
				<h3>Monthly Attendance Report</h3>
				<p>See a student's present/absent tally, month by month, up to today.</p>
			</a>
			<a class="dashboard-tile" href="/academic/add">
				<h3>SGPA / CGPA</h3>
				<p>Add or update a student's semester SGPA; CGPA is calculated automatically.</p>
			</a>
		</div>
	</div>
</body>
</html>
