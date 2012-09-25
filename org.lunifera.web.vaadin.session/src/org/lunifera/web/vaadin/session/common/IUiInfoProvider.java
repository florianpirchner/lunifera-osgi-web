package org.lunifera.web.vaadin.session.common;

import com.vaadin.ui.UI;

public interface IUiInfoProvider {

	/**
	 * Returns the class of the vaadin UI.
	 * 
	 * @return
	 */
	Class<? extends UI> getUIClass();

}
