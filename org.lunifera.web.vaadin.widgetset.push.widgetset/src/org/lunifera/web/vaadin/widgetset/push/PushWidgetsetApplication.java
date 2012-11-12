package org.lunifera.web.vaadin.widgetset.push;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

public class PushWidgetsetApplication extends UI {
	@Override
	public void init(VaadinRequest request) {
		Label label = new Label("Hello Vaadin user");
		addComponent(label);
	}

}
