package main.java.table;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

//@Entity
//@Table(name="user")
public
class  User {
	public User(){};
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	//@Column(name="username")
	private String username;
	//@Column(name="first_name")
//	private String first_name;
	//@Column(name="last_name")
//	private String last_name;
	private String password;
	private Set<Item> items;
/*	public User(int id, String username,, String last_name, String password) {
		super();
		this.id = id;
		this.username = username;
	//	this.first_name = first_name;
	//	this.last_name = last_name;
		this.password = password;
		
	}*/
	public User(int id, String username, String password)
	{
		super();
		this.id = id;
		this.password = password;
		this.username = username;
	}
	public User(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
/*	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	public String toString()
	{
		return "[" + id + ", " + username + " ]";
	}
	public Set<Item> getItems(){
		return items;
	}
	public void setItems(Set<Item> items){
		this.items = items;
	}
}
