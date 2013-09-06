package org.experimentalerp.phonebook.GwtEventBus;

import com.google.gwt.event.shared.EventHandler;

public interface GetUserByLoginEventHandler extends EventHandler {
	public void onFind(GetUserByLoginEvent event);

}
