<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adding Item page</title>
<link rel="stylesheet" type="text/css" href="/TodoProject/style.css"/>
</head>
<body>
<%
		int userID = (int) session.getAttribute("userID");
	%>
	<h1>
		Hi
		<%=userID %>
	</h1>
	<%
	%>
<div align="center"id="mystyle" class="myform">
		<form action="/TodoProject/Controller/addItem" method="get">
			<b>Title:</b> <input type="text" name="itemTitle"/>
			<br/>
			<b>Description:</b> <input type="text" name="itemDescription"/>
			<br/>
			
			<input type="hidden" name="userID" value=<%=userID%>/>
			
				<br/> <br/> 
				<input type="submit" class="btn" value="Add Item"/>
		</form>
	</div>
</body>
</html>