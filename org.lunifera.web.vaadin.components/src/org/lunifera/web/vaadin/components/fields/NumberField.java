/*******************************************************************************
 * Copyright (c) 2011 Florian Pirchner, Hans-Georg Glöckler
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Florian Pirchner - initial API and implementation
 *    Hans-Georg Glöckler - initial API and implementation
 *******************************************************************************/
package org.lunifera.web.vaadin.components.fields;

import java.text.DecimalFormatSymbols;

import org.lunifera.web.vaadin.components.converter.NumberConverter;

/**
 * A numericfield specific for redvoodo.
 */
@SuppressWarnings("serial")
public class NumberField extends TextField {

	private final NumberConverter converter;

	public NumberField() {
		this(null);
	}

	public NumberField(String caption) {
		this(caption, null);
	}

	public NumberField(String caption, NumberConverter converter) {
		super(caption);

		setNullRepresentation("");
		setNullSettingAllowed(false);

		// Important: Is responsible that the Converter is used in the Field
		this.converter = converter != null ? converter : createConverter();
		setConverter(this.converter);
	}

	/**
	 * Creates a default converter.
	 * 
	 * @return
	 */
	protected NumberConverter createConverter() {
		return new NumberConverter();
	}

	/**
	 * Sets the number format to be used.
	 * 
	 * @param numberFormatPattern
	 *            the numberFormat to set
	 */
	public void setNumberFormatPattern(String numberFormatPattern) {
		converter.setNumberFormatPattern(numberFormatPattern);

		markAsDirty();
	}

	/**
	 * Sets the Symbols which are used to Format.
	 * 
	 * @param decimalFormatSymbols
	 */
	public void setDecimalFormatSymbols(
			DecimalFormatSymbols decimalFormatSymbols) {
		converter.setDecimalFormatSymbols(decimalFormatSymbols);

		markAsDirty();
	}

	/**
	 * Returns true, if grouping is used. False otherwise.
	 * 
	 * @return
	 */
	public boolean isUseGrouping() {
		return converter.isUseGrouping();
	}

	/**
	 * Set true, if grouping should be used. False otherwise.
	 * 
	 * @param useGrouping
	 */
	public void setUseGrouping(boolean useGrouping) {
		converter.setUseGrouping(useGrouping);

		markAsDirty();
	}
}
