/**
 * Yury Demodenko 2013. 
 * based on Sengha examples.
 * GNU GPL license v3
 */
package org.experimentalerp.phonebook.GwtView;

import org.experimentalerp.phonebook.model.IUser;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class UserEditor implements IsWidget, Editor<IUser> {

  private FormPanel panel;
  private VerticalLayoutContainer container;

  TextField login;
  TextField phone;

  private TextButton btnSave, btnDelete;
  
  public UserEditor() {
    panel = new FormPanel();
    panel.setLabelWidth(50);

    container = new VerticalLayoutContainer();
    panel.setWidget(container);

    login = new TextField();
    login.setEnabled(false);
    phone = new TextField();
    phone.setEnabled(false);

    container.add(new FieldLabel(login, "Login"), new VerticalLayoutData(1, -1));
    container.add(new FieldLabel(phone, "Phone"), new VerticalLayoutData(1, -1));

    btnSave=new TextButton("Save");
    btnSave.setEnabled(false);
    container.add(btnSave);
    
    btnDelete=new TextButton("Delete");
    btnDelete.setEnabled(false);
    container.add(btnDelete);

    panel.setLabelWidth(50);
  }

  public Widget asWidget() {
    return panel;
  }
  
  public void setEnabled(boolean enabled) {
	    btnSave.setEnabled(enabled);
	    btnDelete.setEnabled(enabled);
	    login.setEnabled(enabled);
	    phone.setEnabled(enabled);
	    if(!enabled){
	    	login.setText("");
	    	phone.setText("");	    	
	    }
  }
	  	  
  public HasSelectHandlers getSaveButton() {
	    return btnSave;
	  }
	  
  public HasSelectHandlers getDeleteButton() {
	    return btnDelete;
	  }
	  
}