package org.experimentalerp.phonebook.GwtEventBus;

import com.google.gwt.event.shared.EventHandler;

public interface EditOrDeleteEventHandler extends EventHandler {
	public void onInvokeEditOrDelete(EditOrDeleteEvent event);

}
