package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LogManager.getLogger();
	private final Dispatcher dispatcherChain = DispatcherChain.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("GET");
		logger.info("{} request coming to {}", req.getMethod(), req.getRequestURI());
		if (dispatcherChain.supports(req)) {
			dispatcherChain.execute(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("POST");
		logger.info("{} request coming to {}", req.getMethod(), req.getRequestURI());
		if (dispatcherChain.supports(req)) {
			dispatcherChain.execute(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("DELETE");
		logger.info("{} request coming to {}", req.getMethod(), req.getRequestURI());
		if (dispatcherChain.supports(req)) {
			dispatcherChain.execute(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("PUT");
		logger.info("{} request coming to {}", req.getMethod(), req.getRequestURI());
		if (dispatcherChain.supports(req)) {
			dispatcherChain.execute(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("PATCH SERVICE");
		if (req.getMethod().equals("PATCH")) {
			doPatch(req, resp);
		} else {
			super.service(req, resp);
		}
	}

	protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("{} request coming to {}", req.getMethod(), req.getRequestURI());
		if (dispatcherChain.supports(req)) {
			dispatcherChain.execute(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}

}
