package org.experimentalerp.phonebook.GwtService;

import java.util.List;
import org.experimentalerp.phonebook.model.IUser;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PhoneBookServiceAsync {
	public void saveUser(IUser user, AsyncCallback<Void> callback);
    public void deleteUser(int id, AsyncCallback<Void> callback);
    public void getAllUsers(AsyncCallback<List<IUser>> callback);
    public void getUsersByLogin(String login, AsyncCallback<IUser> callback);
    public void getUsersById(int id, AsyncCallback<IUser> callback);
    public void findByKey(String key, AsyncCallback<List<IUser>> callback);
}
