<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>My SGPA / CGPA</title>
<link rel="stylesheet" href="/css/style.css">
<script src="/js/theme.js" defer></script>
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<button class="theme-toggle" type="button">Dark mode</button>
			<a href="/studentDashboard">Dashboard</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<div class="card">
			<h2>My SGPA / CGPA</h2>
			<form class="search-form" action="/academic/search" method="get">
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
						<p>No academic records found yet.</p>
					</c:when>
					<c:otherwise>
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
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
	</div>
</body>
</html>
