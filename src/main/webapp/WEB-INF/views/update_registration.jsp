<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Update</title>
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
	<h2>Update registration</h2>
	<form action="updateReg" method="post">
		<pre>
		     <input type="hidden" name="id" value="${dto.id}"/>
	         Name <input type="text" name="name" value="${dto.name}"/>
	         Email <input type="text" name="email" value="${dto.email}"/>
	         Mobile <input type="text" name="mobile" value="${dto.mobile}"/>
	         Course <input type="text" name="course" value="${dto.course}"/>
	         <input type="submit" value="update"/>
	   </pre>
	</form>
    ${msg}
	<p><a href="findReg">Back to registrations</a> | <a href="view">Create new registration</a></p>
	</div>
	</div>
</body>
</html>
