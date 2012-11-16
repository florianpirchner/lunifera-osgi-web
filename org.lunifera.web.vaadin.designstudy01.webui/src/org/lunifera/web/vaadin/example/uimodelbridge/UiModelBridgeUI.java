/*******************************************************************************
 * Copyright (c) 2012 by committers of lunifera.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Florian Pirchner - initial API and implementation
 * 
 *******************************************************************************/
package org.lunifera.web.vaadin.example.uimodelbridge;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecp.ui.model.core.datatypes.YDatadescription;
import org.eclipse.emf.ecp.ui.model.core.uimodel.YUiView;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayout;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiGridLayoutCellStyle;
import org.eclipse.emf.ecp.ui.model.core.uimodel.extension.YUiTextField;
import org.eclipse.emf.ecp.ui.model.core.uimodel.util.SimpleModelFactory;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.beans.IValueBean;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.context.ContextException;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.context.IViewContext;
import org.eclipse.emf.ecp.ui.uimodel.core.editparts.presentation.IWidgetPresentation;
import org.lunifera.web.ecp.uimodel.presentation.vaadin.VaadinRenderer;
import org.lunifera.web.vaadin.designstudy01.api.IPerson;
import org.lunifera.web.vaadin.designstudy01.api.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.artur.icepush.ICEPush;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Specify the class name after the factory name.
 */
@Theme("ds01")
public class UiModelBridgeUI extends UI implements UI.CleanupListener,
		IUiAccess, ItemClickEvent.ItemClickListener {

	private static final Logger logger = LoggerFactory
			.getLogger(UiModelBridgeUI.class);

	private static final long serialVersionUID = 1L;

	private SimpleModelFactory factory = new SimpleModelFactory();

	private CssLayout mainLayout;

	private ICEPush icePush;

	private Table masterTable;

	private IViewContext context;

	private IPersonService service;
	private IPerson itemId;

	private static Set<IUiAccess> uiaccesses = new HashSet<IUiAccess>();

	/**
	 * @return the iNSTANCES
	 */
	public static Set<IUiAccess> getUiAccesses() {
		return uiaccesses;
	}

	/**
	 * Called by OSGi DS
	 * 
	 * @param service
	 */
	public void setService(IPersonService service) {
		this.service = service;
	}

	/**
	 * Called by OSGi DS
	 * 
	 * @param service
	 */
	public void unsetService(IPersonService service) {
		this.service = null;
	}

	@SuppressWarnings("serial")
	@Override
	public void init(VaadinRequest request) {

		// create ui
		addStyleName(Reindeer.LAYOUT_BLUE);

		VerticalLayout content = (VerticalLayout) getContent();
		content.addStyleName(Reindeer.LAYOUT_BLUE);
		content.setSpacing(true);
		content.setSizeFull();

		icePush = new ICEPush();
		addComponent(icePush);

		Label heading = new Label("Designstudy - Master-Detail-Tiles");
		heading.setWidth("100%");
		heading.addStyleName(Reindeer.LABEL_H1);
		content.addComponent(heading);
		content.setComponentAlignment(heading, Alignment.TOP_CENTER);

		HorizontalLayout toolbar = new HorizontalLayout();
		toolbar.setSpacing(true);
		toolbar.setMargin(new MarginInfo(false, false, false, true));
		toolbar.setWidth("150px");
		toolbar.setHeight("48px");
		toolbar.addStyleName("toolbar");
		content.addComponent(toolbar);

		Embedded load = new Embedded();
		load.setDescription("Load");
		load.setSource(new ThemeResource("icons/Load.png"));
		load.setWidth("32px");
		toolbar.addComponent(load);
		load.addClickListener(new MouseEvents.ClickListener() {
			@Override
			public void click(ClickEvent event) {
				masterTable.setContainerDataSource(createContainer());
			}
		});

		Embedded save = new Embedded();
		save.setDescription("Save");
		save.setSource(new ThemeResource("icons/Save.png"));
		save.setWidth("32px");
		toolbar.addComponent(save);
		save.addClickListener(new MouseEvents.ClickListener() {
			@Override
			public void click(ClickEvent event) {
				if (context != null && service != null) {
					IValueBean bean = context.getValueBean("master");
					IPerson person = (IPerson) bean.getValue();
					service.save(person);
					masterTable.setContainerDataSource(createContainer());
					masterTable.setValue(person);
				}
			}
		});

		Embedded add = new Embedded();
		add.setDescription("Add");
		add.setSource(new ThemeResource("icons/Add.png"));
		add.setWidth("32px");
		toolbar.addComponent(add);
		add.addClickListener(new MouseEvents.ClickListener() {
			@Override
			public void click(ClickEvent event) {
				if (context != null && service != null) {
					IPerson newEntity = service.createNew();
					IValueBean bean = context.getValueBean("master");
					bean.setValue(newEntity);
				}
			}
		});

		Label toolbarspanner = new Label();
		toolbar.addComponent(toolbarspanner);
		toolbar.setExpandRatio(toolbarspanner, 1.0f);

		mainLayout = new CssLayout();
		mainLayout.setSizeFull();
		addComponent(mainLayout);

		CssLayout spanner = new CssLayout();
		spanner.setSizeFull();
		addComponent(spanner);

		content.setExpandRatio(mainLayout, 0.5f);
		content.setExpandRatio(spanner, 0.5f);

		// put the instances into the cache
		uiaccesses.add(this);

		showUI(createDefaultView());
	}

	@Override
	public void showUI(YUiView yView) {
		if (yView == null) {
			return;
		}

		mainLayout.removeAllComponents();

		// render the view again
		context = null;
		try {
			VaadinRenderer renderer = new VaadinRenderer();
			context = renderer.render(mainLayout, yView, null);

			icePush.push();

		} catch (ContextException e) {
			logger.error("{}", e);
		}

		bind(context);

	}

	/**
	 * Binds the table to the model.<br>
	 * TODO will be extracted to UI model core if function available!
	 * 
	 * @param context
	 */
	private void bind(IViewContext context) {
		// unbind old
		if (masterTable != null) {
			masterTable.removeItemClickListener(this);
		}

		// bind new
		IWidgetPresentation<CssLayout> selectablePresentation = context
				.getPresentation("mastertable");
		if (selectablePresentation != null) {
			masterTable = (Table) selectablePresentation.getWidget()
					.getComponent(0);
			if (masterTable != null) {
				masterTable.setSelectable(true);
				masterTable.addItemClickListener(this);
				BeanItemContainer<IPerson> personContainer = createContainer();
				masterTable.setContainerDataSource(personContainer);
			}
		}

		if (itemId != null) {
			IValueBean bean = context.getValueBean("master");
			masterTable.setValue(itemId);
			bean.setValue(itemId);
		}
	}

	/**
	 * Creates the data container.
	 * 
	 * @return
	 */
	private BeanItemContainer<IPerson> createContainer() {
		BeanItemContainer<IPerson> personContainer = new BeanItemContainer<IPerson>(
				IPerson.class);
		if (service != null) {
			personContainer.addAll(service.getAll());
		}
		return personContainer;
	}

	protected YUiGridLayoutCellStyle createCellStyle(YUiGridLayout yGridLayout,
			YUiTextField yText1) {
		return factory.createGridLayoutCellStyle(yText1, yGridLayout);
	}

	/**
	 * Creates a new text field.
	 * 
	 * @param label
	 *            the label to show
	 * @return textField
	 */
	protected YUiTextField newText(String label) {
		YUiTextField field = factory.createTextField();
		if (label != null) {
			YDatadescription dtd = factory.createDatadescription();
			field.setDatadescription(dtd);
			dtd.setLabel(label);
		}
		return field;
	}

	@Override
	public void cleanup(CleanupEvent event) {
		uiaccesses.remove(this);
	}

	private YUiView createDefaultView() {
		YUiView yView = factory.createView();
		YUiGridLayout ymainLayout = factory.createGridLayout();
		yView.setContent(ymainLayout);
		applyLayout(ymainLayout, true);
		return yView;
	}

	/**
	 * Applies the layout settings. Horizontal or vertical.
	 * 
	 * @param ymainLayout
	 * @param horizontal
	 */
	private void applyLayout(YUiGridLayout ymainLayout, boolean horizontal) {
		if (horizontal) {
			ymainLayout.setColumns(2);
			ymainLayout.setCssClass(Reindeer.LAYOUT_BLUE);
		} else {
			ymainLayout.setColumns(1);
		}
		ymainLayout.setSpacing(true);
		ymainLayout.setFillHorizontal(true);
		ymainLayout.setFillVertical(true);
	}

	@Override
	public void itemClick(ItemClickEvent event) {
		itemId = (IPerson) event.getItemId();
		if (context != null) {
			IValueBean bean = context.getValueBean("master");
			bean.setValue(itemId);
		}
	}
}
