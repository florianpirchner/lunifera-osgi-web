/*******************************************************************************
 * Copyright (c) 2012 by committers of lunifera.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Florian Pirchner - initial API and implementation
 *    
 *******************************************************************************/
package org.lunifera.web.vaadin.example;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Specify the class name after the factory name.
 */
@Theme(Reindeer.THEME_NAME)
public class Vaadin7DemoUI extends UI {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(VaadinRequest request) {
		VerticalLayout layout = new VerticalLayout(new Label(
				"Hi - that's the amazing vaadin 7 demo UI!"));
		layout.setStyleName(Reindeer.LAYOUT_BLUE);
		layout.setSizeFull();
		layout.setMargin(true);
		setContent(layout);
	}
}
