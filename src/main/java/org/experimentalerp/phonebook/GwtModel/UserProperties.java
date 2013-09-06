package org.experimentalerp.phonebook.GwtModel;

import org.experimentalerp.phonebook.model.IUser;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface UserProperties extends PropertyAccess<IUser> {
	  @Path("id")
	  ModelKeyProvider<IUser> key();

	  @Path("login")
	  LabelProvider<IUser> nameLabel();

	  ValueProvider<IUser, String> login();
	  ValueProvider<IUser, String> phone();
}
