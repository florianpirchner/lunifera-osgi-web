package org.lunifera.web.vaadin.session;

import java.util.Map;

import org.lunifera.web.vaadin.session.common.IUiInfoProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinServletSession;

@SuppressWarnings("serial")
public class VaadinOSGiSession extends VaadinServletSession {

	private static final Logger logger = LoggerFactory
			.getLogger(VaadinOSGiSession.class);

	public VaadinOSGiSession() {

	}

	/**
	 * Called by OSGi DS.
	 * 
	 * @param provider
	 * @param properties
	 */
	protected void addUiInfoProviderFactory(IUiInfoProvider provider,
			Map<String, Object> properties) {
		logger.info("OSGI service: addUiInfoProviderFactory"); //$NON-NLS-1$
		addUIProvider(new OSGiUIProvider(provider));
	}

	/**
	 * Called by OSGi DS.
	 * 
	 * @param provider
	 * @param properties
	 */
	protected void removeUiInfoProviderFactory(IUiInfoProvider provider,
			Map<String, Object> properties) {
		logger.info("OSGI service: removeUiInfoProviderFactory"); //$NON-NLS-1$
		for (UIProvider uiProvider : getUIProviders().toArray(
				new UIProvider[getUIProviders().size()])) {
			if (uiProvider.getClass() == provider.getClass()) {
				removeUIProvider(uiProvider);
				break;
			}
		}
	}
}
