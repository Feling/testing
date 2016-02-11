<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="/TodoProject/style.css"/>
<title>Login Page</title>
</head>
<body>
<%if(request.getAttribute("userMessage")!=null)
	{
		out.print(request.getAttribute("userMessage"));
	}
	%>
<center>
	 <div id="mystyle" class="myform">
		 <form id="form" name="form" method="get" action="/TodoProject/Controller/login">
			 <h1>Login</h1>
			 <p>Please enter your login information
				 <br/>New User? <a href="register.jsp">Register</a></p>
			 <label>User name
				 <span class="small">Enter your user name</span>
			 </label>
			 <input type="text" name="username" id="username" />

			 <label>Password
				 <span class="small">Min. size 6 chars</span>
			 </label>
			 <input type="password" name="password" id="password" />

			 <button type="submit">Sign-in</button>
			 <div class="spacer"></div>
		 </form>
	 </div>
</center>
	<%
	String userMessage = "";
	if(request.getAttribute("userMessage")!=null)
	{
		userMessage = (String)request.getAttribute("userMessage");
	}
	
	%>
</body>
</html>