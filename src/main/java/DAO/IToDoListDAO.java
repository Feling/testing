package main.java.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import main.java.table.*;

public interface IToDoListDAO {
	public void addUser(User user) throws SQLException;
	public void deleteUser(User user) throws SQLException;
	public boolean addItem(Item item) throws SQLException;
	public void getUsers();
	//public boolean deletItem(int i);
	public boolean deleteItem(int id);
	public void getItems();
	public void setUsername(User user) throws SQLException; //todo add function
	boolean isUserExists(User user);
	public boolean authenticate(String username, String password) throws SQLException;
	public User getUserByUsername(String username) throws SQLException; 
	public List<Item> getItems(User user);
	public User getUser(int userID);
	public boolean updateItem(Item updateItem);
	public Item getItem(int itemID);
	public boolean connect(String name, String password);
	public boolean connect(User user);
	public boolean isUserExists(int userID);
	
}