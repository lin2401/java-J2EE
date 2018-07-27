package com.banque.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.mockito.Mockito;

import com.banque.web.Clefs;

/**
 * Controller qui liste les comptes <br/>
 */
public class CompteControllerTest extends AbstractTestController {

	@Test
	public void testServletService() throws IOException, ServletException {
		Mockito.when(this.session.getAttribute(Clefs.CLEF_AUTHENTIFICATION)).thenReturn(Integer.valueOf(1));
		super.service();
	}

	@Override
	protected AbstractController initControler() {
		return new CompteController();
	}

}
