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
 *******************************************************************************/
package org.lunifera.web.vaadin.designstudy01.services;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	// Reference to service
	// private IMyService service;

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		// Shows how services can registered manually
		// service = new MyService();
		// bundleContext.registerService(IMyService.class,
		// service, null).getReference();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		// Do not forget to set service to null after bundle stop
		// service = null;
		Activator.context = null;
	}
}
