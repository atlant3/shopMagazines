package pl.ciechocinek.mb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import pl.ciechocinek.mb.dao.impl.UserDaoImpl;

public class Logout extends HttpServlet {
	private static Logger LOG = Logger.getLogger(UserDaoImpl.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			LOG.info("LOGOUT = " + session.getAttribute("userEmail"));
			session.invalidate();

		}

		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("index.jsp");
	}

}