package pl.ciechocinek.mb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.ciechocinek.mb.domain.User;
import pl.ciechocinek.mb.domain.UserRole;
import pl.ciechocinek.mb.service.UserService;
import pl.ciechocinek.mb.service.impl.UserServiceImpl;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private UserService ussi = UserServiceImpl.getUserService();

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (!email.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
			ussi.create(new User(firstName, lastName, email, password, UserRole.USER.toString()));
		}

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

}
