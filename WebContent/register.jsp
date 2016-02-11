<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255" errorPage="errorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Registration Form</title>
<link rel="stylesheet" type="text/css" href="/TodoProject/style.css"/>
</head>
 
<body>
<%if(request.getAttribute("userMessage")!=null)
	{
		out.print(request.getAttribute("userMessage"));
	}
	%>
	 <div id="mystyle" class="myform">
<h3>Student Registration Form</h3>
<form action="/TodoProject/Controller/register" method="get">
 
<table align="center" cellpadding = "10">
<tr>
<td>User name</td>
<td><input type="text" name="username" maxlength="30"/>
(max 30 characters a-z and A-Z)
</td>
</tr>
<tr>
<td>Password</td>
<td><input type="text" name="password" maxlength="30"/>
(max 30 characters a-z and A-Z)
</td>
</tr>

<tr>
<td colspan="2" align="center">
<input type="submit" value="Submit">
<input type="reset" value="Reset">
</td>
</tr>
</table>
 
</form>
</div>
</body>
</html>