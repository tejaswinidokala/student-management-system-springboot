package com.demo.config;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//Lightweight, session based route guard.
//Existing endpoints in StudentController / StudentControllerView keep working exactly as before;
//this only ADDS an access check in front of the routes listed below.
@Component
public class AuthInterceptor implements HandlerInterceptor {

	//Update / Delete of student records, and attendance management -> teachers only.
	private static final Set<String> TEACHER_ONLY_PATHS = Set.of(
			"/updateReg", "/studentByIdReg", "/deleteReg",
			"/teacherDashboard",
			"/attendance/mark", "/attendance/save",
			"/attendance/list", "/attendance/delete",
			"/attendance/edit", "/attendance/update"
	);

	//Only students should land here.
	private static final Set<String> STUDENT_ONLY_PATHS = Set.of(
			"/studentDashboard", "/attendance/search"
	);

	//Any logged in user (teacher or student) may use these.
	private static final Set<String> LOGGED_IN_PATHS = Set.of(
			"/view", "/createReg", "/findReg"
	);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getRequestURI().substring(request.getContextPath().length());

		HttpSession session = request.getSession(false);
		String role = (session != null) ? (String) session.getAttribute("role") : null;

		if (TEACHER_ONLY_PATHS.contains(path)) {
			if (role == null) {
				response.sendRedirect(request.getContextPath() + "/login");
				return false;
			}
			if (!"TEACHER".equals(role)) {
				response.sendRedirect(request.getContextPath() + "/studentDashboard?error=access_denied");
				return false;
			}
			return true;
		}

		if (STUDENT_ONLY_PATHS.contains(path)) {
			if (role == null) {
				response.sendRedirect(request.getContextPath() + "/login");
				return false;
			}
			if (!"STUDENT".equals(role)) {
				response.sendRedirect(request.getContextPath() + "/teacherDashboard?error=access_denied");
				return false;
			}
			return true;
		}

		if (LOGGED_IN_PATHS.contains(path) && role == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}

		return true;
	}
}
