package main.java.DAO;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


import main.java.table.Item;
import main.java.table.User;
import main.java.util.HibernateUtil;

public class HibernateToDoListDAO implements IToDoListDAO{



	@Override
	public void addUser(User user) throws SQLException {
		Session session = null;
		 
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
		//session.close();
	}

/*	@Override
/*	public boolean isUserExists(User user){
		 Session session = null;
		 boolean result = false;
		 try{
		 session = HibernateUtil.getSessionFactory().openSession();
			 session.beginTransaction();
			 Query query = session.createQuery("from User where ID ='"+user.getId()+"'");
			 User u = (User)query.uniqueResult();
			 session.getTransaction().commit();
			 if(u!=null) result = true;
		 }catch(Exception e){
			e.printStackTrace();
		 }finally{
			 session.close();
		 }
		return result;
	}*/
	
	@Override
	public void deleteUser(User user) throws SQLException {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
		
		
	}

	@Override
	public boolean addItem(Item item) throws SQLException {
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(item);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
		//session.close();
		return true;
		
	}

	@Override
	public void getUsers()  {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			List Users = session.createQuery("from User").list();
			Iterator i = Users.iterator();
			System.out.println("There are " + Users.size() + " Users");
			while(i.hasNext()) 
			{
				System.out.println(i.next());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
			
			
		
	}
	public void setUsername (User user) throws SQLException {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
}
	/*public void getItems(User user)  {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			List Users = session.createQuery("from user").list();
			Iterator i = Users.iterator();
			System.out.println("There are " + Users.size() + " Users");
			while(i.hasNext()) 
			{
				System.out.println(i.next());
			}
		}catch(Exception e){
		
			e.printStackTrace();
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
	}*/

	@Override
	public boolean deleteItem(int id)
	{
		boolean success = true;
		Session session = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Item tempInstance = (Item) session.load(Item.class, id);
			session.delete(tempInstance);
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			throw new HibernateException("Could't delete item");
		}
		finally
		{
			session.close();
		}
		return success;
	}
	/*public boolean deletItem(Item item) {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(item);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
		return true;
	}*/

	@Override
	public void getItems() {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			List Items = session.createQuery("from Item").list();
			Iterator i = Items.iterator();
			System.out.println("There are " + Items.size() + " Items");
			while(i.hasNext()) 
			{
				System.out.println(i.next());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if ((session != null) && (session.isOpen()))session.close();
		}
		
	}

	public boolean authenticate(String username, String password) {
		 User user = getUserByUsername(username);		
		 if(user!=null && user.getUsername().equals(username) && user.getPassword().equals(password)){
			 return true;
		 }else{
			 return false;
		 }
	}
		 public User getUserByUsername(String username) {
			 Session session = null;
			 User user = null;
			 try{
					session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();

			 	 Query query = session.createQuery("from User where username='"+username+"'");
				 user = (User)query.uniqueResult();
				 session.getTransaction().commit();
			 } catch (Exception e) {
				 if (session != null) {
					 session.beginTransaction().rollback();
				 }
				 e.printStackTrace();
			 } finally {
				 session.close();
			 }
			 return user;
		 }
		
		/* public Set<Item> getItems(User user) {
			 Session session = null;	

				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();

		 Query query=session.createQuery("from User");  
		    List<User> list=query.list();
		      
		    Iterator<User> itr=list.iterator();  
		    while(itr.hasNext()){  
		        User q=itr.next();  
		        System.out.println("Question Name: "+q.getUsername());  
		    }
			return null;
		 }
}*/
		 
		 @Override 
		 public List<Item> getItems(User user) 
			{
				String hql = "from Item where ID='" + user.getId() + "'";
				List<Item> itemsOfUser = null;
				Session session = null;
				try
				{
					if (isUserExists(user))
					{
						session = HibernateUtil.getSessionFactory().openSession();
						session.beginTransaction();
						Query query = session.createQuery(hql);
						itemsOfUser = query.list();
						session.getTransaction().commit();
						
					}
					else
					{
						session.close();
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				//	rollBackSession();
				//	throw new ToDoListWebException("Cannot get items", e);
				}
				finally
				{
					session.close();
				}
				return itemsOfUser;
			}

		public User getUser(int userID) {
			
			 Session session = null;
			 User user = null;
			 try{
					session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();

			 	 Query query = session.createQuery("from User where ID='"+userID+"'");
				 user = (User)query.uniqueResult();
				 session.getTransaction().commit();
			 } catch (Exception e) {
				 if (session != null) {
					 session.beginTransaction().rollback();
				 }
				 e.printStackTrace();
			 } finally {
				 session.close();
			 }
			 return user;
		}

		public Item getItem(int itemID) {
			 Session session = null;
			 Item item = null;
			 try{
					session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();

			 	 Query query = session.createQuery("from Item where ITEM_ID='"+itemID+"'");
				 item = (Item)query.uniqueResult();
				 session.getTransaction().commit();
			 } catch (Exception e) {
				 if (session != null) {
					 session.beginTransaction().rollback();
				 }
				 e.printStackTrace();
			 } finally {
				 session.close();
			 }
			 return item;
		}

	/*	public boolean updateItem(Item updateItem) {
			Session session = null;
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				session.update(updateItem);
				session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if ((session != null) && (session.isOpen()))session.close();
			}
			//session.close();
			return true;
		}*/
		@Override
		public boolean updateItem(Item item) 
		{
			boolean success = true;
			if (isUserExists(item.getItemid()))
			{
				Session session = null;
				try
				{
					session = HibernateUtil.getSessionFactory().openSession();
					session.beginTransaction();
					Item tempInstanceOfItem = (Item) session.load(Item.class, item.getItemid());
					if (tempInstanceOfItem != null && tempInstanceOfItem.getItemid() == item.getItemid())
					{
						if (item.getDescription() != null)
						{
							tempInstanceOfItem.setDescription(item.getDescription());
						}
						if (item.getName() != null)
						{
							tempInstanceOfItem.setName(item.getName());
						}
						session.save(tempInstanceOfItem);
						session.getTransaction().commit();
					}
					else
					{
						throw new HibernateException("Update Error: You cannot change this item.");
					}
				}
				catch (HibernateException e)
				{
					success = false;
					if (session.getTransaction() != null)
					{
						session.getTransaction().rollback();
					}
					else
					{
						throw new HibernateException("Error: Transaction is empty");
					}
				}
				finally
				{
					session.close();
				}
				return success;
			}
			else
			{
				System.out.println("no user connected");
				return !success;
			}
		}


		public boolean isUserExists(int userID) {
			 Session session = null;
			 boolean result = false;
			 try{
			 session = HibernateUtil.getSessionFactory().openSession();
				 session.beginTransaction();
				 Query query = session.createQuery("from User where ID ='"+userID+"'");
				 session.getTransaction().commit();
				 if (query != null)
					 result = true;
			 }catch(Exception e){
				e.printStackTrace();
			 }finally{
				 session.close();
			 }
			return result;
		}

		public boolean connect(User user) 
		{
			return connect(user.getUsername(), user.getPassword());

		}

		/**
		 * an overloading of connect of the IToDoListUserDAO interface.
		 *
		 * @param i_User
		 *            the i_ user
		 * @return true, if successfull
		 * @throws HibernateToDoListDAOException
		 *             the hibernate to do list dao exception
		 */
		public boolean connect(String name, String password) 
		{
			return isUserExists(name, password);
		}
	
		private boolean isUserExists(String Name, String Password)
		{
			return isUserExists(new User(0, Name, Password));
		}

		/**
		 * Checks if is user exists.
		 *
		 * @param user
		 *            the user
		 * @return true, if is user exists
		 */
		 public boolean isUserExists(User user)
		{
			return isUserExists(user.getId());
		}
		
}


