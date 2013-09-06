package org.experimentalerp.phonebook.GwtEventBus;

import com.google.gwt.event.shared.EventHandler;

public interface SaveEventHandler extends EventHandler {
	public void onSave(SaveEvent event);

}
