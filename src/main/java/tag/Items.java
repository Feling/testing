package main.java.tag;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.hibernate.HibernateException;

import main.java.DAO.HibernateToDoListDAO;
import main.java.table.Item;
import main.java.table.User;
import javax.servlet.jsp.JspWriter;

import java.io.*;
import java.util.*;

public class Items extends SimpleTagSupport{
	
	private int userID;
	public void setUserID(int userid)
	{
		this.userID = userid;
	}
	private List<Item> items;
	private User user;
	public void doTag() throws IOException
	{
		try
		{
		 HibernateToDoListDAO hibernatetodolistDAO = new HibernateToDoListDAO();
		 user = hibernatetodolistDAO.getUser(userID);
		 items = hibernatetodolistDAO.getItems(user);
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
		}

		JspWriter out = getJspContext().getOut();
		StringBuilder builder = new StringBuilder();
		builder.append("<table class='tbl'>");
		builder.append("<tr><td><b>Delete</b></td><td><b>Title</b></td>");
		builder.append("<td><b>Description</b></td><td><b>Date</b></td>");
		builder.append("<td><b>Time</b></td><td><b>State</b></td>");
		builder.append("<td><b>Update</b></td></tr>");

		if (items != null)
		{
			for (Item item : items)
			{
				int itemID = item.getItemid();
				String title = item.getName(), description = item.getName();
			//	boolean state = item.getItemState();
			//	Date date = item.getDate();
				//String[] dateStr = date.toString().split(" ");

				builder.append("<tr align='center'>");
				builder.append("<td><form action='/TodoProject/Controller/deleteItem'");
				builder.append("method='get'>");
				builder.append("<input type='image' src='../Image/DeleteIcon.png' alt='submit'>");
				builder.append("<input name='itemID' type='hidden' value=" + String.valueOf(itemID) + "/>");
				builder.append("</form></td><td>" + title + "</td>");
				builder.append("<td>" + description + "</td>");
			//	builder.append("<td>" + String.valueOf(dateStr[0]) + "</td>");
			//	builder.append("<td>" + String.valueOf(dateStr[1]) + "</td>");
			//	String str = String.valueOf(state).equals("true") ? "Active" : "Finished";
			//	builder.append("<td>" + str + "</td>");
				builder.append("<td>");
				builder.append("<form action='/TodoProject/Controller/updateItemPage'");
				builder.append("method='get'>");
				builder.append("<input type='image' src='../Image/UpdateIcon.png' alt='submit'>");
				builder.append("<input name='itemID' type='hidden'");
				builder.append("value=" + String.valueOf(itemID) + " />");
				builder.append("</form></td></tr>");
			}
			builder.append("</table>");

			out.print(builder.toString());
		}
	}
}