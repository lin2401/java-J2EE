package com.banque.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.mockito.Mockito;

import com.banque.web.Clefs;

/**
 * Controller pour l'historique <br/>
 */
public class HistoriqueControllerTest extends AbstractTestController {

	@Test
	public void testServletService() throws IOException, ServletException {
		Mockito.when(this.session.getAttribute(Clefs.CLEF_AUTHENTIFICATION)).thenReturn(Integer.valueOf(1));
		Mockito.when(this.request.getParameter("inNumeroCompte")).thenReturn("12");
		Mockito.when(this.request.getParameter("inDateDebut")).thenReturn(null);
		Mockito.when(this.request.getParameter("inDateFin")).thenReturn(null);
		Mockito.when(this.request.getParameter("inCredit")).thenReturn("true");
		Mockito.when(this.request.getParameter("inDebit")).thenReturn("true");
		super.service();
	}

	@Override
	protected AbstractController initControler() {
		return new HistoriqueController();
	}

}
