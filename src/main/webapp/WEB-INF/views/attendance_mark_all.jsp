<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mark Attendance - Whole Class</title>
<link rel="stylesheet" href="/css/style.css">
<script src="/js/theme.js" defer></script>
<style>
.date-form { display: flex; gap: 12px; align-items: flex-end; margin-bottom: 10px; }
.quick-actions { display: flex; gap: 10px; margin: 14px 0; }
.status-cell label { font-weight: normal; color: var(--text); margin-right: 14px; cursor: pointer; }
</style>
</head>
<body>
	<div class="navbar">
		<div class="brand">Student Management System</div>
		<div>
			<button class="theme-toggle" type="button">Dark mode</button>
			<a href="/teacherDashboard">Dashboard</a>
			<a href="/attendance/list">All Attendance</a>
			<a href="/logout">Logout</a>
		</div>
	</div>
	<div class="container">
		<div class="card">
			<h2>Mark Attendance - Whole Class</h2>

			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

			<form class="date-form" method="get" action="/attendance/mark-all">
				<div>
					<label for="attendanceDatePick">Date</label>
					<input id="attendanceDatePick" type="date" name="attendanceDate"
						value="${selectedDate}" onchange="this.form.submit()" />
				</div>
			</form>

			<c:if test="${empty students}">
				<p>No students registered yet.</p>
			</c:if>

			<c:if test="${not empty students}">
				<form id="bulkForm" method="post" action="/attendance/mark-all/save">
					<input type="hidden" name="attendanceDate" value="${selectedDate}" />

					<div class="quick-actions">
						<button type="button" class="btn btn-secondary" onclick="markAll('PRESENT')">Mark all Present</button>
						<button type="button" class="btn btn-secondary" onclick="markAll('ABSENT')">Mark all Absent</button>
					</div>

					<table>
						<tr>
							<th>Name</th>
							<th>Email</th>
							<th>Course</th>
							<th>Status</th>
						</tr>
						<c:forEach var="student" items="${students}">
							<c:set var="current" value="${existing[student.id]}" />
							<tr>
								<td>${student.name}</td>
								<td>${student.email}</td>
								<td>${student.course}</td>
								<td class="status-cell">
									<label>
										<input type="radio" name="status_${student.id}" value="PRESENT"
											${current == 'PRESENT' ? 'checked' : ''} /> Present
									</label>
									<label>
										<input type="radio" name="status_${student.id}" value="ABSENT"
											${current == 'ABSENT' ? 'checked' : ''} /> Absent
									</label>
								</td>
							</tr>
						</c:forEach>
					</table>

					<div class="actions" style="margin-top: 16px;">
						<button type="submit">Save Attendance for All</button>
					</div>
				</form>
			</c:if>
		</div>
	</div>

	<script>
		function markAll(status) {
			document.querySelectorAll('#bulkForm input[type=radio][value=' + status + ']')
				.forEach(function (radio) { radio.checked = true; });
		}
	</script>
</body>
</html>
