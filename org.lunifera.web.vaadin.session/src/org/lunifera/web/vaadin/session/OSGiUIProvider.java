package org.lunifera.web.vaadin.session;

import org.lunifera.web.vaadin.session.common.IUiInfoProvider;

import com.vaadin.server.AbstractUIProvider;
import com.vaadin.server.WrappedRequest;
import com.vaadin.ui.UI;

public class OSGiUIProvider extends AbstractUIProvider {

	private IUiInfoProvider provider;

	public OSGiUIProvider(IUiInfoProvider provider) {
		this.provider = provider;
	}

	@Override
	public Class<? extends UI> getUIClass(WrappedRequest request) {
		return provider.getUIClass();
	}
}
