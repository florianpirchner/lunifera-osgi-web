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

import org.eclipse.emf.ecp.ui.model.core.datatypes.YDatadescription;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiInput;

public class Util {

	/**
	 * Returns the css class.
	 * 
	 * @param yInput
	 * @return
	 * @see org.eclipse.emf.ecp.ui.model.core.uimodel.YUiCssAble#getCssClass()
	 */
	public static String getCssClass(YUiInput yInput) {
		return yInput.getCssClass();
	}

	/**
	 * Returns true, if the css class is not null and not empty.
	 * 
	 * @param yInput
	 * 
	 * @return
	 */
	public static boolean isCssClassValid(YUiInput yInput) {
		return getCssClass(yInput) != null && !getCssClass(yInput).equals("");
	}

	/**
	 * Returns the css id.
	 * 
	 * @param yInput
	 * @return
	 * @see org.eclipse.emf.ecp.ui.model.core.uimodel.YUiCssAble#getCssID()
	 */
	public static String getCssID(YUiInput yInput) {
		return yInput.getCssID();
	}

	/**
	 * Returns true, if the css id is not null and not empty.
	 * 
	 * @param yInput
	 * 
	 * @return
	 */
	public static boolean isCssIdValid(YUiInput yInput) {
		return getCssID(yInput) != null && !getCssID(yInput).equals("");
	}

	/**
	 * Returns true, if the label is valid.
	 * 
	 * @param yDatadescription
	 * 
	 * @return
	 */
	public static boolean isLabelValid(YDatadescription yDatadescription) {
		return yDatadescription != null && yDatadescription.getLabel() != null;
	}

	/**
	 * Returns the label.
	 * 
	 * @param yDatadescription
	 * 
	 * @return
	 */
	public static String getLabel(YDatadescription yDatadescription) {
		return yDatadescription.getLabel();
	}
}
