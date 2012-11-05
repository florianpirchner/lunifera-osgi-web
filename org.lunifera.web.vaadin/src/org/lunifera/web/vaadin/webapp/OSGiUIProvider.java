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
package org.lunifera.web.vaadin.webapp;

import java.util.Dictionary;

import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;

import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.UI;
import com.vaadin.ui.UI.CleanupEvent;
import com.vaadin.ui.UI.CleanupListener;

@SuppressWarnings("serial")
public class OSGiUIProvider extends UIProvider {
 
	private final ComponentFactory factory;
	private final Class<? extends UI> uiClass;

	@SuppressWarnings("rawtypes")
	public OSGiUIProvider(ComponentFactory factory,
			Class<? extends UI> uiClass, Dictionary properties) {
		super();
		this.factory = factory;
		this.uiClass = uiClass;
	}

	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		return uiClass;
	}

	@Override
	public UI createInstance(UICreateEvent event) {
		ComponentInstance instance = factory.newInstance(null);
		UI ui = (UI) instance.getInstance();
		// create a new lifecylce
		new InstanceLifecycle(instance, event);

		return ui;
	}

	/**
	 * Handles the disposal of the session.
	 */
	private static class InstanceLifecycle implements SessionDestroyListener,
			CleanupListener {

		private ComponentInstance instance;
		private VaadinService service;

		public InstanceLifecycle(ComponentInstance instance, UICreateEvent event) {
			this.instance = instance;
			this.service = event.getService();
			this.service.addSessionDestroyListener(this);
			UI ui = (UI) instance.getInstance();
			ui.addCleanupListener(this);
		}

		@Override
		public void sessionDestroy(SessionDestroyEvent event) {
			if (event.getService() == service) {
				dispose();
			}
		}

		@Override
		public void cleanup(CleanupEvent event) {
			dispose();
		}

		private void dispose() {
			instance.dispose();
			instance = null;

			service.removeSessionDestroyListener(this);
			service = null;
		}
	}
}
