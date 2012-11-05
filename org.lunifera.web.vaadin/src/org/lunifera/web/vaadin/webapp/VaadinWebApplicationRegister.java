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
 *				- com.c4biz.osgiutils.vaadin.equinox.shiro.ApplicationRegister from Cristiano Gaviao
 *
 * Contributors:
 *    Florian Pirchner - migrated to vaadin 7 and copied into org.lunifera namespace
 *    
 *******************************************************************************/
package org.lunifera.web.vaadin.webapp;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.Filter;

import org.apache.shiro.web.servlet.IniShiroFilter;
import org.eclipse.equinox.http.servlet.ExtendedHttpService;
import org.lunifera.web.vaadin.Activator;
import org.lunifera.web.vaadin.common.IVaadinWebApplication;
import org.lunifera.web.vaadin.servlet.WebResourcesHttpContext;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.http.HttpContext;

import com.vaadin.server.VaadinServlet;

/**
 * This class is responsible for registering the {@link ComponentFactory} as a
 * vaadin {@link VaadinSession}. It is a {@link ManagedService} so that it can
 * receive properties which are then passed in to the {@link VaadinOSGiServlet}
 * as init parameters, e.g. to enable production mode.
 */
@SuppressWarnings("deprecation")
public class VaadinWebApplicationRegister implements ManagedService {

	private final ExtendedHttpService http;
	private final String alias;

	private VaadinServlet servlet;

	private Filter filter;

	private final String RESOURCE_BASE = "/VAADIN";
	private IVaadinWebApplication webApplication;

	public VaadinWebApplicationRegister(ExtendedHttpService http,
			IVaadinWebApplication webApplication) {
		super();
		this.http = http;
		this.webApplication = webApplication;
		this.alias = webApplication.getAlias();
	}

	public void kill() {
		if (filter != null) {
			http.unregisterFilter(filter);
			filter = null;
		}
		if (alias != null) {
			try {
				http.unregister(alias);
				http.unregister(RESOURCE_BASE);
			} catch (java.lang.IllegalArgumentException e) {
				// ignore in case alias was not found exception
			}
		}
		if (servlet != null) {
			servlet.destroy();
			servlet = null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void updated(Dictionary properties) throws ConfigurationException {
		kill();

		try {
			if (properties == null) {
				properties = new Hashtable();
			}
			properties
					.put(org.lunifera.web.vaadin.common.Constants.OSGI_COMP_NAME__WEBAPPLICATION,
							webApplication.getName());

			servlet = new VaadinServlet();
			HttpContext defaultContext = new WebResourcesHttpContext(Activator
					.getBundleContext().getBundle());
			http.registerFilter("/", getSecurityFilter(), properties,
					defaultContext);
			http.registerResources(RESOURCE_BASE, RESOURCE_BASE, defaultContext);
			http.registerServlet(alias, servlet, properties, defaultContext);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Filter getSecurityFilter() {
		if (filter == null) {
			filter = new IniShiroFilter();
		}
		return filter;
	}
}
