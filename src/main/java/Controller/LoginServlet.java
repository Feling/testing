package main.java.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.DAO.HibernateToDoListDAO;
import main.java.table.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {	
		 String username = request.getParameter("username");	
		 String password = request.getParameter("password");
		 HibernateToDoListDAO loginService = new HibernateToDoListDAO();
		 boolean result = loginService.authenticate(username, password);
		 User user = loginService.getUserByUsername(username);
		 if(result == true){
			 request.getSession().setAttribute("user", user);		
			 response.sendRedirect("home.jsp");
		 }
		 else{
			 response.sendRedirect("login.jsp");
		 }
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
