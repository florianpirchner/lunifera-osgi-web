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
package org.lunifera.web.ecp.uimodel.presentation.vaadin.example;

import org.eclipse.emf.ecp.ui.model.core.uimodel.YUiView;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiAlignment;
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
public class UiModelDemoUI extends UI {

	private static final Logger logger = LoggerFactory.getLogger(UiModelDemoUI.class);

	private static final long serialVersionUID = 1L;
	
	private SimpleModelFactory factory = new SimpleModelFactory();

	@Override
	public void init(VaadinRequest request) {

		CssLayout renderingContent = new CssLayout();
		renderingContent.setWidth("600px");
		renderingContent.setHeight("450px");
		setContent(renderingContent);

		// build the view model
		// ...> yView
		// ......> yLayout
		// .........> yText1
		// .........> yText2
		// .........> yText3
		// .........> yText4
		// .........> yText5
		// .........> yText6
		// .........> yText7
		YUiView yView = factory.createView();
		
		// create the layout
		YUiGridLayout yLayout = factory.createGridLayout();
		yView.setContent(yLayout);
		yLayout.setColumns(3);
		yLayout.setPackHorizontal(false);
		yLayout.setPackVertical(false);
		yLayout.setSpacing(true);
		yLayout.setMargin(true);
		
		// add some text fields
		//
		YUiTextField yText1 = newText();
		yLayout.getElements().add(yText1);
		YUiTextField yText2 = newText();
		yLayout.getElements().add(yText2);
		YUiTextField yText3 = newText();
		yLayout.getElements().add(yText3);
		YUiTextField yText4 = newText();
		yLayout.getElements().add(yText4);
		YUiTextField yText5 = newText();
		yLayout.getElements().add(yText5);
		YUiTextField yText6 = newText();
		yLayout.getElements().add(yText6);
		YUiTextField yText7 = newText();
		yLayout.getElements().add(yText7);

		// create the styling information
		//
		// text 1 -> alignment
		YUiGridLayoutCellStyle yStyle1 = createCellStyle(yLayout, yText1);
		yStyle1.setAlignment(YUiAlignment.TOP_LEFT);
		// text 2 -> alignment
		YUiGridLayoutCellStyle yStyle2 = createCellStyle(yLayout, yText2);
		yStyle2.setAlignment(YUiAlignment.MIDDLE_CENTER);
		// text 3 -> alignment
		YUiGridLayoutCellStyle yStyle3 = createCellStyle(yLayout, yText3);
		yStyle3.setAlignment(YUiAlignment.BOTTOM_FILL);
		// text 4 -> Span from (0,1) to (0,2)
		YUiGridLayoutCellStyle yStyle4 = createCellStyle(yLayout, yText4);
		yStyle4.setAlignment(YUiAlignment.FILL_FILL);
		factory.createSpanInfo(yStyle4, 0, 1, 0, 2);
		// text 8 -> Span from (1,1) to (2,1)
		YUiGridLayoutCellStyle yStyle8 = createCellStyle(yLayout, yText7);
		yStyle8.setAlignment(YUiAlignment.FILL_FILL);
		factory.createSpanInfo(yStyle8, 1, 2, 2, 2);

		try {
			VaadinRenderer renderer = new VaadinRenderer();
			renderer.render(renderingContent, yView, null);
		} catch (ContextException e) {
			logger.error("{}", e);
		}
	}

	protected YUiGridLayoutCellStyle createCellStyle(YUiGridLayout yGridLayout, YUiTextField yText1) {
		return factory.createGridLayoutCellStyle(yText1, yGridLayout);
	}

	protected YUiTextField newText() {
		return factory.createTextField();
	}
}
