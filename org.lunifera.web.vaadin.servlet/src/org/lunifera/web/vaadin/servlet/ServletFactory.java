/*******************************************************************************
 * Copyright (c) 2010, 2011 Florian Pirchner (Vienna, Austria) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Florian Pirchner   - ideas, initial API and implementation
 *    
 * more info: http://redvoodo.org
 *    
 *******************************************************************************/
package org.lunifera.web.vaadin.servlet;

import java.util.Dictionary;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.lunifera.web.vaadin.bridge.common.IServletFactory;
import org.osgi.service.component.ComponentFactory;

public class ServletFactory implements IServletFactory {
	@Override
	public HttpServlet createServlet(ComponentFactory factory,
			String servletpath, Map<String, Object> properties) {
		return new VaadinOSGiServlet(factory);
	}

	@Override
	public String buildPath(String basePath) {
		return basePath;
	}

	@Override
	public void appendServletParams(Dictionary<String, String> dictionary) {

	}

	@Override
	public int getRegistrationOrder() {
		return 1;
	}

}
