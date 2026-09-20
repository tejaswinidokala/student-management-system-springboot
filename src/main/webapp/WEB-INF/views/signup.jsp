<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sign up - Student Management System</title>
<link rel="stylesheet" href="/css/style.css">
<script src="/js/theme.js" defer></script>
</head>
<body>
	<div class="login-wrapper">
		<button class="theme-toggle" type="button">Dark mode</button>
		<div class="card login-card">
			<h2>Create a student account</h2>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>

			<form class="styled-form" action="doSignup" method="post">
				<div>
					<label for="username">Choose a username</label>
					<input id="username" type="text" name="username" required />
				</div>
				<div>
					<label for="password">Choose a password</label>
					<input id="password" type="password" name="password" required />
				</div>
				<button type="submit">Sign up</button>
			</form>

			<p class="hint">
				Note: sign-up creates a <b>student</b> login only. Teacher
				accounts are managed separately.
			</p>
			<p class="hint">Already have an account? <a href="login">Log in</a></p>
		</div>
	</div>
</body>
</html>
