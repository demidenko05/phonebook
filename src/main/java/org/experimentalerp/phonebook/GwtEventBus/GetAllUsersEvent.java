package org.experimentalerp.phonebook.GwtEventBus;

import org.experimentalerp.phonebook.GwtView.PhoneBook.UsersExchange;
import org.experimentalerp.phonebook.model.IUser;
import com.google.gwt.event.shared.GwtEvent;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class GetAllUsersEvent extends GwtEvent <GetAllUsersEventHandler>{

	public static Type<GetAllUsersEventHandler> TYPE = new Type<GetAllUsersEventHandler>();
	
	private UsersExchange userExchange;
	private Grid<IUser> userList;
	
	public GetAllUsersEvent(UsersExchange userExchange, Grid<IUser> userList) {
		this.userExchange = userExchange;
		this.userList = userList;
	}

	public UsersExchange getUserExchange() {
		return userExchange;
	}

	public void setUserExchange(UsersExchange userExchange) {
		this.userExchange = userExchange;
	}

	public Grid<IUser> getUserList() {
		return userList;
	}

	public void setUserList(Grid<IUser> userList) {
		this.userList = userList;
	}

	@Override
	protected void dispatch(GetAllUsersEventHandler handler) {
		handler.onFind(this);		
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<GetAllUsersEventHandler> getAssociatedType() {
		return TYPE;
	}

}
