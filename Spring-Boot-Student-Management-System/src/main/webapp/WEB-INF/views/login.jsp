<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Student Management System</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<div class="login-wrapper">
		<div class="card login-card">
			<h2>Sign in</h2>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

			<form class="styled-form" action="doLogin" method="post">
				<div>
					<label for="username">Username</label>
					<input id="username" type="text" name="username" required />
				</div>
				<div>
					<label for="password">Password</label>
					<input id="password" type="password" name="password" required />
				</div>
				<button type="submit">Login</button>
			</form>

			<p class="hint">
				Demo accounts — Teacher: <b>teacher / teacher123</b><br />
				Student: <b>student / student123</b>
			</p>
			<p class="hint">New student? <a href="signup">Create an account</a></p>
		</div>
	</div>
</body>
</html>
