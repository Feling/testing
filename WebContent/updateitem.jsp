<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String itemOldTitle = (String) request.getAttribute("updateItemTitle");
		String itemOldDescription = (String) request.getAttribute("updateItemDescription");
	//	String[] itemDateTime = ((String)request.getAttribute("updateItemDate")).split(" ");
		int itemID = (int)request.getAttribute("updateItemID");
	//	String itemOldDate = itemDateTime[0];
	//	String itemOldTime = itemDateTime[1];
	%>
	<div align="center">
		<form action="/TodoProject/Controller/updateitem" method="get">
			<b>Title:</b> <input type="text" name="itemTitle" value="<%=itemOldTitle%>"/>
			<br />
			<b>Description:</b> <input type="text" name="itemDescription" value="<%=itemOldDescription%>"/>
			<br />
			 <input type="hidden" name="userID" value=<%=request.getAttribute("userID")%> />
			 <input type="hidden" name="itemID" value=<%=request.getAttribute("updateItemID")%> />
			 <br /> <br />
			<input type="submit" class="btn" value="Update" />
		</form>
	</div>
</body>
</html>