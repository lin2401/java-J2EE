package fr.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet // ou ("monurl")
(name = "joliServlet", description = "servlet dev", urlPatterns = { "/joliServlet", "/joliServletBis" })
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FirstServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Passage dans init");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Passage dans destroy");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setAttribute("maclef", new java.util.Date());

		request.setAttribute("monsupermessage", "hello");

		RequestDispatcher dispatcher = request.getRequestDispatcher("myJsp.jsp");
		// mainenat je vais sur ma vue
		dispatcher.forward(request, response);

		PrintWriter out = response.getWriter();
		out.write("Bonjour tout le monde (je suis en Get)");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String vCh1 = request.getParameter("ch1");
		String vCh2 = request.getParameter("ch2");

		if (vCh1 != null) {

		}
		// this.doGet(request, response);
		PrintWriter out = response.getWriter();
		out.write("Bonjour tout le monde (je suis en Post)" + vCh1 + " " + vCh2);
	}

}
