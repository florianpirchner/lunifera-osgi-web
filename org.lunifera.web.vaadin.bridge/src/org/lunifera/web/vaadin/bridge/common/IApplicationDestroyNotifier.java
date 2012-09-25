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

import com.vaadin.server.VaadinSession;

/**
 * Is used to observe the destroy of sessions.
 */
public interface IApplicationDestroyNotifier {

	/**
	 * Adds a listener that becomes notified if the application becomes
	 * destroyed.
	 * 
	 * @param listener
	 */
	void addListener(Listener listener);

	public interface Listener {

		/**
		 * The given session was destroyed.
		 * 
		 * @param session
		 */
		void destroyed(VaadinSession session);

	}
}
