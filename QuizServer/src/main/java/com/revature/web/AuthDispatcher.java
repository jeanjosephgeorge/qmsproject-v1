package com.revature.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.UserDao;
import com.revature.model.LoginForm;
import com.revature.model.User;
import com.revature.util.Json;

public class AuthDispatcher implements Dispatcher {

	private final UserDao userDao = UserDao.currentImplementation;
	private final Logger logger = LogManager.getLogger(getClass());

	AuthDispatcher() {
	}

	public boolean supports(HttpServletRequest request) {
		return isForLogin(request) || isForUserInfo(request) || isForUserLogout(request);
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			LoginForm form = (LoginForm) Json.read(request.getInputStream(), LoginForm.class);
			logger.info(form);
			User info = userDao.login(form.getUsername(), form.getPassword());
			if (request.getMethod().equals("PUT")) {
				logger.info("Inside Logout");
				Cookie[] cookies = request.getCookies();
				// Cookie cookie = new Cookie("currentUser","");
				if (cookies != null)
					for (int i = 0; i < cookies.length; i++) {
						cookies[i].setMaxAge(0);
						response.addCookie(cookies[i]);
					}
				// cookie.setMaxAge(0);
				response.setContentType("text/html");
				response.setStatus(HttpServletResponse.SC_OK);
				return;
			}
			if (info != null) {
				response.setContentType(Json.CONTENT_TYPE);
				Cookie cookie = new Cookie("currentUser", info.getUsername());
				cookie.setPath("/QuizServer/api");
				response.addCookie(cookie);
				response.getOutputStream().write(Json.write(info));
				return;

			} else {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return;
			}

		} catch (IOException e) {
			logger.warn("Exception Encountered: {}", e);
			e.printStackTrace();
		}
	}

	private boolean isForLogin(HttpServletRequest request) {
		return request.getMethod().equals("POST") && request.getRequestURI().equals("/QuizServer/api/login");
	}

	private boolean isForUserInfo(HttpServletRequest request) {
		return request.getMethod().equals("GET") && request.getRequestURI().equals("/QuizServer/api/info");
	}

	private boolean isForUserLogout(HttpServletRequest request) {
		return request.getMethod().equals("PUT") && request.getRequestURI().equals("/QuizServer/api/logout");
	}
}
