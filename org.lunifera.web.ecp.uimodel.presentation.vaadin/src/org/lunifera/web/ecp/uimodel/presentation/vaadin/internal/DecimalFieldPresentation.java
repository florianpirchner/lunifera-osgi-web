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

import org.eclipse.emf.ecp.ui.model.core.datatypes.YDecimalDatatype;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiDecimalField;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.IUiElementEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.extension.IUiNumericFieldEditpart;
import org.lunifera.web.vaadin.components.fields.DecimalField;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

/**
 * This presenter is responsible to render a text field on the given layout.
 */
public class DecimalFieldPresentation extends AbstractFieldPresenter {

	private final YUiDecimalField yDecimalField;
	private CssLayout componentBase;
	private DecimalField decimalField;

	/**
	 * Constructor.
	 * 
	 * @param editpart
	 *            The editpart of that presenter
	 */
	public DecimalFieldPresentation(IUiElementEditpart editpart) {
		super((IUiNumericFieldEditpart) editpart);
		this.yDecimalField = (YUiDecimalField) editpart.getModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component createWidget(Object parent) {
		if (componentBase == null) {
			componentBase = new CssLayout();
			componentBase.addStyleName(CSS_CLASS__CONTROL_BASE);
			if (Util.isCssIdValid(yDecimalField)) {
				componentBase.setId(Util.getCssID(yDecimalField));
			} else {
				componentBase.setId(getEditpart().getId());
			}

			decimalField = new DecimalField();
			decimalField.addStyleName(CSS_CLASS__CONTROL);
			decimalField.setSizeFull();
			decimalField.setNullRepresentation("");
			decimalField.setNullSettingAllowed(true);
			componentBase.addComponent(decimalField);

			if (Util.isCssClassValid(yDecimalField)) {
				decimalField.addStyleName(Util.getCssClass(yDecimalField));
			}

			if (Util.isLabelValid(yDecimalField.getDatadescription())) {
				decimalField.setCaption(Util.getLabel(yDecimalField
						.getDatadescription()));
			}

			// applies the input properties
			Util.applyInputProperties(decimalField, yDecimalField);

			YDecimalDatatype datatype = yDecimalField.getDatatype();
			if (datatype != null) {
				decimalField.setUseGrouping(datatype.isGrouping());
				decimalField.setMarkNegative(datatype.isMarkNegative());
				decimalField.setPrecision(datatype.getPrecision());
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
			decimalField = null;
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
