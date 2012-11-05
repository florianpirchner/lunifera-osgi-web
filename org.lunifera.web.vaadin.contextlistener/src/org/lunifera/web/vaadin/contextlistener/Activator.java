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
 *				- com.c4biz.osgiutils.vaadin.equinox.shiro.Activator from Cristiano Gaviao
 *
 * Contributors:
 *    Florian Pirchner - migrated to vaadin 7 and copied into org.lunifera namespace
 *    
 *******************************************************************************/
package org.lunifera.web.vaadin.contextlistener;

import java.util.Collection;

import org.lunifera.web.vaadin.common.IVaadinWebApplication;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private static BundleContext bundleContext;

	static BundleContext getBundleContext() {
		return bundleContext;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		bundleContext = context;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		context = null;
	}
	
	/**
	 * Returns the vaadin web application with the given name.
	 * 
	 * @param name
	 * @return
	 */
	public static IVaadinWebApplication getVaadinWebApplication(String name) {
		try {
			Collection<ServiceReference<IVaadinWebApplication>> refs = bundleContext
					.getServiceReferences(IVaadinWebApplication.class,
							"(component.name=" + name + ")");
			if (refs.size() > 0) {
				ServiceReference<IVaadinWebApplication> ref = refs.iterator()
						.next();
				return bundleContext.getService(ref);
			}
		} catch (InvalidSyntaxException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

}
