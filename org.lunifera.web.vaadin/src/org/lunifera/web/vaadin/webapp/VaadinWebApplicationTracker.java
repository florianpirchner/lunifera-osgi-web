/*******************************************************************************
 * Copyright (c) 2012 by committers of lunifera.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Information:
 * 		Based on original sources of 
 * 				- org.vaadin.osgi.Activator from Chris Brind
 *				- com.c4biz.osgiutils.vaadin.equinox.shiro.VaadinApplicationTracker from Cristiano Gaviao
 *
 * Contributors:
 *    Florian Pirchner - migrated to vaadin 7 and copied into org.lunifera namespace
 *    
 *******************************************************************************/
package org.lunifera.web.vaadin.webapp;

import org.lunifera.web.vaadin.common.IVaadinWebApplication;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.http.HttpService;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

/**
 * This is the tracker which looks for the {@link ComponentFactory}
 * registrations.
 * <p>
 * For each {@link ComponentFactory} found it creates a tracker for
 * {@link HttpService}.
 */
@SuppressWarnings(value = { "rawtypes", "unchecked" })
public class VaadinWebApplicationTracker extends ServiceTracker {

	private final LogService logService;

	public VaadinWebApplicationTracker(BundleContext ctx, LogService logService)
			throws InvalidSyntaxException {
		super(ctx, IVaadinWebApplication.class, null);
		this.logService = logService;
	}

	@Override
	public Object addingService(ServiceReference reference) {
		Object o = super.addingService(reference);

		IVaadinWebApplication webApplication = (IVaadinWebApplication) o;
		String alias = webApplication.getAlias();
		String id = webApplication.getName();
		logService.log(LogService.LOG_DEBUG, "WebApp " + id + " for " + alias
				+ " was added.");

		return o;
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		IVaadinWebApplication webApplication = (IVaadinWebApplication) service;
		String alias = webApplication.getAlias();
		String id = webApplication.getName();
		logService.log(LogService.LOG_DEBUG, "WebApp " + id + " for " + alias
				+ " was removed.");
	}
}
