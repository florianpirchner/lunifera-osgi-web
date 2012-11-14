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

import org.eclipse.emf.ecp.ui.uimodel.core.editparts.IUiElementEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.IUiViewEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.context.IViewContext;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.extension.IUiCheckboxEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.extension.IUiGridLayoutEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.extension.IUiLabelEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.extension.IUiTableEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.extension.IUiTextAreaEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.extension.IUiTextFieldEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.presentation.IPresentationFactory;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.presentation.IWidgetPresentation;
import org.lunifera.web.ecp.uimodel.presentation.vaadin.VaadinRenderer;

/**
 * The presenter factory.
 */
public class PresenterFactory implements IPresentationFactory {

	@Override
	public boolean isFor(IViewContext uiContext, IUiElementEditpart editpart) {
		String presentationURI = uiContext.getPresentationURI();
		return presentationURI != null
				&& presentationURI.equals(VaadinRenderer.UI_KIT_URI);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A extends IWidgetPresentation<?>> A createPresentation(
			IViewContext uiContext, IUiElementEditpart editpart) {
		A presentation = null;
		if (editpart instanceof IUiViewEditpart) {
			presentation = (A) new ViewPresentation((IUiViewEditpart) editpart);
		} else if (editpart instanceof IUiTextFieldEditpart) {
			presentation = (A) new TextFieldPresentation(editpart);
		} else if (editpart instanceof IUiGridLayoutEditpart) {
			presentation = (A) new GridLayoutPresentation(editpart);
		} else if (editpart instanceof IUiTableEditpart) {
			presentation = (A) new TablePresentation(editpart);
		} else if (editpart instanceof IUiLabelEditpart) {
			presentation = (A) new LabelPresentation(editpart);
		} else if (editpart instanceof IUiTextAreaEditpart) {
			presentation = (A) new TextAreaPresentation(editpart);
		} else if (editpart instanceof IUiCheckboxEditpart) {
			presentation = (A) new CheckBoxPresentation(editpart);
		}
		if (presentation == null) {
			throw new IllegalArgumentException(String.format(
					"No presenter available for editpart %s[%s]", editpart
							.getClass().getName(), editpart.getId()));
		}
		uiContext.registerPresentation(editpart.getId(), presentation);

		return presentation;
	}
}
