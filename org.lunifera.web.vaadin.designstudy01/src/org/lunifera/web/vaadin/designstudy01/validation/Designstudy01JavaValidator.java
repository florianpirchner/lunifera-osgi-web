package org.lunifera.web.vaadin.designstudy01.validation;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.validation.Check;
import org.lunifera.web.vaadin.designstudy01.designstudy01.Designstudy01Package;
import org.lunifera.web.vaadin.designstudy01.designstudy01.UiDetailPart;
import org.lunifera.web.vaadin.designstudy01.designstudy01.UiSimpleField;
import org.lunifera.web.vaadin.designstudy01.designstudy01.UiTextField;

public class Designstudy01JavaValidator extends
		AbstractDesignstudy01JavaValidator {

	@Check
	public void checkLabels(UiDetailPart part) {

		int i = 0;
		Set<String> unique = new HashSet<String>();
		for (UiSimpleField field : part.getContent()) {
			String name = "";
			if (field instanceof UiTextField) {
				name = ((UiTextField) field).getName();
			}
			if (unique.contains(name)) {
				error("Field names must be unique!",
						Designstudy01Package.Literals.UI_DETAIL_PART__CONTENT,
						i, "duplicateEntry", name);
			}
			unique.add(name);

			i++;
		}
	}

}
