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

import org.lunifera.web.vaadin.components.converter.BasicConverter;

/**
 * A decimalfield specific for redvoodo.
 * 
 * @author haglo
 * 
 */
@SuppressWarnings("serial")
public class DecimalField extends NumberField {

	private final BasicConverter basicConverter = new BasicConverter();

	public DecimalField() {
		this(null);
	}

	public DecimalField(String caption) {
		super(caption);

		// Important: Is responsible that the Converter is used in the Field
		setConverter(basicConverter);
	}

	/**
	 * @param nullAllowed
	 *            the nullAllowed to set
	 */
	public void setNullSettingAllowed(boolean nullAllowed) {
		super.setNullSettingAllowed(nullAllowed);
	}

	/**
	 * @param nullRepresentation
	 *            the nullRepresentation to set
	 */
	public void setNullRepresentation(String nullRepresentation) {
		super.setNullRepresentation(nullRepresentation);
	}

	/**
	 * Sets the number format to be used.
	 * 
	 * @param numberFormatPattern
	 *            the numberFormat to set
	 */
	public void setNumberFormatPattern(String numberFormatPattern) {
		basicConverter.setNumberFormatPattern(numberFormatPattern);

		markAsDirty();
	}

	/**
	 * Sets the Symbols which are used to Format.
	 * 
	 * @param decimalFormatSymbols
	 */
	public void setDecimalFormatSymbols(
			DecimalFormatSymbols decimalFormatSymbols) {
		basicConverter.setDecimalFormatSymbols(decimalFormatSymbols);

		markAsDirty();
	}

}
