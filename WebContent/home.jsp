<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255" errorPage="errorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>To Do List App</title>

</head>
<body>
	<h1 align="center" style="color: blue;">You are wellcome to try this app!</h1>
	<div align="center">
		<form action="/TodoProject/Controller/loginpage" method="get">
			<input type="submit" class="btn"
				value="Login" />
		</form>
		<br />
		<form action="/TodoProject/Controller/registerPage" method="get">
			<input type="submit" class="btn"
				value="Register" />
		</form>
	</div>
	

</body>
</html>