/*******************************************************************************
 * Copyright (c) 2011 Florian Pirchner
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Florian Pirchner - initial API and implementation
 *******************************************************************************/
package org.lunifera.web.vaadin.bridge.common;

import java.util.Dictionary;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.lunifera.web.vaadin.bridge.VaadinOSGiSessionManager;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.http.HttpService;

/**
 * A factory provided by OSGI-Services to contribute servlets.
 */
public interface IServletFactory {

	/**
	 * Returns a servlet to be used by the {@link VaadinOSGiSessionManager}.
	 * 
	 * @param factory
	 *            which has create the {@link Application}
	 * @param servletpath
	 *            which is used to register the servlet
	 * @param initialProperties
	 *            Properties registered against the component factory.
	 * @return
	 */
	HttpServlet createServlet(ComponentFactory factory, String servletpath,
			Map<String, Object> initialProperties);

	/**
	 * Is responsible to append servlet parameters to the given dictionary that
	 * is used as additional information for registering the servlet at the http
	 * service. <br/>
	 * See
	 * {@link HttpService#registerServlet(String, javax.servlet.Servlet, Dictionary, org.osgi.service.http.HttpContext)}
	 * 
	 * @param dictionary
	 *            Properties registered against the component factory.
	 * @return
	 */
	void appendServletParams(Dictionary<String, String> dictionary);

	/**
	 * Is responsible to build the servlet path. If <code>null</code> or an
	 * empty String is returned, the base path is used as servlet path.
	 * 
	 * @param basePath
	 * @return
	 */
	String buildPath(String basePath);

	/**
	 * Returns the order the servlets should be registered. Note, that this
	 * order can only be used for servlets that are contained in the same
	 * bundle. Otherwise the start order of the servlet factories is not
	 * defined.
	 * 
	 * @return The order of the servlet
	 */
	int getRegistrationOrder();
}
