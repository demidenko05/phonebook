package org.experimentalerp.phonebook.GwtService;

import java.util.List;
import org.experimentalerp.phonebook.model.IUser;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface PhoneBookService extends RemoteService{
	public void saveUser(IUser user);
    public void deleteUser(int id);
    public List<IUser> getAllUsers();
    public IUser getUsersByLogin(String login);
    public IUser getUsersById(int id);
    public List<IUser> findByKey(String key);
}
