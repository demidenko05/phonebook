package org.experimentalerp.phonebook.GwtEventBus;

import com.google.gwt.event.shared.EventHandler;

public interface GetUsersByKeyEventHandler extends EventHandler {
	public void onFind(GetUsersByKeyEvent event);

}
