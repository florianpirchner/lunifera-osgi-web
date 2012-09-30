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
 * 				- org.vaadin.osgi.VaadinOSGiServlet from Chris Brind
 *				- com.c4biz.osgiutils.vaadin.equinox.shiro.VaadinOSGiServlet from Cristiano Gaviao
 *
 * Contributors:
 *    Florian Pirchner - migrated to vaadin 7 and copied into org.lunifera namespace
 *    
 *******************************************************************************/
package org.lunifera.web.vaadin.servlet;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;

/**
 * Used to create instances of applications that have been registered with the
 * container via a component factory.
 * 
 * @author brindy
 */
@SuppressWarnings("rawtypes")
public class VaadinOSGiServlet extends VaadinServlet implements
		OSGiServletService.IVaadinSessionManager {

	private static final long serialVersionUID = 1L;

	private final ComponentFactory factory;

	private Set<VaadinSessionInfo> sessions = new HashSet<VaadinSessionInfo>();

	private Dictionary properties;

	public VaadinOSGiServlet(ComponentFactory factory, Dictionary properties) {
		this.factory = factory;
		this.properties = properties;
	}

	@Override
	protected OSGiServletService createServletService(
			DeploymentConfiguration deploymentConfiguration) {
		return new OSGiServletService(this, deploymentConfiguration, this);
	}

	@Override
	public VaadinSession createVaadinSession(VaadinRequest request,
			HttpServletRequest httpServletRequest) {
		final VaadinSessionInfo info = new VaadinSessionInfo(
				factory.newInstance(properties),
				httpServletRequest.getSession());

		info.session.setAttribute(VaadinOSGiServlet.class.getName(),
				new HttpSessionListener() {
					@Override
					public void sessionDestroyed(HttpSessionEvent arg0) {
						info.dispose();
					}

					@Override
					public void sessionCreated(HttpSessionEvent arg0) {

					}
				});
		System.out.println("Ready: " + info); //$NON-NLS-1$
		return (VaadinSession) info.instance.getInstance();
	}

	@Override
	public void destroy() {
		super.destroy();

		synchronized (this) {
			HashSet<VaadinSessionInfo> sessions = new HashSet<VaadinSessionInfo>();
			sessions.addAll(this.sessions);
			this.sessions.clear();
			for (VaadinSessionInfo info : sessions) {
				info.dispose();
			}
		}
	}

	/**
	 * Track the component instance and session. If this is disposed the entire
	 * associated http session is also disposed.
	 */
	class VaadinSessionInfo {

		final ComponentInstance instance;
		final HttpSession session;

		public VaadinSessionInfo(ComponentInstance instance, HttpSession session) {
			this.instance = instance;
			this.session = session;
			sessions.add(this);
		}

		public void dispose() {
			VaadinSessionInfo app = (VaadinSessionInfo) instance.getInstance();
			if (app != null) {
				app.dispose();
			}

			instance.dispose();

			session.removeAttribute(VaadinOSGiServlet.class.getName());
			sessions.remove(this);
		}
	}

}
