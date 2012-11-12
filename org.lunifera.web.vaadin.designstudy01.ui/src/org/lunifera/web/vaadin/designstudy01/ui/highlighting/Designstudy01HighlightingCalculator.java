/**
 * Copyright (c) 2011 - 2012, Florian Pirchner - lunifera.org
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributions:
 * 		Florian Pirchner - Initial implementation
 */
package org.lunifera.web.vaadin.designstudy01.ui.highlighting;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.nodemodel.BidiTreeIterator;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;

public class Designstudy01HighlightingCalculator implements
		ISemanticHighlightingCalculator {

	private static Set<String> escaped = new HashSet<String>();
	static {
		escaped.add("firstname");
		escaped.add("lastname");
		escaped.add("age");
		escaped.add("Person");
	}

	public void provideHighlightingFor(XtextResource resource,
			IHighlightedPositionAcceptor acceptor) {
		if (resource == null || resource.getParseResult() == null)
			return;

		INode root = resource.getParseResult().getRootNode();
		BidiTreeIterator<INode> it = root.getAsTreeIterable().iterator();
		while (it.hasNext()) {
			INode node = it.next();
			String text = node.getText();
			if (escaped.contains(text)) {
				acceptor.addPosition(node.getOffset(), node.getLength(),
						Designstudy01HighlightingConfiguration.DEFAULT_ID);
			}
		}
	}
}
