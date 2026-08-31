<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<a href="/">Dashboard</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
	<div class="card">
	<h2>Create new registration</h2>
	<form action="createReg" method="post">
		<pre>
	         Name <input type="text" name="name"/>
	         Email <input type="text" name="email"/>
	         Mobile <input type="text" name="mobile"/>
	         Course <input type="text" name="course"/>
	         <input type="submit" value="save"/>
	   </pre>
	</form>
    ${msg}
	<p><a href="findReg">View all registrations</a></p>
	</div>
	</div>
</body>
</html>