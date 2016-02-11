package main.java.general;

import main.java.DAO.HibernateToDoListDAO;
import main.java.DAO.IToDoListDAO;

public class Factory {
	public static Factory instance = new Factory();
	public IToDoListDAO itodolistdao;
	
	private Factory() {}
	
		public static Factory getInstance(){
			return Factory.instance;
		}
	public IToDoListDAO getIToDoListDAO(){
		if(itodolistdao == null) itodolistdao = new HibernateToDoListDAO();
		return itodolistdao;
	}
}


