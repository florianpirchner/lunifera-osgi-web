/*******************************************************************************
 * Copyright (c) 2011 Florian Pirchner
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Florian Pirchner - initial API and implementation
 *******************************************************************************/
package org.lunifera.web.ecp.uimodel.presentation.vaadin.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecp.ui.model.core.uimodel.YUiEmbeddable;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiAlignment;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayout;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayoutCellStyle;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiSpanInfo;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.IUiElementEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.IUiEmbeddableEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.IUiLayoutEditpart;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.presentation.IWidgetPresentation;
import org.lunifera.web.ecp.uimodel.presentation.vaadin.IConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;

/**
 * This presenter is responsible to render a text field on the given layout.
 */
public class GridLayoutPresentation extends AbstractLayoutPresenter {

	private static final Logger logger = LoggerFactory.getLogger(GridLayoutPresentation.class);

	private CssLayout componentBase;
	private GridLayout gridlayout;
	private ModelAccess modelAccess;

	/**
	 * The constructor.
	 * 
	 * @param editpart The editpart of that presentation.
	 */
	public GridLayoutPresentation(IUiElementEditpart editpart) {
		super((IUiLayoutEditpart) editpart);
		this.modelAccess = new ModelAccess((YUiGridLayout) editpart.getModel());
	}

	@Override
	public Object getModel() {
		return getEditpart().getModel();
	}

	@Override
	public void add(IWidgetPresentation<?> presentation) {
		super.add(presentation);

		refreshUI();
	}

	@Override
	public void remove(IWidgetPresentation<?> presentation) {
		super.remove(presentation);

		presentation.unrender();
		refreshUI();
	}

	@Override
	public void insert(IWidgetPresentation<?> presentation, int index) {
		super.insert(presentation, index);

		refreshUI();
	}

	@Override
	public void move(IWidgetPresentation<?> presentation, int index) {
		super.move(presentation, index);

		refreshUI();
	}

	/**
	 * Is called to refresh the UI. The element will be removed from the grid layout and added to it again afterwards.
	 */
	protected void refreshUI() {
		gridlayout.removeAllComponents();

		// create a map containing the style for the embeddable
		//
		Map<YUiEmbeddable, YUiGridLayoutCellStyle> yStyles = new HashMap<YUiEmbeddable, YUiGridLayoutCellStyle>();
		for (YUiGridLayoutCellStyle style : modelAccess.getCellStyles()) {
			if (yStyles.containsKey(style.getTarget())) {
				logger.warn("Multiple style for element {}", style.getTarget());
			}
			yStyles.put(style.getTarget(), style);
		}

		// iterate all elements and build the child element
		//
		for (IUiEmbeddableEditpart editPart : getEditpart().getElements()) {
			IWidgetPresentation<?> childPresentation = editPart.getPresentation();
			YUiEmbeddable yChild = (YUiEmbeddable) childPresentation.getModel();
			buildChild(childPresentation, yStyles.get(yChild));
		}

		// handle packaging - therefore a new row / column is added and set to expandRatio = 1.0f. This will cause the
		// last row / column to grab excess space.
		//
		if (modelAccess.isPackHorizontal()) {
			int packingHelperColumnIndex = gridlayout.getColumns();
			gridlayout.setColumns(packingHelperColumnIndex + 1);
			gridlayout.setColumnExpandRatio(packingHelperColumnIndex, 1.0f);
		}

		if (modelAccess.isPackVertical()) {
			int packingHelperRowIndex = gridlayout.getRows();
			gridlayout.setRows(packingHelperRowIndex + 1);
			gridlayout.setRowExpandRatio(packingHelperRowIndex, 1.0f);
		}

	}

	/**
	 * Is called to create the child component and apply layouting defaults to it.
	 * 
	 * @param presentation
	 * @param yStyle
	 * @return
	 */
	protected Component buildChild(IWidgetPresentation<?> presentation, YUiGridLayoutCellStyle yStyle) {

		Component child = (Component) presentation.createWidget(gridlayout);

		// applies the child to the parent
		//
		applyToParent(child, yStyle);

		// calculate the required settings for packing
		//
		applyPacking(child);

		if (yStyle != null) {
			// applies the alignment settings
			applyAlignment(child, yStyle.getAlignment());
			applyGrapSpace(child, yStyle.isGrabHorizontalSpace(), yStyle.isGrabVerticalSpace());
		} else {
			applyAlignment(child, YUiAlignment.MIDDLE_CENTER);
			applyGrapSpace(child, false, false);
		}

		return child;
	}

	/**
	 * GrapSpace means, that the component increases its width / height up to 100% of available space.
	 * 
	 * @param child
	 * @param grapHorizontal
	 * @param grapVertical
	 */
	protected void applyGrapSpace(Component child, boolean grapHorizontal, boolean grapVertical) {
		if (grapHorizontal) {
			child.setWidth("100%");
		}

		if (grapVertical) {
			child.setHeight("100%");
		}
	}

	/**
	 * Applies the child to its parent.
	 * 
	 * @param child
	 * @param yStyle
	 */
	protected void applyToParent(Component child, YUiGridLayoutCellStyle yStyle) {

		// calculate the spanning of the element
		//
		int col1 = -1;
		int row1 = -1;
		int col2 = -1;
		int row2 = -1;
		if (yStyle != null) {
			YUiSpanInfo ySpanInfo = yStyle.getSpanInfo();
			if (ySpanInfo != null) {
				col1 = ySpanInfo.getColumnFrom();
				row1 = ySpanInfo.getRowFrom();
				col2 = ySpanInfo.getColumnTo();
				row2 = ySpanInfo.getRowTo();
			}
		}

		// add the element to the grid layout
		//
		if (col1 >= 0 && row1 >= 0 && col1 <= col2 && row1 <= row2) {
			gridlayout.setRows(row2 + 1);
			gridlayout.addComponent(child, col1, row1, col2, row2);
		} else if (col1 < 0 || row1 < 0) {
			gridlayout.addComponent(child);
		} else {
			gridlayout.addComponent(child);
			logger.warn("Invalid span: col1 {}, row1 {}, col2 {}, row2{}", new Object[] { col1, row1, col2, row2 });
		}
	}

	/**
	 * Packaging settings are applied to the component. Packaging means, that the components decrease their size to its
	 * preferred value. If no packaging is done, the components are going to increase their size to 100% of available
	 * space.
	 * 
	 * @param child
	 */
	protected void applyPacking(Component child) {
		child.setSizeUndefined();
		if (!modelAccess.isPackHorizontal()) {
			child.setWidth("100%");
		}
		if (!modelAccess.isPackVertical()) {
			child.setHeight("100%");
		}
	}

	/**
	 * Sets the alignment to the component.
	 * 
	 * @param child
	 * @param yAlignment
	 */
	protected void applyAlignment(Component child, YUiAlignment yAlignment) {

		if (yAlignment != null) {
			child.setWidth("-1%");
			child.setHeight("-1%");
			switch (yAlignment) {
			case BOTTOM_CENTER:
				gridlayout.setComponentAlignment(child, Alignment.BOTTOM_CENTER);
				break;
			case BOTTOM_FILL:
				gridlayout.setComponentAlignment(child, Alignment.BOTTOM_LEFT);
				child.setWidth("100%");
				break;
			case BOTTOM_LEFT:
				gridlayout.setComponentAlignment(child, Alignment.BOTTOM_LEFT);
				break;
			case BOTTOM_RIGHT:
				gridlayout.setComponentAlignment(child, Alignment.BOTTOM_RIGHT);
				break;
			case MIDDLE_CENTER:
				gridlayout.setComponentAlignment(child, Alignment.MIDDLE_CENTER);
				break;
			case MIDDLE_FILL:
				gridlayout.setComponentAlignment(child, Alignment.MIDDLE_LEFT);
				child.setWidth("100%");
				break;
			case MIDDLE_LEFT:
				gridlayout.setComponentAlignment(child, Alignment.MIDDLE_LEFT);
				break;
			case MIDDLE_RIGHT:
				gridlayout.setComponentAlignment(child, Alignment.MIDDLE_RIGHT);
				break;
			case TOP_CENTER:
				gridlayout.setComponentAlignment(child, Alignment.TOP_CENTER);
				break;
			case TOP_FILL:
				gridlayout.setComponentAlignment(child, Alignment.TOP_LEFT);
				child.setWidth("100%");
				break;
			case TOP_LEFT:
				gridlayout.setComponentAlignment(child, Alignment.TOP_LEFT);
				break;
			case TOP_RIGHT:
				gridlayout.setComponentAlignment(child, Alignment.TOP_RIGHT);
				break;
			case FILL_CENTER:
				gridlayout.setComponentAlignment(child, Alignment.TOP_CENTER);
				child.setHeight("100%");
				break;
			case FILL_FILL:
				gridlayout.setComponentAlignment(child, Alignment.TOP_LEFT);
				child.setWidth("100%");
				child.setHeight("100%");
				break;
			case FILL_LEFT:
				gridlayout.setComponentAlignment(child, Alignment.TOP_LEFT);
				child.setHeight("100%");
				break;
			case FILL_RIGHT:
				gridlayout.setComponentAlignment(child, Alignment.TOP_RIGHT);
				child.setHeight("100%");
				break;
			}
		}
	}

	@Override
	public ComponentContainer createWidget(Object parent) {
		if (componentBase == null) {
			componentBase = new CssLayout();
			componentBase.addStyleName(CSS_CLASS__CONTROL_BASE);
			componentBase.setSizeFull();

			gridlayout = new GridLayout(modelAccess.getColumns(), 1);
			gridlayout.setSizeFull();
			gridlayout.setSpacing(false);
			componentBase.addComponent(gridlayout);

			if (modelAccess.isMargin()) {
				gridlayout.addStyleName(IConstants.CSS_CLASS__MARGIN);
				gridlayout.setMargin(true);
			}

			if (modelAccess.isSpacing()) {
				gridlayout.setData(IConstants.CSS_CLASS__SPACING);
				gridlayout.setSpacing(true);
			}

			if (modelAccess.isCssIdValid()) {
				gridlayout.setId(modelAccess.getCssID());
			} else {
				gridlayout.setId(getEditpart().getId());
			}

			if (modelAccess.isCssClassValid()) {
				gridlayout.addStyleName(modelAccess.getCssClass());
			} else {
				gridlayout.addStyleName(CSS_CLASS__CONTROL);
			}

			// load presentations once
			loadChildPresentations();
			renderChildren(false);
		}

		return componentBase;
	}

	/**
	 * Loads all child presentations from the edit part into the internal cache.
	 */
	protected void loadChildPresentations() {
		for (IUiEmbeddableEditpart editPart : getEditpart().getElements()) {
			IWidgetPresentation<?> presentation = editPart.getPresentation();
			if (!contains(presentation)) {
				// will be rendered automatically after add
				super.add(presentation);
			}
		}
	}

	@Override
	public ComponentContainer getWidget() {
		return componentBase;
	}

	@Override
	public boolean isRendered() {
		return componentBase != null;
	}

	@Override
	protected void internalDispose() {
		unrender();
	}

	@Override
	public void unrender() {
		if (componentBase != null) {
			ComponentContainer parent = ((ComponentContainer) componentBase.getParent());
			if (parent != null) {
				parent.removeComponent(componentBase);
			}
			componentBase = null;
			gridlayout = null;

			// unrender the childs
			for (IWidgetPresentation<?> child : getChildren()) {
				child.unrender();
			}
		}
	}

	@Override
	public void renderChildren(boolean force) {
		if (force) {
			unrenderChildren();
		}

		refreshUI();
	}

	/**
	 * Will unrender all children.
	 */
	protected void unrenderChildren() {
		for (IWidgetPresentation<?> presentation : getChildren()) {
			if (presentation.isRendered()) {
				presentation.unrender();
			}
		}
	}

	/**
	 * An internal helper class.
	 */
	private static class ModelAccess {
		private final YUiGridLayout yLayout;

		public ModelAccess(YUiGridLayout yLayout) {
			super();
			this.yLayout = yLayout;
		}

		/**
		 * @return
		 * @see org.eclipse.emf.ecp.ui.model.core.uimodel.YUiCssAble#getCssClass()
		 */
		public String getCssClass() {
			return yLayout.getCssClass();
		}

		/**
		 * Returns true, if the css class is not null and not empty.
		 * 
		 * @return
		 */
		public boolean isCssClassValid() {
			return getCssClass() != null && !getCssClass().equals("");
		}

		/**
		 * @return
		 * @see org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayout#isSpacing()
		 */
		public boolean isSpacing() {
			return yLayout.isSpacing();
		}

		/**
		 * @return
		 * @see org.eclipse.emf.ecp.ui.model.core.uimodel.YUiCssAble#getCssID()
		 */
		public String getCssID() {
			return yLayout.getCssID();
		}

		/**
		 * Returns true, if the css id is not null and not empty.
		 * 
		 * @return
		 */
		public boolean isCssIdValid() {
			return getCssID() != null && !getCssID().equals("");
		}

		/**
		 * @return
		 * @see org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayout#isMargin()
		 */
		public boolean isMargin() {
			return yLayout.isMargin();
		}

		/**
		 * @return
		 * @see org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayout#getColumns()
		 */
		public int getColumns() {
			int columns = yLayout.getColumns();
			return columns <= 0 ? 2 : columns;
		}

		/**
		 * @return
		 * @see org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayout#getCellStyles()
		 */
		public EList<YUiGridLayoutCellStyle> getCellStyles() {
			return yLayout.getCellStyles();
		}

		/**
		 * @return
		 * @see org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayout#isPackHorizontal()
		 */
		public boolean isPackHorizontal() {
			return yLayout.isPackHorizontal();
		}

		/**
		 * @return
		 * @see org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayout#isPackVertical()
		 */
		public boolean isPackVertical() {
			return yLayout.isPackVertical();
		}
	}
}
