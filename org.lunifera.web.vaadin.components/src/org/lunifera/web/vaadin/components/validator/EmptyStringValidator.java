/**
 * 
 */
package org.lunifera.web.vaadin.components.validator;

import com.vaadin.data.validator.StringLengthValidator;

/**
 *
 */
@SuppressWarnings("serial")
public class EmptyStringValidator extends StringLengthValidator {

	public EmptyStringValidator(String errorMessage) {
		super(errorMessage, 1, -1, true);
	}

	/**
	 * Checks if empty values are allowed
	 * 
	 * @param allowed
	 *            the value to set
	 * @return
	 */
	public EmptyStringValidator setEmptyAllowed(boolean allowed) {
		if (allowed) {
			setMinLength(0);
		} else {
			setMinLength(1);
		}
		return this;
	}

}
