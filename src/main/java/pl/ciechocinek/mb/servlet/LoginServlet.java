package pl.ciechocinek.mb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import dto.UserLogin;
import pl.ciechocinek.mb.dao.impl.UserDaoImpl;
import pl.ciechocinek.mb.domain.User;
import pl.ciechocinek.mb.service.UserService;
import pl.ciechocinek.mb.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger LOG = Logger.getLogger(UserDaoImpl.class);

	private UserService ussi = UserServiceImpl.getUserServive();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("email");
		String password = request.getParameter("password");

		User user = ussi.getUserByEmail(login);

		if (user != null && user.getPassword().equals(password)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userEmail", login);
			UserLogin userLogin = new UserLogin();
			userLogin.destinationUrl = "cabinet.jsp";
			userLogin.userEmail = user.getEmail();

			String json = new Gson().toJson(userLogin);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

			LOG.info("SET ATR LOGIN" + login + userLogin.userEmail + userLogin.destinationUrl + password);

		}
	}

}
