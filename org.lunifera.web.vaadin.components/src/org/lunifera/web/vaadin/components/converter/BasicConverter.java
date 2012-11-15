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
package org.lunifera.web.vaadin.components.converter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.util.converter.StringToDoubleConverter;

/**
 * @author haglo
 * 
 */
@SuppressWarnings("serial")
public class BasicConverter extends StringToDoubleConverter {
	private String numberFormatPattern;
	private DecimalFormatSymbols decimalFormatSymbols;

	/**
	 * 
	 * @param numberFormatPattern
	 *            the numberFormatPattern to set
	 */
	public void setNumberFormatPattern(String numberFormatPattern) {
		this.numberFormatPattern = numberFormatPattern;
	}

	/**
	 * 
	 * @param decimalFormatSymbols
	 *            the decimalFormatSymbols to set
	 */
	public void setDecimalFormatSymbols(
			DecimalFormatSymbols decimalFormatSymbols) {
		this.decimalFormatSymbols = decimalFormatSymbols;
	}

	protected NumberFormat getFormat(Locale locale) {
		if (locale == null) {
			locale = Locale.getDefault();
		}

		NumberFormat result = null;
		if (numberFormatPattern != null && !numberFormatPattern.equals("")) {
			if (decimalFormatSymbols != null) {
				result = new DecimalFormat(numberFormatPattern,
						decimalFormatSymbols);
			} else {
				result = new DecimalFormat(numberFormatPattern,
						new DecimalFormatSymbols(locale));
			}
		} else {
			result = NumberFormat.getNumberInstance(locale);
		}
		return result;
	}
}
