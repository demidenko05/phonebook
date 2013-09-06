package org.experimentalerp.phonebook.service;

import java.util.List;

import org.experimentalerp.phonebook.dao.UserDao;
import org.experimentalerp.phonebook.model.IUser;
import org.experimentalerp.phonebook.model.User;

public class UserAction implements IUserAction {

	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public long createUser(String login, String phone) {
		IUser user=new User(login, phone);
		return userDao.insertUser(user);
	}

	public void updateUser(int id, String login, String phone) {
		IUser user=userDao.getUser(id);
		user.setLogin(login);
		user.setPhone(phone);
		userDao.updateUser(user);
	}

	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}

	public List<IUser> getAllUsers() {
		return userDao.getAllUsers();
	}

	public IUser getUsersByLogin(String login) {
		return userDao.getUserByLogin(login);
	}

	public IUser getUsersById(int id) {
		return userDao.getUser(id);
	}

	public List<IUser> findByKey(String key) {
		return userDao.findByKey(key);
	}

}
