package main.java.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name="item")
public class Item {

	//@Column(name="Item_id")
	private int Itemid;
	//@Column(name="name")
	private String name;
	private String description;
	private User user;
	public Item() {
	}
	public Item(String name, int Itemid, String description, User user) {
		super();
		this.name = name;
		this.Itemid = Itemid;
		this.description = description;
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getItemid() {
		return Itemid;
	}
	public void setItemid(int Itemid) {
		this.Itemid = Itemid;
	
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String toString()
	{
		return "[" + Itemid + ", " + name +   "]" ;
	}
}
