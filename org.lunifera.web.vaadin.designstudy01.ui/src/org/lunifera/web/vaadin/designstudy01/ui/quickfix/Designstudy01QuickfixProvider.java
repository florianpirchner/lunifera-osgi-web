package org.lunifera.web.vaadin.designstudy01.ui.quickfix;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

public class Designstudy01QuickfixProvider extends DefaultQuickfixProvider {

	/**
	 * Convert the name to lower case.
	 * 
	 * @param issue
	 * @param acceptor
	 */
	@Fix("duplicateEntry")
	public void convertNameToLowercase(final Issue issue,
			IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Rename name",
				"Renames the name by appending a number", "refactor.png",
				new IModification() {
					public void apply(IModificationContext context)
							throws BadLocationException {
						IXtextDocument xtextDocument = context
								.getXtextDocument();
						String line = xtextDocument.get(issue.getOffset(),
								issue.getLength());
						line= line.replaceFirst(issue.getData()[0],
								issue.getData()[0] + "_1");
						xtextDocument.replace(issue.getOffset(),
								issue.getLength(), line);
					}
				});
	}
}
