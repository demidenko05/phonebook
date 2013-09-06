package org.experimentalerp.phonebook.GwtEventBus;

import org.experimentalerp.phonebook.GwtView.UserEditor;
import org.experimentalerp.phonebook.GwtView.PhoneBook.UserDriver;
import org.experimentalerp.phonebook.model.IUser;
import com.google.gwt.event.shared.GwtEvent;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class SaveEvent extends GwtEvent <SaveEventHandler>{

	public static Type<SaveEventHandler> TYPE = new Type<SaveEventHandler>();
	
	private Grid<IUser> userList;
	private UserEditor userEditor;
	private UserDriver itemDriver;
	private IUser user;
		
	public SaveEvent(Grid<IUser> userList, UserEditor userEditor,
			UserDriver itemDriver) {
		this.userList = userList;
		this.userEditor = userEditor;
		this.itemDriver = itemDriver;
	}

	public Grid<IUser> getUserList() {
		return userList;
	}

	public void setUserList(Grid<IUser> userList) {
		this.userList = userList;
	}

	public UserEditor getUserEditor() {
		return userEditor;
	}

	public void setUserEditor(UserEditor userEditor) {
		this.userEditor = userEditor;
	}

	public UserDriver getItemDriver() {
		return itemDriver;
	}

	public void setItemDriver(UserDriver itemDriver) {
		this.itemDriver = itemDriver;
	}

	@Override
	protected void dispatch(SaveEventHandler handler) {
		handler.onSave(this);		
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SaveEventHandler> getAssociatedType() {
		return TYPE;
	}

	public IUser getUser() {
		return user;
	}

	public void setUser(IUser user) {
		this.user = user;
	}

}
