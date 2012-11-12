/*
 * generated by Xtext
 */
package org.lunifera.web.vaadin.designstudy01.formatting;

import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.lunifera.web.vaadin.designstudy01.services.Designstudy01GrammarAccess;
import org.lunifera.web.vaadin.designstudy01.services.Designstudy01GrammarAccess.UiDetailPartElements;
import org.lunifera.web.vaadin.designstudy01.services.Designstudy01GrammarAccess.UiMasterDetailTilesElements;
import org.lunifera.web.vaadin.designstudy01.services.Designstudy01GrammarAccess.UiMasterPartElements;
import org.lunifera.web.vaadin.designstudy01.services.Designstudy01GrammarAccess.UiTableElements;
import org.lunifera.web.vaadin.designstudy01.services.Designstudy01GrammarAccess.UiTextFieldElements;

/**
 * This class contains custom formatting description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#formatting
 * on how and when to use it
 * 
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an
 * example
 */
public class Designstudy01Formatter extends AbstractDeclarativeFormatter {

	@Override
	protected void configureFormatting(FormattingConfig c) {
		configure(c, (Designstudy01GrammarAccess) getGrammarAccess());
	}

	public void configure(FormattingConfig c, Designstudy01GrammarAccess ga) {

		c.setAutoLinewrap(120);

		configure(c, ga.getUiDetailPartAccess());
		configure(c, ga.getUiMasterDetailTilesAccess());
		configure(c, ga.getUiMasterPartAccess());

		configure(c, ga.getUiTextFieldAccess());
		configure(c, ga.getUiTableAccess());
	}

	private void configure(FormattingConfig config, UiDetailPartElements ele) {
		config.setLinewrap(1, 1, 2).around(ele.getRule());
		config.setLinewrap(1, 1, 2).around(ele.getSizeKeyword_3_0_0());
		config.setLinewrap(1, 1, 2).around(ele.getTextAlignAssignment_3_1_2());
		config.setLinewrap(1, 1, 2).after(ele.getLeftCurlyBracketKeyword_2());
		config.setLinewrap(1).before(ele.getRightCurlyBracketKeyword_5());
		// indentation
		config.setIndentationIncrement().after(
				ele.getLeftCurlyBracketKeyword_2());
		config.setIndentationDecrement().before(
				ele.getRightCurlyBracketKeyword_5());
	}

	private void configure(FormattingConfig config,
			UiMasterDetailTilesElements ele) {
		config.setLinewrap(1, 1, 2).around(ele.getRule());
		config.setLinewrap(1, 1, 2).after(ele.getLeftCurlyBracketKeyword_3());
		config.setLinewrap(1).before(ele.getRightCurlyBracketKeyword_6());
		// indentation
		config.setIndentationIncrement().after(
				ele.getLeftCurlyBracketKeyword_3());
		config.setIndentationDecrement().before(
				ele.getRightCurlyBracketKeyword_6());
	}

	private void configure(FormattingConfig config, UiMasterPartElements ele) {
		config.setLinewrap(1, 1, 2).around(ele.getRule());
		config.setLinewrap(1, 1, 2).after(ele.getLeftCurlyBracketKeyword_2());
		config.setLinewrap(1).before(ele.getRightCurlyBracketKeyword_4());
		// indentation
		config.setIndentationIncrement().after(
				ele.getLeftCurlyBracketKeyword_2());
		config.setIndentationDecrement().before(
				ele.getRightCurlyBracketKeyword_4());
	}

	private void configure(FormattingConfig config, UiTextFieldElements ele) {
		config.setLinewrap(1, 1, 2).around(ele.getRule());
	}

	private void configure(FormattingConfig config, UiTableElements ele) {
		config.setLinewrap(1, 1, 2).around(ele.getRule());
	}
}