package org.experimentalerp.phonebook.GwtEventBus;

import com.google.gwt.event.shared.EventHandler;

public interface GetAllUsersEventHandler extends EventHandler {
	public void onFind(GetAllUsersEvent event);

}
