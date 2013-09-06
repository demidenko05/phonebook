package org.experimentalerp.phonebook.dao;

import java.util.List;
import org.experimentalerp.phonebook.model.IUser;

public interface UserDao {
	public IUser getUser(int id);
	public int insertUser(IUser user);
	public boolean deleteUser(int id);
	public boolean updateUser(IUser user);
	public List<IUser> getAllUsers();
	public IUser getUserByLogin(String login);
	public List<IUser> findByKey(String key);
}
