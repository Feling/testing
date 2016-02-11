package main.java.general;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.java.DAO.IToDoListDAO;
import main.java.table.Item;
import main.java.table.User;

//Test project
//Igor Korchagin 317330546
public class Project {
	public static void main(String[] args)  {
		Factory factory = Factory.getInstance();
		IToDoListDAO todoDAO = factory.getIToDoListDAO(); 
		User u1 = new User(0, "u1", "first");
		User u2 = new User(0,"u2 test","second");
		User u3 = new User(0,"test","test");
		User u4 = new User(0,"user3","another");
		
		Set<Item> user1 = new HashSet<Item>();
		Set<Item> user2 = new HashSet<Item>();
		Item today = new Item ("today", 0, "good one", u1);
		Item tomorow = new Item ("tomorow", 0, "not good", u1);
		Item today2 = new Item("today2",0,"I like it!", u2);
		Item tomorow2 =new Item("tomorow2",0,"fuck", u2);
		Item testing_addItem = new Item(null, 0, "loh", u1);
		//today.setUser(u4);
		user1.add(today);
		user1.add(tomorow);
		user2.add(today2);
		user2.add(tomorow2);	
		u1.setItems(user1);
		u2.setItems(user2);
	try {
		todoDAO.addUser(u1);
		todoDAO.addUser(u2);
		todoDAO.addItem(testing_addItem);
		todoDAO.authenticate("u1", "123");
	//	todoDAO.deletItem(today);
		//todoDAO.addItem(today2);
	List<Item> b = todoDAO.getItems(u1);
		for (Item current: b)
		{
			System.out.println("Name: "+current.getName());
			System.out.println("Description: "+current.getItemid());
		}
		//todoDAO.addUser(u1);
		//todoDAO.addUser(u1);
		//todoDAO.addUser(u2);
	//	todoDAO.addItem(item);
		//todoDAO.addItem(item2);
	//	todoDAO.deleteUser(u3);
	//	todoDAO.deletItem(item2);
		//todoDAO.getItems(u1);
	//	todoDAO.getUsers();
		//todoDAO.getItems();
	//	todoDAO.isUserExists(u1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
}
