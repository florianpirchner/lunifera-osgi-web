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

import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiTextArea;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.IUiElementEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.extension.IUiTextAreaEditpart;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextArea;

/**
 * This presenter is responsible to render a textArea field on the given layout.
 */
public class TextAreaPresentation extends AbstractFieldPresenter {

	private final YUiTextArea yTextArea;
	private CssLayout componentBase;
	private TextArea textArea;

	/**
	 * Constructor.
	 * 
	 * @param editpart
	 *            The editpart of that presenter
	 */
	public TextAreaPresentation(IUiElementEditpart editpart) {
		super((IUiTextAreaEditpart) editpart);
		this.yTextArea = (YUiTextArea) editpart.getModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component createWidget(Object parent) {
		if (componentBase == null) {
			componentBase = new CssLayout();
			componentBase.addStyleName(CSS_CLASS__CONTROL_BASE);
			if (Util.isCssIdValid(yTextArea)) {
				componentBase.setId(Util.getCssID(yTextArea));
			} else {
				componentBase.setId(getEditpart().getId());
			}

			textArea = new TextArea();
			textArea.addStyleName(CSS_CLASS__CONTROL);
			textArea.setSizeFull();
			textArea.setNullRepresentation("");
			textArea.setNullSettingAllowed(true);
			componentBase.addComponent(textArea);

			if (Util.isCssClassValid(yTextArea)) {
				textArea.addStyleName(Util.getCssClass(yTextArea));
			}

			if (Util.isLabelValid(yTextArea.getDatadescription())) {
				textArea.setCaption(Util.getLabel(yTextArea
						.getDatadescription()));
			}

			// applies the input properties
			Util.applyInputProperties(textArea, yTextArea);
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
			textArea = null;
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