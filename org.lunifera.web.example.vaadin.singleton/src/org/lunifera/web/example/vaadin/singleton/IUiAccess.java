package org.lunifera.web.example.vaadin.singleton;

import org.eclipse.emf.ecp.ui.model.core.uimodel.YUiView;

public interface IUiAccess {

	/**
	 * Shows the view at the current uiInstance.
	 * 
	 * @param yView
	 */
	void showUI(YUiView yView);

}
