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
package org.lunifera.web.example.vaadin.components;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.lunifera.web.vaadin.components.fields.DecimalField;
import org.lunifera.web.vaadin.components.fields.NumberField;
import org.lunifera.web.vaadin.components.validator.NumberBoundsValidator;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Reindeer;

/**
 * Specify the class name after the factory name.
 */
@Theme(Reindeer.THEME_NAME)
public class DemoUI extends UI {

	private static final long serialVersionUID = 1L;

	private static final String CAPTION_HELP_1 = "<b>Settings</b><br>- Formater = 000,000.000000<br> - Locale = System Default";
	private static final String CAPTION_HELP_2 = "<b>Settings</b><br> - Formater = ##,##0.00<br> - Min Value = -3<br>- Max Value = 100<br> - Locale = System Default";
	private static final String CAPTION_HELP_3 = "<b>Settings</b><br> - Formater = ##,##0.00<br> - Value = 8<br> - Locale = en_US";
	private static final String CAPTION_HELP_4 = "<b>Settings</b><br> - Formater = ##,##0.00<br> - Locale = %s";
	private static final String CAPTION_HELP_5 = "<b>Settings</b><br> - Formater = ##,##0.00<br> - Locale = %s <br> - Group Char = :";
	private static final String CAPTION_HELP_6 = "Simply Textfield<br> without any Settings";
	private static final String CAPTION_HELP_8 = "<b>Settings</b><br> - Formater = ##,##0.00<br> - noGrouping<br> - Locale = en_US";

	@Override
	public void init(VaadinRequest request) {
		TabSheet sheet = new TabSheet();
		sheet.setSizeFull();
		addComponent(sheet);
		sheet.addTab(createDecimalTab(), "Decimals");
		// sheet.addTab(createNumericTab(), "Numeric");
	}

	@SuppressWarnings("serial")
	private AbsoluteLayout createDecimalTab() {
		setStyleName(Reindeer.LAYOUT_BLUE);
		// Initialize
		AbsoluteLayout main = new AbsoluteLayout();
		main.setStyleName(Reindeer.LAYOUT_BLUE);
		main.setWidth("800px");
		main.setHeight("900px");

		/*
		 * Decimal Field 1
		 */
		DecimalField field1 = new DecimalField("Decimal Field 1");
		field1.setImmediate(true);
		field1.setBuffered(false);
		field1.setPrecision(4);
		field1.setMarkNegative(true);
		field1.setUseGrouping(true);

		ObjectProperty<Double> prop1 = new ObjectProperty<Double>(new Double(
				1234567890.123456));
		field1.setPropertyDataSource(prop1);
		main.addComponent(field1, "top:20.0px;left:40.0px;");

		// Add Description (called Settings)
		Label desc1 = new Label(CAPTION_HELP_1);
		desc1.setContentMode(ContentMode.HTML);
		desc1.setWidth("300px");
		main.addComponent(desc1, "top:20.0px;left:200.0px;");

		// Add horizontal Line
		Label line1 = new Label("<hr>");
		line1.setContentMode(ContentMode.HTML);
		main.addComponent(line1, "top:80.0px;left:40.0px;");

		// /*
		// * Decimal Field 2
		// */
		//
		// // Add Decimal Field
		// DecimalField field2 = new DecimalField("Decimal Field 2");
		// field2.setImmediate(true);
		// field2.setBuffered(false);
		// ObjectProperty<Double> prop2 = new ObjectProperty<Double>(new Double(
		// 1234567890.123456));
		// field2.setPropertyDataSource(prop2);
		// field2.addValidator(new NumberBoundsValidator("Min = -3 | Max = 100")
		// .lower(100).greaterEqual(-3));
		// main.addComponent(field2, "top:110.0px;left:40.0px;");
		//
		// // Add Description (called Settings)
		// Label desc2 = new Label(CAPTION_HELP_2);
		// desc2.setContentMode(ContentMode.HTML);
		// desc2.setWidth("300px");
		// main.addComponent(desc2, "top:110.0px;left:200.0px;");
		//
		// // Add horizontal Line
		// Label line2 = new Label("<hr>");
		// line2.setContentMode(ContentMode.HTML);
		// main.addComponent(line2, "top:220.0px;left:40.0px;");
		//
		// /*
		// * Decimal Field 3
		// */
		//
		// // Add DecimalField
		// DecimalField field3 = new DecimalField("Decimal Field 3");
		// field3.setImmediate(true);
		// field3.setBuffered(false);
		// field3.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
		// ObjectProperty<Double> prop3 = new ObjectProperty<Double>(new Double(
		// 1234567890.123456));
		// field3.setPropertyDataSource(prop3);
		// field3.addValidator(new NumberBoundsValidator("Equals 8 validator")
		// .equal(8));
		// main.addComponent(field3, "top:250.0px;left:40.0px;");
		//
		// // Add Description (called Settings)
		// Label desc3 = new Label(CAPTION_HELP_3);
		// desc3.setContentMode(ContentMode.HTML);
		// desc3.setWidth("300px");
		// main.addComponent(desc3, "top:250.0px;left:200.0px;");
		//
		// // Add horizontal Line
		// Label line3 = new Label("<hr>");
		// line3.setContentMode(ContentMode.HTML);
		// main.addComponent(line3, "top:320.0px;left:40.0px;");
		//
		// /*
		// * Decimal Field 4
		// */
		//
		// // Add DecimalField
		// final DecimalField field4 = new DecimalField("Decimal Field 4");
		// field4.setImmediate(true);
		// field4.setBuffered(false);
		// field4.setDecimalFormatSymbols(new
		// DecimalFormatSymbols(Locale.GERMAN));
		// ObjectProperty<Double> prop4 = new ObjectProperty<Double>(new Double(
		// 1234567890.123456));
		// field4.setPropertyDataSource(prop4);
		// main.addComponent(field4, "top:370.0px;left:40.0px;");
		//
		// // Add CombpBox
		// ComboBox box4 = new ComboBox();
		// box4.setImmediate(true);
		// box4.addItem("de_DE");
		// box4.addItem("us_US");
		// box4.setValue("de_DE");
		// main.addComponent(box4, "top:370.0px;left:200.0px;");
		//
		// // Add Description (called Settings)
		// final Label desc4 = new Label(String.format(CAPTION_HELP_4,
		// box4.getValue()));
		// desc4.setContentMode(ContentMode.HTML);
		// desc4.setWidth("300px");
		// main.addComponent(desc4, "top:400.0px;left:200.0px;");
		//
		// // Add horizontal Line
		// Label line4 = new Label("<hr>");
		// line4.setContentMode(ContentMode.HTML);
		// main.addComponent(line4, "top:480.0px;left:40.0px;");
		//
		// // Listener for ComboBox
		// box4.addValueChangeListener(new Property.ValueChangeListener() {
		// @Override
		// public void valueChange(Property.ValueChangeEvent event) {
		// String value = (String) event.getProperty().getValue();
		// if (value.equals("de_DE")) {
		// field4.setDecimalFormatSymbols(new DecimalFormatSymbols(
		// Locale.GERMAN));
		// } else {
		// field4.setDecimalFormatSymbols(new DecimalFormatSymbols(
		// Locale.US));
		// }
		// desc4.setValue(String.format(CAPTION_HELP_4, value));
		// }
		// });
		//
		// /*
		// * DecimalField 5
		// */
		// // Add DecimalField
		// final DecimalField field5 = new DecimalField("Decimal Field 5");
		// field5.setImmediate(true);
		// field5.setBuffered(false);
		// DecimalFormatSymbols symbols5 = new
		// DecimalFormatSymbols(Locale.GERMAN);
		// symbols5.setGroupingSeparator(':');
		// field5.setDecimalFormatSymbols(symbols5);
		// ObjectProperty<Double> prop5 = new ObjectProperty<Double>(new Double(
		// 1234567890.123456));
		// field5.setPropertyDataSource(prop5);
		// prop5.setValue(123456789.998877d);
		// main.addComponent(field5, "top:510.0px;left:40.0px;");
		//
		// // Add ComboBox
		// ComboBox box5 = new ComboBox();
		// box5.setImmediate(true);
		// box5.addItem("de_DE");
		// box5.addItem("us_US");
		// box5.setValue("de_DE");
		// main.addComponent(box5, "top:510.0px;left:200.0px;");
		//
		// // Add Description (called Settings)
		// final Label desc5 = new Label(String.format(CAPTION_HELP_5,
		// box5.getValue()));
		// desc5.setContentMode(ContentMode.HTML);
		// desc5.setWidth("300px");
		// main.addComponent(desc5, "top:540.0px;left:200.0px;");
		//
		// // Add horizontal Line
		// Label line5 = new Label("<hr>");
		// line5.setContentMode(ContentMode.HTML);
		// main.addComponent(line5, "top:640.0px;left:40.0px;");
		//
		// // Listener for ComboBox
		// box5.addValueChangeListener(new Property.ValueChangeListener() {
		// @Override
		// public void valueChange(Property.ValueChangeEvent event) {
		// String value = (String) event.getProperty().getValue();
		// // set the decimalFormaSymbols! Setting the Locale is not enough
		// // since the DecimalField cached the original symbols
		// if (value.equals("de_DE")) {
		// DecimalFormatSymbols symbols = new DecimalFormatSymbols(
		// Locale.GERMAN);
		// symbols.setGroupingSeparator(':');
		// field5.setDecimalFormatSymbols(symbols);
		// } else {
		// DecimalFormatSymbols symbols = new DecimalFormatSymbols(
		// Locale.US);
		// symbols.setGroupingSeparator(':');
		// field5.setDecimalFormatSymbols(symbols);
		// }
		// desc5.setValue(String.format(CAPTION_HELP_5, value));
		// }
		// });
		//
		// // Add TextField
		// ObjectProperty<Double> prop7 = new ObjectProperty<Double>(new Double(
		// 1234567890.123456));
		// TextField field7 = new TextField("Text Field 1");
		// field7.setImmediate(true);
		// field7.setBuffered(false);
		// field7.setConverter(Double.class);
		// field7.setPropertyDataSource(prop7);
		// main.addComponent(field7, "top:670.0px;left:40.0px;");
		//
		// // Add Description (called Settings)
		// final Label desc7 = new Label(String.format(CAPTION_HELP_6));
		// desc7.setContentMode(ContentMode.HTML);
		// desc7.setWidth("300px");
		// main.addComponent(desc7, "top:670.0px;left:200.0px;");
		//
		// // Add horizontal Line
		// Label line8 = new Label("<hr>");
		// line8.setContentMode(ContentMode.HTML);
		// main.addComponent(line8, "top:730.0px;left:40.0px;");
		//
		// /*
		// * Decimal Field 8
		// */
		// DecimalField field8 = new DecimalField("Decimal Field 8");
		// field8.setImmediate(true);
		// field8.setBuffered(false);
		// field8.setUseGrouping(false);
		// field8.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
		// ObjectProperty<Double> prop8 = new ObjectProperty<Double>(new Double(
		// 1234567890.123456));
		// field8.setPropertyDataSource(prop8);
		// main.addComponent(field8, "top:760.0px;left:40.0px;");
		//
		// // Add Description (called Settings)
		// Label desc8 = new Label(CAPTION_HELP_8);
		// desc8.setContentMode(ContentMode.HTML);
		// desc8.setWidth("300px");
		// main.addComponent(desc8, "top:760.0px;left:200.0px;");

		return main;
	}

	@SuppressWarnings("serial")
	private AbsoluteLayout createNumericTab() {
		setStyleName(Reindeer.LAYOUT_BLUE);
		// Initialize
		AbsoluteLayout main = new AbsoluteLayout();
		main.setStyleName(Reindeer.LAYOUT_BLUE);
		main.setWidth("800px");
		main.setHeight("900px");

		/*
		 * Number Field 1
		 */
		// Add Decimal Field
		NumberField field1 = new NumberField("Number Field 1");
		field1.setImmediate(true);
		field1.setBuffered(false);
		ObjectProperty<Integer> prop1 = new ObjectProperty<Integer>(
				new Integer(1234567890));
		field1.setPropertyDataSource(prop1);
		main.addComponent(field1, "top:20.0px;left:40.0px;");

		// Add Description (called Settings)
		Label desc1 = new Label(CAPTION_HELP_1);
		desc1.setContentMode(ContentMode.HTML);
		desc1.setWidth("300px");
		main.addComponent(desc1, "top:20.0px;left:200.0px;");

		// Add horizontal Line
		Label line1 = new Label("<hr>");
		line1.setContentMode(ContentMode.HTML);
		main.addComponent(line1, "top:80.0px;left:40.0px;");

		/*
		 * Number Field 2
		 */

		// Add Number Field
		NumberField field2 = new NumberField("Number Field 2");
		field2.setImmediate(true);
		field2.setBuffered(false);
		ObjectProperty<Integer> prop2 = new ObjectProperty<Integer>(
				new Integer(1234567890));
		field2.setPropertyDataSource(prop2);
		field2.addValidator(new NumberBoundsValidator("Min = -3 | Max = 100")
				.lower(100).greaterEqual(-3));
		main.addComponent(field2, "top:110.0px;left:40.0px;");

		// Add Description (called Settings)
		Label desc2 = new Label(CAPTION_HELP_2);
		desc2.setContentMode(ContentMode.HTML);
		desc2.setWidth("300px");
		main.addComponent(desc2, "top:110.0px;left:200.0px;");

		// Add horizontal Line
		Label line2 = new Label("<hr>");
		line2.setContentMode(ContentMode.HTML);
		main.addComponent(line2, "top:220.0px;left:40.0px;");

		/*
		 * Number Field 3
		 */
		// Add Number Field
		NumberField field3 = new NumberField("Number Field 3");
		field3.setImmediate(true);
		field3.setBuffered(false);
		field3.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
		ObjectProperty<Integer> prop3 = new ObjectProperty<Integer>(
				new Integer(1234567890));
		field3.setPropertyDataSource(prop3);
		field3.addValidator(new NumberBoundsValidator("Equals 8 validator")
				.equal(8));
		main.addComponent(field3, "top:250.0px;left:40.0px;");

		// Add Description (called Settings)
		Label desc3 = new Label(CAPTION_HELP_3);
		desc3.setContentMode(ContentMode.HTML);
		desc3.setWidth("300px");
		main.addComponent(desc3, "top:250.0px;left:200.0px;");

		// Add horizontal Line
		Label line3 = new Label("<hr>");
		line3.setContentMode(ContentMode.HTML);
		main.addComponent(line3, "top:320.0px;left:40.0px;");

		/*
		 * Number Field 4
		 */

		// Add Number Field
		final NumberField field4 = new NumberField("Number Field 4");
		field4.setImmediate(true);
		field4.setBuffered(false);
		field4.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.GERMAN));
		ObjectProperty<Integer> prop4 = new ObjectProperty<Integer>(
				new Integer(1234567890));
		field4.setPropertyDataSource(prop4);
		main.addComponent(field4, "top:370.0px;left:40.0px;");

		// Add CombpBox
		ComboBox box4 = new ComboBox();
		box4.setImmediate(true);
		box4.addItem("de_DE");
		box4.addItem("us_US");
		box4.setValue("de_DE");
		main.addComponent(box4, "top:370.0px;left:200.0px;");

		// Add Description (called Settings)
		final Label desc4 = new Label(String.format(CAPTION_HELP_4,
				box4.getValue()));
		desc4.setContentMode(ContentMode.HTML);
		desc4.setWidth("300px");
		main.addComponent(desc4, "top:400.0px;left:200.0px;");

		// Add horizontal Line
		Label line4 = new Label("<hr>");
		line4.setContentMode(ContentMode.HTML);
		main.addComponent(line4, "top:480.0px;left:40.0px;");

		// Listener for ComboBox
		box4.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				String value = (String) event.getProperty().getValue();
				if (value.equals("de_DE")) {
					field4.setDecimalFormatSymbols(new DecimalFormatSymbols(
							Locale.GERMAN));
				} else {
					field4.setDecimalFormatSymbols(new DecimalFormatSymbols(
							Locale.US));
				}
				desc4.setValue(String.format(CAPTION_HELP_4, value));
			}
		});

		/*
		 * Number Field 5
		 */
		// Add Number Field
		final NumberField field5 = new NumberField("Number Field 5");
		field5.setImmediate(true);
		field5.setBuffered(false);
		DecimalFormatSymbols symbols5 = new DecimalFormatSymbols(Locale.GERMAN);
		symbols5.setGroupingSeparator(':');
		field5.setDecimalFormatSymbols(symbols5);
		ObjectProperty<Integer> prop5 = new ObjectProperty<Integer>(
				new Integer(1234567890));
		field5.setPropertyDataSource(prop5);
		prop5.setValue(123456789);
		main.addComponent(field5, "top:510.0px;left:40.0px;");

		// Add ComboBox
		ComboBox box5 = new ComboBox();
		box5.setImmediate(true);
		box5.addItem("de_DE");
		box5.addItem("us_US");
		box5.setValue("de_DE");
		main.addComponent(box5, "top:510.0px;left:200.0px;");

		// Add Description (called Settings)
		final Label desc5 = new Label(String.format(CAPTION_HELP_5,
				box5.getValue()));
		desc5.setContentMode(ContentMode.HTML);
		desc5.setWidth("300px");
		main.addComponent(desc5, "top:540.0px;left:200.0px;");

		// Add horizontal Line
		Label line5 = new Label("<hr>");
		line5.setContentMode(ContentMode.HTML);
		main.addComponent(line5, "top:640.0px;left:40.0px;");

		// Listener for ComboBox
		box5.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				String value = (String) event.getProperty().getValue();
				// set the decimalFormaSymbols! Setting the Locale is not enough
				// since the DecimalField cached the original symbols
				if (value.equals("de_DE")) {
					DecimalFormatSymbols symbols = new DecimalFormatSymbols(
							Locale.GERMAN);
					symbols.setGroupingSeparator(':');
					field5.setDecimalFormatSymbols(symbols);
				} else {
					DecimalFormatSymbols symbols = new DecimalFormatSymbols(
							Locale.US);
					symbols.setGroupingSeparator(':');
					field5.setDecimalFormatSymbols(symbols);
				}
				desc5.setValue(String.format(CAPTION_HELP_5, value));
			}
		});

		// Add TextField
		ObjectProperty<Integer> prop7 = new ObjectProperty<Integer>(
				new Integer(1234567890));
		TextField field7 = new TextField("Text Field 1");
		field7.setImmediate(true);
		field7.setBuffered(false);
		field7.setConverter(Integer.class);
		field7.setPropertyDataSource(prop7);
		main.addComponent(field7, "top:670.0px;left:40.0px;");

		// Add Description (called Settings)
		final Label desc7 = new Label(String.format(CAPTION_HELP_6));
		desc7.setContentMode(ContentMode.HTML);
		desc7.setWidth("300px");
		main.addComponent(desc7, "top:670.0px;left:200.0px;");

		// Add horizontal Line
		Label line8 = new Label("<hr>");
		line8.setContentMode(ContentMode.HTML);
		main.addComponent(line8, "top:730.0px;left:40.0px;");

		/*
		 * Number Field 8
		 */
		NumberField field8 = new NumberField("Number Field 8");
		field8.setImmediate(true);
		field8.setBuffered(false);
		field8.setUseGrouping(false);
		field8.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
		ObjectProperty<Integer> prop8 = new ObjectProperty<Integer>(
				new Integer(1234567890));
		field8.setPropertyDataSource(prop8);
		main.addComponent(field8, "top:760.0px;left:40.0px;");

		// Add Description (called Settings)
		Label desc8 = new Label(CAPTION_HELP_8);
		desc8.setContentMode(ContentMode.HTML);
		desc8.setWidth("300px");
		main.addComponent(desc8, "top:760.0px;left:200.0px;");

		return main;
	}
}
