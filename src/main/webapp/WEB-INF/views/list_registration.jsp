<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>All Students</title>
<link rel="stylesheet" href="/css/style.css">
<script src="/js/theme.js" defer></script>
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<button class="theme-toggle" type="button">Dark mode</button>
			<a href="/">Dashboard</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
	<div class="card">
	<c:if test="${sessionScope.role == 'TEACHER'}">
		<p><a href="/view">Create new registration</a></p>
	</c:if>
	<form class="search-form" action="findReg" method="get">
		<div>
		<label for="name">Name</label>
		<input id="name" type="text" name="name" placeholder="Search name" />
		</div>
		<div>
		<label for="course">Course</label>
		<input id="course" type="text" name="course" placeholder="Search course" />
		</div>
		<div>
		<label for="email">Email</label>
		<input id="email" type="email" name="email" placeholder="Search email" />
		</div>
		<div>
		<label for="mobile">Mobile</label>
		<input id="mobile" type="text" name="mobile" placeholder="Search mobile" />
		</div>
		<button type="submit">Search</button>
		<a class="btn btn-secondary" href="findReg">Show all</a>
	</form>
	<table>
		<tr>
			<th>name</th>
			<th>email</th>
			<th>mobile</th>
			<th>course</th>
			<c:if test="${sessionScope.role == 'TEACHER'}">
				<th>Delete</th>
				<th>Update</th>
			</c:if>
		</tr>
		<c:forEach var="student" items="${students}">
			<tr>
				<td>${student.name}</td>
				<td>${student.email}</td>
				<td>${student.mobile}</td>
				<td>${student.course}</td>
				<c:if test="${sessionScope.role == 'TEACHER'}">
					<td><a href="deleteReg?id=${student.id}">delete</a></td>
					<td><a href="studentByIdReg?id=${student.id}">update</a></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
</body>
</html>