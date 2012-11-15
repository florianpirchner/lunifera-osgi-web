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
 * A converter used to format and parse Decimal values.
 */
@SuppressWarnings("serial")
public class DecimalConverter extends StringToDoubleConverter {
	private String numberFormatPattern;
	private boolean useGrouping = true;
	private DecimalFormatSymbols decimalFormatSymbols;

	/**
	 * Sets the number format pattern that should be used to format the number.
	 * 
	 * @param numberFormatPattern
	 *            the numberFormatPattern to set
	 */
	public void setNumberFormatPattern(String numberFormatPattern) {
		this.numberFormatPattern = numberFormatPattern;
	}

	/**
	 * Sets the {@link DecimalFormatSymbols} that should be used by the
	 * formatter.
	 * 
	 * @param decimalFormatSymbols
	 *            the decimalFormatSymbols to set
	 */
	public void setDecimalFormatSymbols(
			DecimalFormatSymbols decimalFormatSymbols) {
		this.decimalFormatSymbols = decimalFormatSymbols;
	}

	/**
	 * If true, then grouping should be used. False otherwise. Default is true.
	 * 
	 * @return
	 */
	public boolean isUseGrouping() {
		return useGrouping;
	}

	/**
	 * If true, then grouping should be used. False otherwise. Default is true.
	 * 
	 * @param useGrouping
	 */
	public void setUseGrouping(boolean useGrouping) {
		this.useGrouping = useGrouping;
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

		result.setGroupingUsed(useGrouping);

		return result;
	}
}
