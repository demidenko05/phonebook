/**
 * Yury Demodenko 2013. 
 * based on Sengha examples (http://www.sencha.com/examples/#ExamplePlace:gridbinding).
 * GNU GPL license v3 (http://www.gnu.org/copyleft/gpl.html)
 */

package org.experimentalerp.phonebook.GwtView;

import java.util.ArrayList;
import java.util.List;
import org.experimentalerp.phonebook.GwtEventBus.DeleteEvent;
import org.experimentalerp.phonebook.GwtEventBus.EditOrDeleteEvent;
import org.experimentalerp.phonebook.GwtEventBus.GetAllUsersEvent;
import org.experimentalerp.phonebook.GwtEventBus.GetUserByLoginEvent;
import org.experimentalerp.phonebook.GwtEventBus.GetUsersByKeyEvent;
import org.experimentalerp.phonebook.GwtEventBus.SaveEvent;
import org.experimentalerp.phonebook.GwtEventBus.ServiceBus;
import org.experimentalerp.phonebook.GwtModel.UserProperties;
import org.experimentalerp.phonebook.model.IUser;
import org.experimentalerp.phonebook.model.User;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class PhoneBook implements EntryPoint, IsWidget, Editor<org.experimentalerp.phonebook.GwtView.PhoneBook.UsersExchange>{
			
	public static class UsersExchange{
		private List<IUser> users=new ArrayList<IUser>();		
		private TextField tfFLogin=new TextField();
		private TextField tfFKey=new TextField();
		
		public TextField getTfFLogin() {
			return tfFLogin;
		}

		public void setTfFLogin(TextField tfFLogin) {
			this.tfFLogin = tfFLogin;
		}

		public TextField getTfFKey() {
			return tfFKey;
		}

		public void setTfFKey(TextField tfFKey) {
			this.tfFKey = tfFKey;
		}

		public List<IUser> getUsers() {
			return users;
		}

		public void setUsers(List<IUser> users) {
			this.users = users;
		}
		
	}

	public interface UserDriver extends SimpleBeanEditorDriver<IUser, UserEditor> {}

	public interface ListDriver extends SimpleBeanEditorDriver<UsersExchange, PhoneBook> {}

	private ListDriver driver = GWT.create(ListDriver.class);
	private UserDriver itemDriver = GWT.create(UserDriver.class);
	
	private FramedPanel panel;
	
	Grid<IUser> userList;
	ListStoreEditor<IUser> users;	
	@Ignore
	UserEditor userEditor;
	
	private UsersExchange userExchange=new UsersExchange();
	
	@Override
	public void onModuleLoad() {
		RootPanel.get().add(this);
		fireRefresh();
	}

	public Widget asWidget() {
		if(panel==null){
			panel=new FramedPanel();
			panel.setPixelSize(400, 400);
			panel.setHeadingText("Users");
			VerticalLayoutContainer vlc=new VerticalLayoutContainer();
			VerticalPanel vpFilter=new VerticalPanel();
			RadioButton rbFNon = new RadioButton("filter", "Non");
			rbFNon.setValue(true);
			vpFilter.add(rbFNon);
			rbFNon.addClickHandler(new ClickHandler() {		
				@Override
				public void onClick(ClickEvent event) {
					fireRefresh();
				}
			});
			final RadioButton rbFLogin = new RadioButton("filter", "Login");
			HorizontalPanel hpFLogin=new HorizontalPanel();			
			hpFLogin.add(rbFLogin);
			hpFLogin.add(userExchange.getTfFLogin());
			vpFilter.add(hpFLogin);
			rbFLogin.addClickHandler(new ClickHandler() {		
				@Override
				public void onClick(ClickEvent event) {
					fireGetUserByLogin();
				}
			});
			userExchange.getTfFLogin().addChangeHandler(new ChangeHandler() {				
				@Override
				public void onChange(ChangeEvent arg0) {
					if(!rbFLogin.getValue()) rbFLogin.setValue(true);
					fireGetUserByLogin();
				}
			});
			final RadioButton rbFKey = new RadioButton("filter", "Login or Phone contains of");
			HorizontalPanel hpFKey=new HorizontalPanel();			
			hpFKey.add(rbFKey);
			hpFKey.add(userExchange.getTfFKey());
			vpFilter.add(hpFKey);
			rbFKey.addClickHandler(new ClickHandler() {		
				@Override
				public void onClick(ClickEvent event) {
					fireFindUserByKey();
				}
			});
			userExchange.getTfFKey().addChangeHandler(new ChangeHandler() {				
				@Override
				public void onChange(ChangeEvent arg0) {
					if(!rbFKey.getValue()) rbFKey.setValue(true);
					fireFindUserByKey();
				}
			});
			vlc.add(vpFilter);
			final UserProperties props=GWT.create(UserProperties.class);
			List<ColumnConfig<IUser, ?>> columns=new ArrayList<ColumnConfig<IUser, ?>>();
			columns.add(new ColumnConfig<IUser, String>(props.login(), 50, "Login"));
			columns.add(new ColumnConfig<IUser, String>(props.phone(), 50, "Phone"));
			userList=new Grid<IUser>(new ListStore<IUser>(props.key()), new ColumnModel<IUser>(columns));
			userList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			userList.setBorders(true);
			users=new ListStoreEditor<IUser>(userList.getStore());
			vlc.add(userList, new VerticalLayoutData(1, 1));
			userList.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<IUser>() {
				@Override
				public void onSelectionChanged(SelectionChangedEvent<IUser> event) {
					if (event.getSelection().size() > 0) {
						fireEditOrDelete(event.getSelection().get(0));
			          } 
					else {
			            userEditor.setEnabled(false);
					}
				}
			});
			TextButton btnAdd;
		    btnAdd=new TextButton("Add");
		    vlc.add(btnAdd);
		    btnAdd.addSelectHandler(new SelectHandler() {			
				@Override
				public void onSelect(SelectEvent event) {
					IUser user=new User();
					userList.getStore().add(user);
					fireEditOrDelete(user);
				}
			});
			userEditor=new UserEditor();
			userEditor.getSaveButton().addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					fireSave();
				}
			});
			userEditor.getDeleteButton().addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					fireDelete();
				}
			});
			vlc.add(userEditor, new VerticalLayoutData(1, -1, new Margins(5)));
			panel.add(vlc);
			itemDriver.initialize(userEditor);
			driver.initialize(this);
			driver.edit(userExchange);
			findAllEvent=new GetAllUsersEvent(userExchange, userList);
			findUsersByKeyEvent=new GetUsersByKeyEvent(userExchange, userList);
			findUserByLoginEvent=new GetUserByLoginEvent(userExchange, userList);
			deleteEvent=new DeleteEvent(userList, userEditor, itemDriver);
			saveEvent=new SaveEvent(userList, userEditor, itemDriver);
			editOrDeleteEvent=new EditOrDeleteEvent(userList, userEditor, itemDriver);
		}
		return panel;
	}
	
	//events:
	private GetAllUsersEvent findAllEvent;
	private GetUsersByKeyEvent findUsersByKeyEvent;
	private GetUserByLoginEvent findUserByLoginEvent;
	private DeleteEvent deleteEvent;
	private SaveEvent saveEvent;
	private EditOrDeleteEvent editOrDeleteEvent;
	
	protected void fireDelete() {
		ServiceBus.EVENT_BUS.fireEvent(deleteEvent);
	}

	private void fireRefresh(){
		ServiceBus.EVENT_BUS.fireEvent(findAllEvent);
	}

	private void fireFindUserByKey() {
		ServiceBus.EVENT_BUS.fireEvent(findUsersByKeyEvent);
	}

	private void fireGetUserByLogin() {
		ServiceBus.EVENT_BUS.fireEvent(findUserByLoginEvent);
	}

	private void fireSave() {
		ServiceBus.EVENT_BUS.fireEvent(saveEvent);
	}

	private void fireEditOrDelete(IUser user) {
		editOrDeleteEvent.setUser(user);
		ServiceBus.EVENT_BUS.fireEvent(editOrDeleteEvent);
	}
			
}
