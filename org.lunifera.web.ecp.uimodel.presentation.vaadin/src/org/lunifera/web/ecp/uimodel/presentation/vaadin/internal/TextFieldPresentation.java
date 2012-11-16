/**
 * Copyright (c) 2012 Florian Pirchner (Vienna, Austria) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Florian Pirchner - initial API and implementation
 */
package org.lunifera.web.ecp.uimodel.presentation.vaadin.internal;

import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiTextField;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.IUiElementEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.extension.IUiTextFieldEditpart;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;

/**
 * This presenter is responsible to render a text field on the given layout.
 */
public class TextFieldPresentation extends AbstractFieldPresenter {

	private final YUiTextField yTextField;
	private CssLayout componentBase;
	private TextField text;

	/**
	 * Constructor.
	 * 
	 * @param editpart
	 *            The editpart of that presenter
	 */
	public TextFieldPresentation(IUiElementEditpart editpart) {
		super((IUiTextFieldEditpart) editpart);
		this.yTextField = (YUiTextField) editpart.getModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component createWidget(Object parent) {
		if (componentBase == null) {
			componentBase = new CssLayout();
			componentBase.addStyleName(CSS_CLASS__CONTROL_BASE);
			if (Util.isCssIdValid(yTextField)) {
				componentBase.setId(Util.getCssID(yTextField));
			} else {
				componentBase.setId(getEditpart().getId());
			}

			text = new TextField();
			text.addStyleName(CSS_CLASS__CONTROL);
			text.setSizeFull();
			text.setNullRepresentation("");
			text.setNullSettingAllowed(true);
			componentBase.addComponent(text);

			if (Util.isCssClassValid(yTextField)) {
				text.addStyleName(Util.getCssClass(yTextField));
			}

			if (Util.isLabelValid(yTextField.getDatadescription())) {
				text.setCaption(Util.getLabel(yTextField.getDatadescription()));
			}
		}
		return componentBase;
	}

	@Override
	public Component getWidget() {
		return componentBase;
	}

	@Override
	public boolean isRendered() {
		return componentBase != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unrender() {
		if (componentBase != null) {
			ComponentContainer parent = ((ComponentContainer) componentBase
					.getParent());
			if (parent != null) {
				parent.removeComponent(componentBase);
			}
			componentBase = null;
			text = null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void internalDispose() {
		// unrender the ui component
		unrender();
	}
}
