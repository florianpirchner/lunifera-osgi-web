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

import org.eclipse.emf.ecp.ui.model.core.datatypes.YCheckBoxDDesc;
import org.eclipse.emf.ecp.ui.model.core.datatypes.YLabelDDesc;
import org.eclipse.emf.ecp.ui.model.core.datatypes.YTextAreaDDesc;
import org.eclipse.emf.ecp.ui.model.core.datatypes.YTextDDesc;
import org.eclipse.emf.ecp.ui.model.core.uimodel.YUiEmbeddable;
import org.eclipse.emf.ecp.ui.model.core.uimodel.YUiView;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiAlignment;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiCheckBox;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayout;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayoutCellStyle;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiLabel;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiTextArea;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiTextField;
import org.eclipse.emf.ecp.ui.model.core.uimodel.util.SimpleModelFactory;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.context.ContextException;
import org.lunifera.web.ecp.uimodel.presentation.vaadin.VaadinRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Specify the class name after the factory name.
 */
@Theme(Reindeer.THEME_NAME)
public class UiModelDemoUI extends UI {

	private static final Logger logger = LoggerFactory
			.getLogger(UiModelDemoUI.class);

	private static final long serialVersionUID = 1L;

	private SimpleModelFactory factory = new SimpleModelFactory();

	private TabSheet t;

	@Override
	public void init(VaadinRequest request) {
		VerticalLayout content = (VerticalLayout) getContent();
		content.setSizeFull();
		content.setStyleName(Reindeer.LAYOUT_BLUE);

		t = new TabSheet();
		t.setSizeFull();
		t.setStyleName(Reindeer.LAYOUT_BLUE);

		t.addTab(new GridlayoutTextExample().render(), "Gridlayout Text");
		t.addTab(new GridlayoutLabelExample().render(), "Gridlayout Label");
		t.addTab(new GridlayoutTextAreaExample().render(),
				"Gridlayout TextArea");
		t.addTab(new GridlayoutCheckBoxExample().render(),
				"Gridlayout Checkbox");

		content.addComponent(t);
		content.setExpandRatio(t, 1.0f);
	}

	protected YUiGridLayoutCellStyle createCellStyle(YUiGridLayout yGridLayout,
			YUiEmbeddable yComponent) {
		return factory.createGridLayoutCellStyle(yComponent, yGridLayout);
	}

	/**
	 * Creates a new text field.
	 * 
	 * @param label
	 *            the label to show
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

	/**
	 * Creates a new check box.
	 * 
	 * @param label
	 *            the label to show
	 * @return checkBox
	 */
	protected YUiCheckBox newCheckBox(String label) {
		YUiCheckBox field = factory.createCheckBox();
		if (label != null) {
			YCheckBoxDDesc dtd = factory.createCheckBoxDatadescription();
			field.setDatadescription(dtd);
			dtd.setLabel(label);
		}
		return field;
	}

	/**
	 * Creates a new text area.
	 * 
	 * @param label
	 *            the label to show
	 * @return textArea
	 */
	protected YUiTextArea newTextArea(String label) {
		YUiTextArea field = factory.createTextArea();
		if (label != null) {
			YTextAreaDDesc dtd = factory.createTextAreaDatadescription();
			field.setDatadescription(dtd);
			dtd.setLabel(label);
		}
		return field;
	}

	/**
	 * Creates a new label.
	 * 
	 * @param label
	 *            the label to show
	 * @return label
	 */
	protected YUiLabel newLabel(String label) {
		YUiLabel field = factory.createLabel();
		if (label != null) {
			YLabelDDesc dtd = factory.createLabelDatadescription();
			field.setDatadescription(dtd);
			dtd.setLabel(label);
		}
		return field;
	}

	private class GridlayoutTextExample {

		private CssLayout render() {

			CssLayout renderingContent = new CssLayout();
			renderingContent.setSizeFull();
			renderingContent.setStyleName(Reindeer.LAYOUT_BLUE);

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
			// .........> yText8
			// .........> yText9
			// .........> yText10
			YUiView yView = factory.createView();
			yView.setCssClass("gridLayoutExample");

			// create the layout
			YUiGridLayout yLayout = factory.createGridLayout();
			yLayout.setCssClass("gridlayout");
			yView.setContent(yLayout);
			yLayout.setColumns(3);
			// yLayout.setPackContentHorizontal(false);
			// yLayout.setPackContentVertical(false);
			yLayout.setSpacing(true);
			yLayout.setMargin(true);

			// add some text fields
			//
			YUiTextField yText1 = newText("Text1");
			yLayout.getElements().add(yText1);
			YUiTextField yText2 = newText("Text2");
			yLayout.getElements().add(yText2);
			YUiTextField yText3 = newText("Text3");
			yLayout.getElements().add(yText3);
			YUiTextField yText4 = newText("Text4");
			yLayout.getElements().add(yText4);
			YUiTextField yText5 = newText("Text5");
			yLayout.getElements().add(yText5);
			YUiTextField yText6 = newText("Text6");
			yLayout.getElements().add(yText6);
			YUiTextField yText7 = newText("Text7");
			yLayout.getElements().add(yText7);
			YUiTextField yText8 = newText("Text8");
			yLayout.getElements().add(yText8);
			YUiTextField yText9 = newText("Text9");
			yLayout.getElements().add(yText9);
			YUiTextField yText10 = newText("Text10");
			yLayout.getElements().add(yText10);

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
			yStyle3.setAlignment(YUiAlignment.BOTTOM_RIGHT);
			// text 4 -> Span from (0,1) to (0,2)
			YUiGridLayoutCellStyle yStyle4 = createCellStyle(yLayout, yText4);
			yStyle4.setAlignment(YUiAlignment.FILL_LEFT);
			factory.createSpanInfo(yStyle4, 0, 1, 0, 2);
			// text 5 -> alignment
			YUiGridLayoutCellStyle yStyle5 = createCellStyle(yLayout, yText5);
			yStyle5.setAlignment(YUiAlignment.MIDDLE_FILL);
			// text 6 -> alignment
			YUiGridLayoutCellStyle yStyle6 = createCellStyle(yLayout, yText6);
			yStyle6.setAlignment(YUiAlignment.MIDDLE_FILL);
			// text 7 -> Span from (1,1) to (2,1)
			YUiGridLayoutCellStyle yStyle7 = createCellStyle(yLayout, yText7);
			yStyle7.setAlignment(YUiAlignment.FILL_FILL);
			factory.createSpanInfo(yStyle7, 1, 2, 2, 2);
			// text 8 -> alignment
			YUiGridLayoutCellStyle yStyle8 = createCellStyle(yLayout, yText8);
			yStyle8.setAlignment(YUiAlignment.BOTTOM_LEFT);

			try {
				VaadinRenderer renderer = new VaadinRenderer();
				renderer.render(renderingContent, yView, null);
			} catch (ContextException e) {
				logger.error("{}", e);
			}

			return renderingContent;
		}
	}

	private class GridlayoutLabelExample {

		private CssLayout render() {

			CssLayout renderingContent = new CssLayout();
			renderingContent.setSizeFull();
			renderingContent.setStyleName(Reindeer.LAYOUT_BLUE);

			// build the view model
			// ...> yView
			// ......> yLayout
			// .........> yLabel1
			// .........> yLabel2
			// .........> yLabel3
			// .........> yLabel4
			// .........> yLabel5
			// .........> yLabel6
			// .........> yLabel7
			// .........> yLabel8
			// .........> yLabel9
			// .........> yLabel10
			YUiView yView = factory.createView();
			yView.setCssClass("gridLayoutExample");

			// create the layout
			YUiGridLayout yLayout = factory.createGridLayout();
			yLayout.setCssClass("gridlayout");
			yView.setContent(yLayout);
			yLayout.setColumns(3);
			// yLayout.setPackContentHorizontal(false);
			// yLayout.setPackContentVertical(false);
			yLayout.setSpacing(true);
			yLayout.setMargin(true);

			// add some text fields
			//
			// add some text fields
			//
			YUiLabel yLabel1 = newLabel("Text1");
			yLayout.getElements().add(yLabel1);
			YUiLabel yLabel2 = newLabel("Text2");
			yLayout.getElements().add(yLabel2);
			YUiLabel yLabel3 = newLabel("Text3");
			yLayout.getElements().add(yLabel3);
			YUiLabel yLabel4 = newLabel("Text4");
			yLayout.getElements().add(yLabel4);
			YUiLabel yLabel5 = newLabel("Text5");
			yLayout.getElements().add(yLabel5);
			YUiLabel yLabel6 = newLabel("Text6");
			yLayout.getElements().add(yLabel6);
			YUiLabel yLabel7 = newLabel("Text7");
			yLayout.getElements().add(yLabel7);
			YUiLabel yLabel8 = newLabel("Text8");
			yLayout.getElements().add(yLabel8);
			YUiLabel yLabel9 = newLabel("Text9");
			yLayout.getElements().add(yLabel9);
			YUiLabel yLabel10 = newLabel("Text10");
			yLayout.getElements().add(yLabel10);

			// create the styling information
			//
			// text 1 -> alignment
			YUiGridLayoutCellStyle yStyle1 = createCellStyle(yLayout, yLabel1);
			yStyle1.setAlignment(YUiAlignment.TOP_LEFT);
			// text 2 -> alignment
			YUiGridLayoutCellStyle yStyle2 = createCellStyle(yLayout, yLabel2);
			yStyle2.setAlignment(YUiAlignment.MIDDLE_CENTER);
			// text 3 -> alignment
			YUiGridLayoutCellStyle yStyle3 = createCellStyle(yLayout, yLabel3);
			yStyle3.setAlignment(YUiAlignment.BOTTOM_RIGHT);
			// text 4 -> Span from (0,1) to (0,2)
			YUiGridLayoutCellStyle yStyle4 = createCellStyle(yLayout, yLabel4);
			yStyle4.setAlignment(YUiAlignment.FILL_LEFT);
			factory.createSpanInfo(yStyle4, 0, 1, 0, 2);
			// text 5 -> alignment
			YUiGridLayoutCellStyle yStyle5 = createCellStyle(yLayout, yLabel5);
			yStyle5.setAlignment(YUiAlignment.MIDDLE_FILL);
			// text 6 -> alignment
			YUiGridLayoutCellStyle yStyle6 = createCellStyle(yLayout, yLabel6);
			yStyle6.setAlignment(YUiAlignment.MIDDLE_FILL);
			// text 7 -> Span from (1,1) to (2,1)
			YUiGridLayoutCellStyle yStyle7 = createCellStyle(yLayout, yLabel7);
			yStyle7.setAlignment(YUiAlignment.FILL_FILL);
			factory.createSpanInfo(yStyle7, 1, 2, 2, 2);
			// text 8 -> alignment
			YUiGridLayoutCellStyle yStyle8 = createCellStyle(yLayout, yLabel8);
			yStyle8.setAlignment(YUiAlignment.BOTTOM_LEFT);

			// create the rendering options
			//
			try {
				VaadinRenderer renderer = new VaadinRenderer();
				renderer.render(renderingContent, yView, null);
			} catch (ContextException e) {
				logger.error("{}", e);
			}

			return renderingContent;
		}
	}

	private class GridlayoutTextAreaExample {

		private CssLayout render() {

			CssLayout renderingContent = new CssLayout();
			renderingContent.setSizeFull();
			renderingContent.setStyleName(Reindeer.LAYOUT_BLUE);

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
			// .........> yText8
			// .........> yText9
			// .........> yText10
			YUiView yView = factory.createView();
			yView.setCssClass("gridLayoutExample");

			// create the layout
			YUiGridLayout yLayout = factory.createGridLayout();
			yLayout.setCssClass("gridlayout");
			yView.setContent(yLayout);
			yLayout.setColumns(3);
			// yLayout.setPackContentHorizontal(false);
			// yLayout.setPackContentVertical(false);
			yLayout.setSpacing(true);
			yLayout.setMargin(true);

			// add some text fields
			//
			YUiTextArea yTextArea1 = newTextArea("Text1");
			yLayout.getElements().add(yTextArea1);
			YUiTextArea yTextArea2 = newTextArea("Text2");
			yLayout.getElements().add(yTextArea2);
			YUiTextArea yTextArea3 = newTextArea("Text3");
			yLayout.getElements().add(yTextArea3);
			YUiTextArea yTextArea4 = newTextArea("Text4");
			yLayout.getElements().add(yTextArea4);
			YUiTextArea yTextArea5 = newTextArea("Text5");
			yLayout.getElements().add(yTextArea5);
			YUiTextArea yTextArea6 = newTextArea("Text6");
			yLayout.getElements().add(yTextArea6);
			YUiTextArea yTextArea7 = newTextArea("Text7");
			yLayout.getElements().add(yTextArea7);
			YUiTextArea yTextArea8 = newTextArea("Text8");
			yLayout.getElements().add(yTextArea8);
			YUiTextArea yTextArea9 = newTextArea("Text9");
			yLayout.getElements().add(yTextArea9);
			YUiTextArea yTextArea10 = newTextArea("Text10");
			yLayout.getElements().add(yTextArea10);

			// create the styling information
			//
			// text 1 -> alignment
			YUiGridLayoutCellStyle yStyle1 = createCellStyle(yLayout,
					yTextArea1);
			yStyle1.setAlignment(YUiAlignment.TOP_LEFT);
			// text 2 -> alignment
			YUiGridLayoutCellStyle yStyle2 = createCellStyle(yLayout,
					yTextArea2);
			yStyle2.setAlignment(YUiAlignment.MIDDLE_CENTER);
			// text 3 -> alignment
			YUiGridLayoutCellStyle yStyle3 = createCellStyle(yLayout,
					yTextArea3);
			yStyle3.setAlignment(YUiAlignment.BOTTOM_RIGHT);
			// text 4 -> Span from (0,1) to (0,2)
			YUiGridLayoutCellStyle yStyle4 = createCellStyle(yLayout,
					yTextArea4);
			yStyle4.setAlignment(YUiAlignment.FILL_LEFT);
			factory.createSpanInfo(yStyle4, 0, 1, 0, 2);
			// text 5 -> alignment
			YUiGridLayoutCellStyle yStyle5 = createCellStyle(yLayout,
					yTextArea5);
			yStyle5.setAlignment(YUiAlignment.MIDDLE_FILL);
			// text 6 -> alignment
			YUiGridLayoutCellStyle yStyle6 = createCellStyle(yLayout,
					yTextArea6);
			yStyle6.setAlignment(YUiAlignment.MIDDLE_FILL);
			// text 7 -> Span from (1,1) to (2,1)
			YUiGridLayoutCellStyle yStyle7 = createCellStyle(yLayout,
					yTextArea7);
			yStyle7.setAlignment(YUiAlignment.FILL_FILL);
			factory.createSpanInfo(yStyle7, 1, 2, 2, 2);
			// text 8 -> alignment
			YUiGridLayoutCellStyle yStyle8 = createCellStyle(yLayout,
					yTextArea8);
			yStyle8.setAlignment(YUiAlignment.BOTTOM_LEFT);

			try {
				VaadinRenderer renderer = new VaadinRenderer();
				renderer.render(renderingContent, yView, null);
			} catch (ContextException e) {
				logger.error("{}", e);
			}

			return renderingContent;
		}
	}

	private class GridlayoutCheckBoxExample {

		private CssLayout render() {

			CssLayout renderingContent = new CssLayout();
			renderingContent.setSizeFull();
			renderingContent.setStyleName(Reindeer.LAYOUT_BLUE);

			// build the view model
			// ...> yView
			// ......> yLayout
			// .........> yCheckBox1
			// .........> yCheckBox2
			// .........> yCheckBox3
			// .........> yCheckBox4
			// .........> yCheckBox5
			// .........> yCheckBox6
			// .........> yCheckBox7
			// .........> yCheckBox8
			// .........> yCheckBox9
			// .........> yCheckBox10
			YUiView yView = factory.createView();
			yView.setCssClass("gridLayoutExample");

			// create the layout
			YUiGridLayout yLayout = factory.createGridLayout();
			yLayout.setCssClass("gridlayout");
			yView.setContent(yLayout);
			yLayout.setColumns(3);
			// yLayout.setPackContentHorizontal(false);
			// yLayout.setPackContentVertical(false);
			yLayout.setSpacing(true);
			yLayout.setMargin(true);

			// add some text fields
			//
			YUiCheckBox yCheckBox1 = newCheckBox("CheckBox1");
			yLayout.getElements().add(yCheckBox1);
			YUiCheckBox yCheckBox2 = newCheckBox("CheckBox2");
			yLayout.getElements().add(yCheckBox2);
			YUiCheckBox yCheckBox3 = newCheckBox("CheckBox3");
			yLayout.getElements().add(yCheckBox3);
			YUiCheckBox yCheckBox4 = newCheckBox("CheckBox4");
			yLayout.getElements().add(yCheckBox4);
			YUiCheckBox yCheckBox5 = newCheckBox("CheckBox5");
			yLayout.getElements().add(yCheckBox5);
			YUiCheckBox yCheckBox6 = newCheckBox("CheckBox6");
			yLayout.getElements().add(yCheckBox6);
			YUiCheckBox yCheckBox7 = newCheckBox("CheckBox7");
			yLayout.getElements().add(yCheckBox7);
			YUiCheckBox yCheckBox8 = newCheckBox("CheckBox8");
			yLayout.getElements().add(yCheckBox8);
			YUiCheckBox yCheckBox9 = newCheckBox("CheckBox9");
			yLayout.getElements().add(yCheckBox9);
			YUiCheckBox yCheckBox10 = newCheckBox("CheckBox10");
			yLayout.getElements().add(yCheckBox10);

			// create the styling information
			//
			// text 1 -> alignment
			YUiGridLayoutCellStyle yStyle1 = createCellStyle(yLayout,
					yCheckBox1);
			yStyle1.setAlignment(YUiAlignment.TOP_LEFT);
			// text 2 -> alignment
			YUiGridLayoutCellStyle yStyle2 = createCellStyle(yLayout,
					yCheckBox2);
			yStyle2.setAlignment(YUiAlignment.MIDDLE_CENTER);
			// text 3 -> alignment
			YUiGridLayoutCellStyle yStyle3 = createCellStyle(yLayout,
					yCheckBox3);
			yStyle3.setAlignment(YUiAlignment.BOTTOM_RIGHT);
			// text 4 -> Span from (0,1) to (0,2)
			YUiGridLayoutCellStyle yStyle4 = createCellStyle(yLayout,
					yCheckBox4);
			yStyle4.setAlignment(YUiAlignment.FILL_LEFT);
			factory.createSpanInfo(yStyle4, 0, 1, 0, 2);
			// text 5 -> alignment
			YUiGridLayoutCellStyle yStyle5 = createCellStyle(yLayout,
					yCheckBox5);
			yStyle5.setAlignment(YUiAlignment.MIDDLE_FILL);
			// text 6 -> alignment
			YUiGridLayoutCellStyle yStyle6 = createCellStyle(yLayout,
					yCheckBox6);
			yStyle6.setAlignment(YUiAlignment.MIDDLE_FILL);
			// text 7 -> Span from (1,1) to (2,1)
			YUiGridLayoutCellStyle yStyle7 = createCellStyle(yLayout,
					yCheckBox7);
			yStyle7.setAlignment(YUiAlignment.FILL_FILL);
			factory.createSpanInfo(yStyle7, 1, 2, 2, 2);
			// text 8 -> alignment
			YUiGridLayoutCellStyle yStyle8 = createCellStyle(yLayout,
					yCheckBox8);
			yStyle8.setAlignment(YUiAlignment.BOTTOM_LEFT);

			try {
				VaadinRenderer renderer = new VaadinRenderer();
				renderer.render(renderingContent, yView, null);
			} catch (ContextException e) {
				logger.error("{}", e);
			}

			return renderingContent;
		}
	}
}
