package com.banque.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test du controller de login avec Mockito. <br/>
 */
public class LoginControllerTest extends AbstractTestController {

	@Test
	public void testServletDoPost() throws IOException, ServletException {
		Mockito.when(this.request.getParameter("inLogin")).thenReturn("df");
		Mockito.when(this.request.getParameter("inPass")).thenReturn("df");
		super.doPost();
	}

	@Test
	public void testServletDoGet() throws Exception {
		super.doGet();
	}

	@Override
	protected AbstractController initControler() {
		return new LoginController();
	}
}
