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

import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiTable;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.IUiElementEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.extension.IUiTableEditpart;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;

/**
 * This presenter is responsible to render a table field on the given layout.
 */
public class TablePresentation extends AbstractFieldPresenter {

	private final YUiTable yTable;
	private CssLayout componentBase;
	private Table table;

	/**
	 * Constructor.
	 * 
	 * @param editpart
	 *            The editpart of that presenter
	 */
	public TablePresentation(IUiElementEditpart editpart) {
		super((IUiTableEditpart) editpart);
		this.yTable = (YUiTable) editpart.getModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component createWidget(Object parent) {
		if (componentBase == null) {
			componentBase = new CssLayout();
			componentBase.addStyleName(CSS_CLASS__CONTROL_BASE);
			if (Util.isCssIdValid(yTable)) {
				componentBase.setId(Util.getCssID(yTable));
			} else {
				componentBase.setId(getEditpart().getId());
			}

			table = new Table();
			table.addStyleName(CSS_CLASS__CONTROL);
			table.setSizeFull();
			componentBase.addComponent(table);

			if (Util.isCssClassValid(yTable)) {
				table.addStyleName(Util.getCssClass(yTable));
			}

			if (Util.isLabelValid(yTable.getDatadescription())) {
				table.setCaption(Util.getLabel(yTable.getDatadescription()));
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
			table = null;
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
