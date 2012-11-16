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

import org.eclipse.emf.ecp.ui.model.core.datatypes.YNumericDatatype;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiNumericField;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.IUiElementEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.extension.IUiNumericFieldEditpart;
import org.lunifera.web.vaadin.components.fields.NumberField;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

/**
 * This presenter is responsible to render a text field on the given layout.
 */
public class NumericFieldPresentation extends AbstractFieldPresenter {

	private final YUiNumericField yNumericField;
	private CssLayout componentBase;
	private NumberField numberField;

	/**
	 * Constructor.
	 * 
	 * @param editpart
	 *            The editpart of that presenter
	 */
	@SuppressWarnings("restriction")
	public NumericFieldPresentation(IUiElementEditpart editpart) {
		super((IUiNumericFieldEditpart) editpart);
		this.yNumericField = (YUiNumericField) editpart.getModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component createWidget(Object parent) {
		if (componentBase == null) {
			componentBase = new CssLayout();
			componentBase.addStyleName(CSS_CLASS__CONTROL_BASE);
			if (Util.isCssIdValid(yNumericField)) {
				componentBase.setId(Util.getCssID(yNumericField));
			} else {
				componentBase.setId(getEditpart().getId());
			}

			numberField = new NumberField();
			numberField.addStyleName(CSS_CLASS__CONTROL);
			numberField.setSizeFull();
			numberField.setNullRepresentation("");
			numberField.setNullSettingAllowed(true);
			componentBase.addComponent(numberField);

			if (Util.isCssClassValid(yNumericField)) {
				numberField.addStyleName(Util.getCssClass(yNumericField));
			}

			if (Util.isLabelValid(yNumericField.getDatadescription())) {
				numberField.setCaption(Util.getLabel(yNumericField
						.getDatadescription()));
			}

			YNumericDatatype datatype = yNumericField.getDatatype();
			if (datatype != null) {
				numberField.setUseGrouping(datatype.isGrouping());
				numberField.setUseGrouping(datatype.isMarkNegative());

				if (datatype.getFormatPattern() != null
						&& !datatype.getFormatPattern().equals("")) {
					numberField.setNumberFormatPattern(datatype
							.getFormatPattern());
				}
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
			numberField = null;
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
