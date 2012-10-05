/*******************************************************************************
 * Copyright (c) 2012 by committers of lunifera.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Florian Pirchner - initial API and implementation
 * 
 *******************************************************************************/
package org.lunifera.web.example.vaadin.singleton;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecp.ui.model.core.datatypes.YTextDDesc;
import org.eclipse.emf.ecp.ui.model.core.uimodel.YUiView;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayout;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayoutCellStyle;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiTextField;
import org.eclipse.emf.ecp.ui.model.core.uimodel.util.SimpleModelFactory;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.context.ContextException;
import org.lunifera.web.ecp.uimodel.presentation.vaadin.VaadinRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Reindeer;

/**
 * Specify the class name after the factory name.
 */
@Theme(Reindeer.THEME_NAME)
public class SingletonUI extends UI implements UI.CleanupListener, IUiAccess {

	private static final Logger logger = LoggerFactory.getLogger(SingletonUI.class);

	private static final long serialVersionUID = 1L;

	private SimpleModelFactory factory = new SimpleModelFactory();

	private CssLayout mainLayout;

	private static Set<IUiAccess> uiaccesses = new HashSet<IUiAccess>();

	/**
	 * @return the iNSTANCES
	 */
	public static Set<IUiAccess> getUiAccesses() {
		return uiaccesses;
	}

	@Override
	public void init(VaadinRequest request) {
		mainLayout = new CssLayout();
		mainLayout.setSizeFull();
		setContent(mainLayout);

		// put the instances into the cache
		uiaccesses.add(this);
	}

	@Override
	public void showUI(YUiView yView) {
		// remove the content
		mainLayout.removeAllComponents();

		// render the view again
		try {
			VaadinRenderer renderer = new VaadinRenderer();
			renderer.render(mainLayout, yView, null);
		} catch (ContextException e) {
			logger.error("{}", e);
		}
	}

	protected YUiGridLayoutCellStyle createCellStyle(YUiGridLayout yGridLayout, YUiTextField yText1) {
		return factory.createGridLayoutCellStyle(yText1, yGridLayout);
	}

	/**
	 * Creates a new text field.
	 * 
	 * @param label the label to show
	 * @return textField
	 */
	protected YUiTextField newText(String label) {
		YUiTextField field = factory.createTextField();
		if (label != null) {
			YTextDDesc dtd = factory.createTextDatadescription();
			field.setDatadescription(dtd);
			dtd.setLabel(label);
		}
		return field;
	}

	@Override
	public void cleanup(CleanupEvent event) {
		uiaccesses.remove(this);
	}

}
