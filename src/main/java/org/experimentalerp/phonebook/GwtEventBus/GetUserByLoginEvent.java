package org.experimentalerp.phonebook.GwtEventBus;

import org.experimentalerp.phonebook.GwtView.PhoneBook.UsersExchange;
import org.experimentalerp.phonebook.model.IUser;
import com.google.gwt.event.shared.GwtEvent;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class GetUserByLoginEvent extends GwtEvent <GetUserByLoginEventHandler>{

	public static Type<GetUserByLoginEventHandler> TYPE = new Type<GetUserByLoginEventHandler>();
	
	private UsersExchange userExchange;
	private Grid<IUser> userList;

	public GetUserByLoginEvent(UsersExchange userExchange, Grid<IUser> userList) {
		super();
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
	protected void dispatch(GetUserByLoginEventHandler handler) {
		handler.onFind(this);		
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<GetUserByLoginEventHandler> getAssociatedType() {
		return TYPE;
	}

}
