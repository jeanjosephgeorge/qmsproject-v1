package com.revature.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDispatcher implements Dispatcher {

	// Constructor with the package-private access
	public UserDispatcher() {
	}

	@Override
	public boolean supports(HttpServletRequest request) {
		return isFindUser(request) || isFindGrade(request);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (isFindUser(request)) {

		} else if (isFindGrade(request)) {

		}

	}

	public boolean isFindUser(HttpServletRequest request) {
		return request.getMethod().equals("GET") && request.getRequestURI().equals("/QuizServer/api/finduser")
				&& request.getParameter("username") != null;
	}

	public boolean isFindGrade(HttpServletRequest request) {
		return request.getMethod().equals("GET") && request.getRequestURI().equals("/QuizServer/api/findGrades")
				&& request.getParameter("username") != null & request.getParameter("quiz") != null;
	}
}
