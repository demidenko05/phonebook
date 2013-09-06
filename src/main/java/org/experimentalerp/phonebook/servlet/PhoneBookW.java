package org.experimentalerp.phonebook.servlet;

import java.util.List;
import org.experimentalerp.hbnutil.HibernateUtil;
import org.experimentalerp.phonebook.GwtService.PhoneBookService;
import org.experimentalerp.phonebook.dao.UserDao;
import org.experimentalerp.phonebook.dao.UserDaoHbn;
import org.experimentalerp.phonebook.model.IUser;
import org.experimentalerp.phonebook.service.IUserAction;
import org.experimentalerp.phonebook.service.UserAction;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class PhoneBookW extends RemoteServiceServlet implements PhoneBookService{

	public static final IUserAction userAction;
	//don't use IoC/CDI
	static {
		userAction=new UserAction();
		UserDao userDao=new UserDaoHbn();
		((UserDaoHbn)userDao).setSessionFactory(HibernateUtil.getSessionFactory());
		((UserAction)userAction).setUserDao(userDao);
	}
	
	
	public void saveUser(IUser user) {
		if(user.getId()==0) userAction.createUser(user.getLogin(), user.getPhone());
		else userAction.updateUser(user.getId(), user.getLogin(), user.getPhone());
	}

	public void deleteUser(int id) {
		userAction.deleteUser(id);		
	}

	public List<IUser> getAllUsers() {
		return userAction.getAllUsers();
	}

	public IUser getUsersByLogin(String login) {
		return (IUser) userAction.getUsersByLogin(login);
	}

	public IUser getUsersById(int id) {
		return (IUser) userAction.getUsersById(id);
	}

	public List<IUser> findByKey(String key) {
		return userAction.findByKey(key);
	}
	
}
