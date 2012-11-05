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
package org.lunifera.web.vaadin.contextlistener;

import org.lunifera.web.vaadin.common.Constants;
import org.lunifera.web.vaadin.common.IVaadinWebApplication;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.log.LogService;

import com.vaadin.server.AddonContextEvent;
import com.vaadin.server.AddonContextListener;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.UIProvider;

@SuppressWarnings("serial")
public class AddonContextListenerImpl implements AddonContextListener {

	private SessionListener sessionListener = new SessionListener();
	private LogService logService;

	public void activate(ComponentContext context) {
		bindLogService(context.getBundleContext());
	}

	protected void bindLogService(BundleContext context) {
		ServiceReference<LogService> ref = context
				.getServiceReference(LogService.class);
		logService = context.getService(ref);
	}

	@Override
	public void contextCreated(AddonContextEvent event) {
		event.getAddonContext().getService()
				.addSessionInitListener(sessionListener);
	}

	@Override
	public void contextDestroyed(AddonContextEvent event) {
		event.getAddonContext().getService()
				.removeSessionInitListener(sessionListener);
	}

	private class SessionListener implements SessionInitListener {
		@Override
		public void sessionInit(SessionInitEvent event) throws ServiceException {
			String name = findWebApplicationName(event);
			IVaadinWebApplication webApp = Activator
					.getVaadinWebApplication(name);
			if (webApp == null) {
				logService.log(LogService.LOG_ERROR, String.format(
						"No vaadin web application available for %s", name));
			} else {
				for (UIProvider provider : webApp.getUiProviders()) {
					event.getSession().addUIProvider(provider);
				}
			}
		}

		/**
		 * Returns the name of the web application.
		 * 
		 * @param event
		 * @return
		 */
		private String findWebApplicationName(SessionInitEvent event) {
			String name = (String) event.getSource()
					.getDeploymentConfiguration().getInitParameters()
					.get(Constants.OSGI_COMP_NAME__WEBAPPLICATION);
			return name;
		}
	}
}
