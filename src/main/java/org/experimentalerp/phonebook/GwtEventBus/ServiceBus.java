package org.experimentalerp.phonebook.GwtEventBus;
import java.util.List;

import org.experimentalerp.phonebook.GwtService.PhoneBookService;
import org.experimentalerp.phonebook.GwtService.PhoneBookServiceAsync;
import org.experimentalerp.phonebook.model.IUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ServiceBus {

	public static final EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);
	public static final PhoneBookServiceAsync PHONE_BOOK_SERVICE=GWT.create(PhoneBookService.class);

	static{
		
		EVENT_BUS.addHandler(EditOrDeleteEvent.TYPE, new EditOrDeleteEventHandler() {		
			@Override
			public void onInvokeEditOrDelete(EditOrDeleteEvent event) {
				event.getItemDriver().edit(event.getUser());
				event.getUserEditor().setEnabled(true);
			}
		});
		
		EVENT_BUS.addHandler(SaveEvent.TYPE, new SaveEventHandler() {		
			@Override
			public void onSave(final SaveEvent event) {
				event.setUser(event.getItemDriver().flush());
				if (!event.getItemDriver().hasErrors()){
					PHONE_BOOK_SERVICE.saveUser(event.getUser(), new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable e) {
							Window.alert(e.getMessage());				
						}
						@Override
						public void onSuccess(Void arg0) {
							event.getUserList().getStore().update(event.getUser());
							event.getUserEditor().setEnabled(false);
						}
					});
				}		
			}
		});
		
		EVENT_BUS.addHandler(DeleteEvent.TYPE, new DeleteEventHandler() {		
			@Override
			public void onDelete(final DeleteEvent event) {
				if(Window.confirm("Delete? Are you shure?")){
					event.setUser(event.getItemDriver().flush());
					if (!event.getItemDriver().hasErrors()){
						if(event.getUser().getId()>0){
							PHONE_BOOK_SERVICE.deleteUser(event.getUser().getId(), new AsyncCallback<Void>() {
								@Override
								public void onFailure(Throwable e) {
									Window.alert(e.getMessage());				
								}
								@Override
								public void onSuccess(Void arg0) {
									event.getUserList().getStore().remove(event.getUser());
								}
							});
						}
						else{
							event.getUserList().getStore().remove(event.getUser());
						}
						event.getUserEditor().setEnabled(false);
					}		
				}
			}
		});
		
		EVENT_BUS.addHandler(GetUserByLoginEvent.TYPE, new GetUserByLoginEventHandler() {		
			@Override
			public void onFind(final GetUserByLoginEvent event) {
				if (event.getUserExchange().getTfFLogin().getText()!=null){
					String filter=event.getUserExchange().getTfFLogin().getText().trim();
					if(!filter.equals("")){
						PHONE_BOOK_SERVICE.getUsersByLogin(filter,new AsyncCallback<IUser>() {
							@Override
							public void onFailure(Throwable e) {
								Window.alert(e.getMessage());				
							}
							@Override
							public void onSuccess(IUser user) {
								event.getUserList().getStore().clear();
								event.getUserList().getStore().add(user);
							}
						});
					}
				}		
			}
		});
		
		EVENT_BUS.addHandler(GetUsersByKeyEvent.TYPE, new GetUsersByKeyEventHandler() {		
			@Override
			public void onFind(final GetUsersByKeyEvent event) {
				if (event.getUserExchange().getTfFKey().getText()!=null){
					String filter=event.getUserExchange().getTfFKey().getText().trim();
					if(!filter.equals("")){
						PHONE_BOOK_SERVICE.findByKey(filter,new AsyncCallback<List<IUser>>() {
							@Override
							public void onFailure(Throwable e) {
								Window.alert(e.getMessage());				
							}
							@Override
							public void onSuccess(List<IUser> users) {
								event.getUserList().getStore().clear();
								event.getUserList().getStore().addAll(users);
							}
						});
					}
				}		
			}
		});

		EVENT_BUS.addHandler(GetAllUsersEvent.TYPE, new GetAllUsersEventHandler() {		
			@Override
			public void onFind(final GetAllUsersEvent event) {
				PHONE_BOOK_SERVICE.getAllUsers(new AsyncCallback<List<IUser>>() {
					@Override
					public void onFailure(Throwable e) {
						Window.alert(e.getMessage());				
					}
					@Override
					public void onSuccess(List<IUser> users) {
						event.getUserList().getStore().clear();
						event.getUserList().getStore().addAll(users);
					}
				});
			}
		});

	}
}
