package org.experimentalerp.phonebook.service;

import java.util.List;
import org.experimentalerp.phonebook.model.IUser;

public interface IUserAction {
	public long createUser(String login, String phone);
    public void updateUser(int id, String login, String phone);
    public void deleteUser(int id);
    public List<IUser> getAllUsers();
    public IUser getUsersByLogin(String login);
    public IUser getUsersById(int id);

    /**
     * This method returns list of ${@link model.IUser} by appropriate search key.
     * The method should search by login, phone and partial matches.
     * @param key search key
     * @return list of ${@link model.IUser}
     */
    public List<IUser> findByKey(String key);
}
