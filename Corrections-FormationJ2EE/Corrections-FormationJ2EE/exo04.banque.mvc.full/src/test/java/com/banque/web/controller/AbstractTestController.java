package com.banque.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe abstraite pour les tests des controleurs avec Mockito. <br/>
 *
 * Note : on pourrait aussi le faire en Spring.
 */
public abstract class AbstractTestController {

	@Mock
	protected HttpServletRequest request;
	@Mock
	protected HttpServletResponse response;
	@Mock
	protected HttpSession session;
	@Mock
	protected RequestDispatcher requestDispatcher;

	private AbstractController controller;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(this.request.getSession()).thenReturn(this.session);
		Mockito.when(this.request.getSession(ArgumentMatchers.anyBoolean())).thenReturn(this.session);
		Mockito.when(this.request.getRequestDispatcher(ArgumentMatchers.anyString()))
				.thenReturn(this.requestDispatcher);
		this.controller = this.initControler();
	}

	protected String doPost() throws IOException, ServletException {
		try (PrintWriter writer = new PrintWriter(new StringWriter());) {
			Mockito.when(this.response.getWriter()).thenReturn(writer);
			this.controller.doPost(this.request, this.response);
			writer.flush();
			return writer.toString();
		}
	}

	protected String doGet() throws IOException, ServletException {
		try (PrintWriter writer = new PrintWriter(new StringWriter());) {
			Mockito.when(this.response.getWriter()).thenReturn(writer);
			this.controller.doGet(this.request, this.response);
			writer.flush();
			return writer.toString();
		}
	}

	protected String service() throws IOException, ServletException {
		try (PrintWriter writer = new PrintWriter(new StringWriter());) {
			Mockito.when(this.response.getWriter()).thenReturn(writer);
			this.controller.service(this.request, this.response);
			writer.flush();
			return writer.toString();
		}
	}

	protected abstract AbstractController initControler();

}
