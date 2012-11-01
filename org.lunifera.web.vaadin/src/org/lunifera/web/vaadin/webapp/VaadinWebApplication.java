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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lunifera.web.vaadin.OSGiUIProvider;
import org.lunifera.web.vaadin.common.IVaadinWebApplication;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.log.LogService;

import com.vaadin.server.UIProvider;

public class VaadinWebApplication implements IVaadinWebApplication {

	private BundleContext bundleContext;
	private String alias;
	private String id;
	private LogService logService;

	private HttpServiceTracker tracker;
	private UIProviderTracker uiProviderTracker;
	private List<OSGiUIProvider> uiProviders = new ArrayList<OSGiUIProvider>();

	protected void bindLogService(BundleContext context) {
		ServiceReference<LogService> ref = context
				.getServiceReference(LogService.class);
		logService = context.getService(ref);

		logService.log(LogService.LOG_DEBUG, "Binded LogService.");
	}

	public void activate(final ComponentContext context) {

		alias = (String) context.getProperties().get(
				org.lunifera.web.vaadin.common.Constants.OSGI_WEBAPP__ALIAS);
		id = (String) context.getProperties().get("component.name");
		// remember bundle for later use
		bundleContext = context.getBundleContext();

		bindLogService(bundleContext);

		try {
			uiProviderTracker = new UIProviderTracker(bundleContext,
					logService, this);
		} catch (InvalidSyntaxException e) {
			new RuntimeException(e);
		}
		uiProviderTracker.open();

		tracker = new HttpServiceTracker(context.getBundleContext(), this,
				logService);
		logService.log(LogService.LOG_DEBUG,
				"The alias that will be tracked is:\"" + alias);
		tracker.open();
	}

	public void deactivate(final ComponentContext context) {
		logService.log(LogService.LOG_DEBUG,
				"Tracker for alias" + tracker.getAlias() + " was removed.");
		if (uiProviderTracker != null) {
			uiProviderTracker.close();
			uiProviderTracker = null;
		}
		if (tracker != null) {
			tracker.close();
			tracker = null;
		}
		
		uiProviders = null;
	}

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public String getName() {
		return id;
	}

	/**
	 * Adds a new {@link UIProvider} to the web application.
	 * 
	 * @param provider
	 */
	public void addOSGiUIProvider(OSGiUIProvider provider) {
		uiProviders.add(provider);
	}

	/**
	 * Removes an {@link UIProvider} from the web application.
	 * 
	 * @param provider
	 */
	public void removeOSGiUIProvider(OSGiUIProvider provider) {
		uiProviders.remove(provider);
	}

	/**
	 * Returns all ui providers.
	 */
	@Override
	public List<OSGiUIProvider> getUiProviders() {
		return Collections.unmodifiableList(uiProviders);
	}

}
