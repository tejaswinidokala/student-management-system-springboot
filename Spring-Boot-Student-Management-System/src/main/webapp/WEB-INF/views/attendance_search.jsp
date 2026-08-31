<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Attendance</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<a href="/studentDashboard">Dashboard</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<div class="card">
			<h2>Check Attendance</h2>
			<form class="search-form" action="/attendance/search" method="get">
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

			<c:if test="${searched}">
				<c:choose>
					<c:when test="${empty records}">
						<p>No attendance records found.</p>
					</c:when>
					<c:otherwise>
						<table>
							<tr>
								<th>Date</th>
								<th>Status</th>
							</tr>
							<c:forEach var="record" items="${records}">
								<tr>
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
								</tr>
							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
	</div>
</body>
</html>
