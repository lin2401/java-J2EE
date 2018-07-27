package com.banque.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Controller de base. <br/>
 */
abstract class AbstractController extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}

	@Override
	@SuppressWarnings("squid:S1989")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	@Override
	@SuppressWarnings("squid:S1989")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}

	/**
	 * Fait un forward vers la destination indiquee.
	 *
	 * @param request
	 *            la request
	 * @param response
	 *            la response
	 * @param pDestination
	 *            ou aller
	 */
	protected void forward(HttpServletRequest request, HttpServletResponse response, String pDestination) {
		RequestDispatcher dispatcher = request.getRequestDispatcher(pDestination);
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			AbstractController.LOG.fatal("Erreur lors d'un forward vers {}", pDestination, e);
		}
	}
}
