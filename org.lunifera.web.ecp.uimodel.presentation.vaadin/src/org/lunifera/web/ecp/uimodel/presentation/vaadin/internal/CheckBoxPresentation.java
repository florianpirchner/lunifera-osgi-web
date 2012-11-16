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

import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiCheckBox;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.IUiElementEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.extension.IUiCheckboxEditpart;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

/**
 * This presenter is responsible to render a text field on the given layout.
 */
public class CheckBoxPresentation extends AbstractFieldPresenter {

	private final YUiCheckBox yCheckBox;
	private CssLayout componentBase;
	private CheckBox checkbox;

	/**
	 * Constructor.
	 * 
	 * @param editpart
	 *            The editpart of that presenter
	 */
	public CheckBoxPresentation(IUiElementEditpart editpart) {
		super((IUiCheckboxEditpart) editpart);
		this.yCheckBox = (YUiCheckBox) editpart.getModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component createWidget(Object parent) {
		if (componentBase == null) {
			componentBase = new CssLayout();
			componentBase.addStyleName(CSS_CLASS__CONTROL_BASE);
			if (Util.isCssIdValid(yCheckBox)) {
				componentBase.setId(Util.getCssID(yCheckBox));
			} else {
				componentBase.setId(getEditpart().getId());
			}

			checkbox = new CheckBox();
			checkbox.addStyleName(CSS_CLASS__CONTROL);
			componentBase.addComponent(checkbox);

			if (Util.isCssClassValid(yCheckBox)) {
				checkbox.addStyleName(Util.getCssClass(yCheckBox));
			}

			if (Util.isLabelValid(yCheckBox.getDatadescription())) {
				checkbox.setCaption(Util.getLabel(yCheckBox
						.getDatadescription()));
			}

			// applies the input properties
			Util.applyInputProperties(checkbox, yCheckBox);
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
			checkbox = null;
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
