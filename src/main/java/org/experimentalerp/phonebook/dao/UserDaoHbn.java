package org.experimentalerp.phonebook.dao;

import java.util.List;

import org.experimentalerp.phonebook.model.IUser;
import org.experimentalerp.phonebook.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class UserDaoHbn implements UserDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public IUser getUser(int id) {
		return (IUser) sessionFactory.getCurrentSession().get(User.class, id);
	}

	public int insertUser(IUser user) {
		return (Integer) sessionFactory.getCurrentSession().save(user);
	}

	public boolean deleteUser(int id) {
		boolean result=false;
		try{
			sessionFactory.getCurrentSession().delete(getUser(id));
			result=true;
		}
		catch(HibernateException e){
			System.err.println(e.getMessage());
		}
		return result;
	}

	public boolean updateUser(IUser user) {
		boolean result=false;
		try{
			sessionFactory.getCurrentSession().merge(user);
			result=true;
		}
		catch(HibernateException e){
			System.err.println(e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<IUser> getAllUsers() {
		Query query=sessionFactory.getCurrentSession().createQuery("From "+User.class.getName());
		return query.list();
	}

	public IUser getUserByLogin(String login) {
		Query query=sessionFactory.getCurrentSession().createQuery("From "+User.class.getName()+" Where login='"+login+"'");
		return (IUser) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<IUser> findByKey(String key) {
		Query query=sessionFactory.getCurrentSession().createQuery("From "+User.class.getName()+" Where login Like '%"+key+"%' or  phone Like '%"+key+"%'");
		return query.list();
	}

}
