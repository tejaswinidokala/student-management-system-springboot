<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Create</title>
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
	<h2>Create new registration</h2>
	<form class="styled-form" action="createReg" method="post">
		<div>
			<label for="name">Name</label>
			<input id="name" type="text" name="name" required />
		</div>
		<div>
			<label for="email">Email</label>
			<input id="email" type="email" name="email" required />
		</div>
		<div>
			<label for="mobile">Mobile</label>
			<input id="mobile" type="text" name="mobile" required />
		</div>
		<div>
			<label for="course">Course</label>
			<input id="course" type="text" name="course" required />
		</div>
		<button type="submit">Save</button>
	</form>
    ${msg}
	<p><a href="findReg">View all registrations</a></p>
	</div>
	</div>
</body>
</html>