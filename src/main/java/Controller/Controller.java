package main.java.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import antlr.collections.List;
import main.java.DAO.HibernateToDoListDAO;
import main.java.table.Item;
import main.java.table.User;

/**
 * Servlet implementation class Controller
 */
/**
 * @author Igork
 *
 */
@WebServlet("/Controller/*")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super(); // TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = null;
		HibernateToDoListDAO hibernatetodolistDAO = new HibernateToDoListDAO();
		User newuser = null;
		boolean alphanumeric = true;
	
		switch (path) {
						//Login with username and password
		case "/login": {
			try {
				String userName = request.getParameter("username");
				String password = request.getParameter("password");
		

				alphanumeric = isAlphaNumeric(userName) && isAlphaNumeric(password);
				if (userName.equals("") || password.equals(""))
				{
					request.setAttribute("userMessage", "Username and Password cant be empty");
					dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
					dispatcher.forward(request, response);
				}
				if (alphanumeric)
				{
					 HibernateToDoListDAO loginService = new HibernateToDoListDAO();
					newuser = new User(userName, password);
				//	 boolean result = loginService.authenticate(userName, password);
					// User user = loginService.getUserByUsername(userName);
					if (loginService.authenticate(userName, password))
					{
						
						newuser = loginService.getUserByUsername(userName);
						Cookie cookie = new Cookie("userID", String.valueOf(newuser.getId()));
						if (cookie != null)
						{
							cookie.setMaxAge(3600);
							response.addCookie(cookie);
						}
						request.getSession().setAttribute("userID", newuser.getId());
						request.getSession().setAttribute("userName", newuser.getUsername());
						dispatcher = getServletContext().getRequestDispatcher("/currentpage.jsp");
						dispatcher.forward(request, response);
					}
					else
					{

						request.setAttribute("userMessage", "User not exist. Please register your user");
						dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
						dispatcher.forward(request, response);
					}
				}
				else
				{
					request.setAttribute("userMessage", "Wrong username or password");
					dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
					dispatcher.forward(request, response);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				dispatcher = getServletContext().getRequestDispatcher("/errorPage.jsp");
				dispatcher.forward(request, response);
			}
			break;	
		}
		
					// Login proceger
		case "/loginpage": {
			try {
				dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
				dispatcher = getServletContext().getRequestDispatcher("/errorPage.jsp");
				dispatcher.forward(request, response);
			}
			break;

		}
					//Register Prosseger
		case "/registerPage":
		{
			try
			{
				dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
				dispatcher.forward(request, response);
			}
			catch (ServletException e)
			{
				e.printStackTrace();
				dispatcher = getServletContext().getRequestDispatcher("/errorPage.jsp");
				dispatcher.forward(request, response);
			}
			break;
		}

					// Register new user
		case "/register": 
		{
			
			try {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				if (isAlphaNumeric(username) && isAlphaNumeric(password))
				{
					alphanumeric = true;
				}
				else
				{
					alphanumeric = false;
				}

				// if the user didn't enter username or password, the user gets
				// a message
				if (username.equals("") || password.equals(""))
				{
					request.setAttribute("userMessage", "Username or password can't be empty");
					dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
					dispatcher.forward(request, response);
				}
				else
				{
					if (alphanumeric)
					{
					newuser = new User(0, username, password);
					if(hibernatetodolistDAO.authenticate(username, password))
							{
						request.setAttribute("userMessage", "User already exist");
						dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
						dispatcher.forward(request, response);
							}
					else
					{
					hibernatetodolistDAO.addUser(newuser);
					if(hibernatetodolistDAO.isUserExists(newuser.getId()))
					{
					request.setAttribute("userMassage","Registration Successful");
					Cookie cookie = new Cookie("userID", String.valueOf(newuser.getId()));
					if (cookie != null)
					{
						cookie.setMaxAge(3600);
						response.addCookie(cookie);
					}
				
				request.getSession().setAttribute("userID", newuser.getId());
				request.getSession().setAttribute("userName", newuser.getUsername());
				dispatcher = getServletContext().getRequestDispatcher("/currentpage.jsp");
				dispatcher.forward(request, response);
					}
					}
					}
					else
					{
						// if username or password arn't alphanumeric, message
						// will be shown
						request.setAttribute("userMessage", "Username must contains only letters or numbers");
						dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
						dispatcher.forward(request, response);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dispatcher = getServletContext().getRequestDispatcher("/errorPage.jsp");
				dispatcher.forward(request, response);
			}
		
			break;
		}	
		case "/logout":
		{
			request.getSession().invalidate();
			dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
			break;
		}
			default:
			case "/home":
			{
				if (request.getSession().getAttribute("userID") == null)
				{
					Cookie[] cookies = request.getCookies();
					if (cookies != null)
					{
						for (Cookie cookie : cookies)
						{
							if (cookie.getName().equals("userID"))
							{
								request.getSession().setAttribute("userID", cookie.getValue());
								request.setAttribute("userMessage", "Connected using cookies");
								break;
							}
						}
					}
				}

				Object ob = request.getSession().getAttribute("userID");
				int userID = 0;
				if (ob != null)
				{
					userID = Integer.parseInt(ob.toString());
				}
				if (userID != 0)
				{
					User user = hibernatetodolistDAO.getUser(userID);
					if (user != null)
					{
						request.getSession().setAttribute("userID", user.getId());
						request.getSession().setAttribute("userName", user.getUsername());
						dispatcher = getServletContext().getRequestDispatcher("/currentpage.jsp");
						dispatcher.forward(request, response);
					}
					else
					{
						request.setAttribute("userMessage", "Couldnt find your profile, try to register.");
						dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
						dispatcher.forward(request, response);
					}
				}
				else
				{
					dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
					dispatcher.forward(request, response);
				}

				break;
			}
		
			case "/addItemPage":
			{
				try
				{
					dispatcher = getServletContext().getRequestDispatcher("/addItem.jsp");
					dispatcher.forward(request, response);
				}
				catch (Exception  e)
				{
					request.setAttribute("userMessage", "Username or password can't be empty");
					throw new ServletException(e);
					//dispatcher = getServletContext().getRequestDispatcher("/errorPage.jsp");
				//.forward(request, response);
				}
				break;
			}
			case "/addItem":
			{
				String message = null;
				try
				{
					String itemTitle = request.getParameter("itemTitle");
					String itemDescription = request.getParameter("itemDescription");
					//String itemDate = request.getParameter("itemDate");
				//	String itemTime = request.getParameter("itemTime");
				//	Date itemDateTime = null;
					/*if (itemDate != null && itemTime != null && itemDate != "" && itemTime != "")
					{
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy/hh:mm:ss");
						try
						{
						itemDateTime = dateFormat.parse(String.format("%s/%s", itemDate, itemTime));
						}
						catch(ParseException e)
						{
							dateFormat = new SimpleDateFormat("dd-MM-yyyy/hh:mm");
							try
							{
								itemDateTime = dateFormat.parse(String.format("%s/%s", itemDate, itemTime));
							}
							catch (ParseException e1)
							{
								request.setAttribute("userMessage", e.getMessage());
								dispatcher = getServletContext().getRequestDispatcher("/errorPage.jsp");
								dispatcher.forward(request, response);
							}
						}
					}
					else
					{
						itemDateTime = new Date();
					}*/
					int userIntID = (int) (request.getSession().getAttribute("userID"));
					newuser = hibernatetodolistDAO.getUser(userIntID);
					Item newItem = new Item(itemTitle, 0, itemDescription, newuser);
					if (hibernatetodolistDAO.addItem(newItem))
					{
						dispatcher = getServletContext().getRequestDispatcher("/currentpage.jsp");
						dispatcher.forward(request, response);
					}
					else
					{
						message = "An error occured trying to add new item.";
						request.getServletContext().setAttribute("userMessage", message);
						dispatcher = getServletContext().getRequestDispatcher("/currentpage.jsp");
						dispatcher.forward(request, response);
					}
				}
				catch (HibernateException e)
				{
					request.setAttribute("userMessage", e.getMessage());
					dispatcher = getServletContext().getRequestDispatcher("/errorPage.jsp");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "/updateItemPage":
			{
				try
				{
				//	int itemID = Integer.parseInt(request.getParameter("itemID"));
					int itemID = Integer.valueOf(request.getParameter("itemID"));
					Item item = hibernatetodolistDAO.getItem(itemID);
					request.setAttribute("updateItemTitle", item.getName());
					request.setAttribute("updateItemDescription", item.getDescription());
					//request.setAttribute("updateItemDate", item.getDate().toString());
					request.setAttribute("updateItemID", item.getItemid());
					dispatcher = getServletContext().getRequestDispatcher("/updateitem.jsp");
					dispatcher.forward(request, response);
				}
				catch (ServletException e)
				{
					dispatcher = getServletContext().getRequestDispatcher("/errorPage.jsp");
					dispatcher.forward(request, response);
				}
				break;
			}
			case "/updateitem":
			{
				try
				{
					String itemTitle = request.getParameter("itemTitle");
					String itemDescription = request.getParameter("itemDescription");
				//	String itemDate = request.getParameter("itemDate");
				//	String itemTime = request.getParameter("itemTime");
				//	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/hh:mm:ss");
				//	Date itemDateTime = dateFormat.parse(String.format("%s/%s", itemDate, itemTime));
					String itemID = (String) request.getParameter("itemID");
					String userID = String.valueOf(request.getSession().getAttribute("userID"));
					int userIntID = Integer.parseInt(userID);
					if (itemTitle.equals("") || itemTitle == null)
					{
						request.setAttribute("userMessage", "itemTitle can't be empty");
						dispatcher = getServletContext().getRequestDispatcher("/updateitem.jsp");
						dispatcher.forward(request, response);
					}
					else
					{
						Item updateItem;
						updateItem = new Item(itemTitle, userIntID, itemDescription, newuser);
						updateItem.setItemid(Integer.parseInt(itemID));
						User user = hibernatetodolistDAO.getUser(userIntID);
						updateItem.setUser(user);
						// test if itemTitle already exists
						if (hibernatetodolistDAO.updateItem(updateItem))
						{
							request.setAttribute("userMessage", updateItem.getItemid());
							// request.setAttribute("items",
							// HibernateToDoListDAO.getInstance().getItems(userIntID));
							dispatcher = getServletContext().getRequestDispatcher("/currentpage.jsp");
							dispatcher.forward(request, response);
						}
						else
						{
							request.setAttribute("userMessage", "Item title not exist");
							dispatcher = getServletContext().getRequestDispatcher("/addItem.jsp");
							dispatcher.forward(request, response);
						}
					}
				}
				catch (HibernateException e)
				{
					request.setAttribute("userMessage", e.getMessage());
					dispatcher = getServletContext().getRequestDispatcher("/errorPage.jsp");
					dispatcher.forward(request, response);
				}
				break;

			}
			case "/deleteItem":
			{
				try
				{
					int itemID = Integer.valueOf(request.getParameter("itemID"));
				//	int itemIDint = Integer.valueOf("itemID");
				//	int itemIDint = (int) (request.getSession().getAttribute("itemID"));
					Item deletedItem = hibernatetodolistDAO.getItem(itemID);
					int userID = (int) request.getSession().getAttribute("userID");
					
					if (hibernatetodolistDAO.isUserExists(userID))	
					{
						
						if (hibernatetodolistDAO.deleteItem(itemID))
						{
							request.setAttribute("userMessage","Item was deleted");
							dispatcher = getServletContext().getRequestDispatcher("/currentpage.jsp");
							dispatcher.forward(request, response);
						}
						else
						{
							request.setAttribute("userMessage", "item was not deleted");
							dispatcher = getServletContext().getRequestDispatcher("/errorPage.jsp");
							dispatcher.forward(request, response);
						}
					}
					else
					{
						request.setAttribute("userMessage", "Invalid user ID");
						dispatcher = getServletContext().getRequestDispatcher("/errorPage.jsp");
						dispatcher.forward(request, response);
					}
				}
				catch (HibernateException e)
				{
					dispatcher = getServletContext().getRequestDispatcher("/errorPage.jsp");
					dispatcher.forward(request, response);
				}
				break;
			}
		}
		
		
				}

	private boolean isAlphaNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!Character.isDigit(c) && !Character.isLetter(c) && (c != ' ')) {
				return false;
			}
		}
		return true;
	}

/*	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		User usertest = new User(0, username, firstname, lastName, password);
		try {
			HibernateToDoListDAO hibernatetodolistDAO = new HibernateToDoListDAO();
			hibernatetodolistDAO.addUser(usertest);
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Registration Successful</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<center>");

			out.println("<h1>Registration Successful</h1>");
			out.println("To login with new UserId and Password<a href=login.jsp>Click here</a>");

			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}
*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
/*}
//if the user didn't enter username or password, the user gets
// a message

newuser = new User(0, userName, password, password, password);
// test if user exists
if (HibernateToDoListDAO) {
	Cookie cookie = new Cookie("userID", String.valueOf(newuser.getId()));
	if (cookie != null) {
		cookie.setMaxAge(3600);
		response.addCookie(cookie);
	}

	request.getSession().setAttribute("userID", newuser.getId());
	request.getSession().setAttribute("userName", newuser.getUsername());
	// request.setAttribute("items",
	// HibernateToDoListDAO.getInstance().getItems(newUser));
	dispatcher = getServletContext().getRequestDispatcher("/currentUserHomePage.jsp");
	dispatcher.forward(request, response);
} else {
	request.setAttribute("userMessage", "User not exist");
	dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
	dispatcher.forward(request, response);
}

} finally {
out.close();
}

break;*/
