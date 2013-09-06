package org.experimentalerp.phonebook.GwtEventBus;

import org.experimentalerp.phonebook.GwtView.PhoneBook.UsersExchange;
import org.experimentalerp.phonebook.model.IUser;
import com.google.gwt.event.shared.GwtEvent;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class GetUsersByKeyEvent extends GwtEvent <GetUsersByKeyEventHandler>{

	public static Type<GetUsersByKeyEventHandler> TYPE = new Type<GetUsersByKeyEventHandler>();
	
	private UsersExchange userExchange;
	private Grid<IUser> userList;

	public GetUsersByKeyEvent(UsersExchange userExchange, Grid<IUser> userList) {
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
	protected void dispatch(GetUsersByKeyEventHandler handler) {
		handler.onFind(this);		
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<GetUsersByKeyEventHandler> getAssociatedType() {
		return TYPE;
	}

}
