<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>My Monthly Attendance</title>
<link rel="stylesheet" href="/css/style.css">
<script src="/js/theme.js" defer></script>
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<button class="theme-toggle" type="button">Dark mode</button>
			<a href="/studentDashboard">Dashboard</a>
			<a href="/attendance/search">Day-by-day view</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<div class="card">
			<h2>My Monthly Attendance</h2>
			<form class="search-form" action="/attendance/monthly-search" method="get">
				<div>
					<label for="email">Email</label>
					<input id="email" type="email" name="email" placeholder="your email" />
				</div>
				<div>
					<label for="mobile">Mobile</label>
					<input id="mobile" type="text" name="mobile" placeholder="your mobile" />
				</div>
				<button type="submit">Search</button>
			</form>
		</div>

		<c:if test="${searched}">
			<c:choose>
				<c:when test="${empty summary}">
					<div class="card">
						<p>No attendance records found.</p>
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
