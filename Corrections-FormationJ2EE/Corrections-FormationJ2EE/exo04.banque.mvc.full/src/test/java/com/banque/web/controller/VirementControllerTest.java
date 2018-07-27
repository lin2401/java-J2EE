package com.banque.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.mockito.Mockito;

import com.banque.web.Clefs;

/**
 * Controller pour le virement. <br/>
 */
public class VirementControllerTest extends AbstractTestController {

	@Test
	public void testServletDoGet() throws IOException, ServletException {
		Mockito.when(this.session.getAttribute(Clefs.CLEF_AUTHENTIFICATION)).thenReturn(Integer.valueOf(1));
		super.doGet();
	}

	@Test
	public void testServletDoPost() throws IOException, ServletException {
		Mockito.when(this.session.getAttribute(Clefs.CLEF_AUTHENTIFICATION)).thenReturn(Integer.valueOf(1));
		Mockito.when(this.request.getParameter("inCmptEme")).thenReturn("12");
		Mockito.when(this.request.getParameter("inCmptDes")).thenReturn("15");
		Mockito.when(this.request.getParameter("inMontant")).thenReturn("5");
		super.doPost();
	}

	@Override
	protected AbstractController initControler() {
		return new VirementController();
	}

}
