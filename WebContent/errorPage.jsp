<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error page</title>
</head>
<body>
<div align="center">
	<%
		String message = (String)request.getAttribute("userMessage");
		if (message != null)
		{
	%>
	<h2><%=message%></h2>
	<%
		}
	%>
	<h3>Student Registration Form</h3>
	</div>
	<div align="center">
		<img src="../Image/error.png">
		<form action="/TodoProject/Controller/home" method="get">
			<input type="submit" class="btn" value="Return to home page" />
		</form>
	</div>
</body>
</html>