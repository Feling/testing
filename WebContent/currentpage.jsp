<%@page import="main.java.DAO.HibernateToDoListDAO,java.util.*,main.java.*,main.java.DAO.*"%>
<%@page import="main.java.table.User"%>
<%@page import="main.java.DAO.HibernateToDoListDAO"%>
<%@page import="java.util.List"%>
<%@page import="main.java.*"%>
<%@page import="java.util.Date"%>
<%@page import="main.java.DAO.*" %>
<%@page import="main.java.table.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ToDo App</title>
<style type="text/css">
h3{font-family: Calibri; font-size: 22pt; font-style: normal; font-weight: bold; color:SlateBlue;
text-align: center; text-decoration: underline }
table{font-family: Calibri; color:white; font-size: 11pt; font-style: normal;width: 50%;
text-align:; background-color: SlateBlue; border-collapse: collapse; border: 2px solid navy}
table.inner{border: 0px}
</style>
</head>
<body>
<%
		int userID = (int) session.getAttribute("userID");
		String userName = (String) session.getAttribute("userName");

		if (userName != null)
		{
	%>
	<h1>
		Hi
		<%=userName%>!
		<%=userID %>
	</h1>
	<%
		}
	%>

	<%
		if (request.getAttribute("userMessage") != null)
		{
			String userMessage = String.valueOf(request.getAttribute("userMessage"));
	%>
	<h4><%=userMessage%></h4>
	<%
		}
	%>
	<h1 align="center">Home Page</h1>
<div align="center"><a href="logout.jsp" style="color: RED">Logout</a> | <a href="addItem.jsp" style="color: BLUE">ADD / DELETE / EDIT</a> | <a href="home.jsp" style="color: Green">Show All ToDos</a></div>
<p align="center">____________________________</p>
<h3 align="center">To Do List</h3>
<p align="center">____________________________</p>
<%
HibernateToDoListDAO dao = new HibernateToDoListDAO();
User user = dao.getUser(userID);
List<Item> todolist = dao.getItems(user);

out.println("<table width='80%' align='center'>");
out.println("<tr><th>Task ID</th><th>Task Name</th><th>Description</th>");
for(Item td : todolist){
	int itemID = td.getItemid();
	String title = td.getName(), description = td.getName();
	//boolean state = td.getItemState();
//	Date date = td.getDate();
	//String[] dateStr = date.toString().split(" ");
	out.println("<tr align='center'>");
	out.println("<td>");
%>
<form action="/TodoProject/Controller/deleteItem" method="get">
<input type="hidden" name="itemID" value="<%=String.valueOf(itemID)%>"/>
<input type="submit" class="btn" value="<%=itemID%>"/>
</form>
<%
	//out.println("<tr align='center'>");
	//out.println("<td><form action='/TodoProject/Controller/deleteItem' method='get'");
//	out.println("method='get'>");
	//out.println("<input type='submit' class='btn' value="+itemID+">");
//	out.println("<type='hidden' name='itemID' value="  +String.valueOf(itemID)+ "/>");
	out.println("</form></td><td>" + title + "</td>");
//	builder.append("<td>" + description + "</td>");
	//builder.append("<td>" + String.valueOf(dateStr[0]) + "</td>");
//	builder.append("<td>" + String.valueOf(dateStr[1]) + "</td>");
//	String str = String.valueOf(state).equals("true") ? "Active" : "Finished";
//	out.println("<td>" + str + "</td>");
	out.println("<td>");
	out.println("<form action='/TodoProject/Controller/updateItemPage'");
	out.println("method='get'>");
//	out.println("<input type='image' src='../Image/UpdateIcon.png' alt='submit'>");
 	out.println("<input type='submit' class='btn' value="+itemID+">");	
	out.println("<input name='itemID' type='hidden'");
	out.println("value=" + String.valueOf(itemID) + " />");
	out.println("</form></td></tr>");
}
//builder.append("</table>");

//out.print(builder.toString());
//	out.println("<tr><td>"+td.getItemid()+"</td><td>"+td.getName()+"</td></tr>");
//}
out.println("</table>");

%>
<div align="center">
		<br /> <br />
		<form
			action="/TodoProject/Controller/addItemPage"
			method="get">
			<input type="submit" class="btn" value="Add Item" />
		</form>
		<%if (userID != 0){%>
		<form action="/TodoProject/Controller/logout"
			method="get">
			<input type="submit" class="btn" value="Logout" />
		</form>
		<%}%>
		</div>
</body>
</html>