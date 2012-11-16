/*
 * generated by Xtext
 */
package org.lunifera.web.vaadin.designstudy01.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.emf.ecp.ui.model.core.uimodel.UiModelFactory
import org.eclipse.emf.ecp.ui.model.core.datatypes.DatatypesFactory
import org.eclipse.emf.ecp.ui.model.core.uimodel.YUiView
import org.lunifera.web.vaadin.example.uimodelbridge.IUiAccess
import org.lunifera.web.vaadin.designstudy01.designstudy01.UiModel
import org.lunifera.web.vaadin.designstudy01.designstudy01.UiLayoutType
import org.eclipse.emf.ecp.ui.model.core.uimodel.^extension.UimodelExtensionFactory
import org.eclipse.emf.ecp.ui.model.core.uimodel.^extension.YUiGridLayout
import org.eclipse.emf.ecp.ui.model.core.uimodel.^extension.YUiAlignment
import org.lunifera.web.vaadin.designstudy01.designstudy01.UiTable
import org.lunifera.web.vaadin.designstudy01.designstudy01.UiTextField
import org.eclipse.emf.ecp.ui.model.core.uimodel.util.SimpleModelFactory
import org.lunifera.web.vaadin.designstudy01.designstudy01.UiDetailSize
import org.lunifera.web.vaadin.designstudy01.designstudy01.UiTextAlignment

class Designstudy01Generator implements IGenerator {
	
	SimpleModelFactory modelFactory = new SimpleModelFactory
	UiModelFactory factory = UiModelFactory::eINSTANCE
	UimodelExtensionFactory extfactory = UimodelExtensionFactory::eINSTANCE
	DatatypesFactory dtFactory = DatatypesFactory::eINSTANCE
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		for(IUiAccess access : org::lunifera::web::vaadin::example::uimodelbridge::UiModelBridgeUI::getUiAccesses){
			val YUiView yView = createView(resource)
			access.showUI(yView);
		}
	}
	
	def YUiView createView(Resource resource) { 
		
		val YUiView yView = factory.createYUiView
		val YUiGridLayout yContent = extfactory.createYUiGridLayout();
		yView.setContent(yContent);
		
		val UiModel model =	resource.contents.get(0) as UiModel
		val tiles = model.tiles
		if(tiles != null) {
			applyLayout(yContent, tiles.layout == UiLayoutType::HORIZONTAL);
			
			// prepare master
			//
			val masterPart = tiles.master
			val yMasterLayout = extfactory.createYUiGridLayout();
			yMasterLayout.columns = 1
			yMasterLayout.spacing=true
			yMasterLayout.fillHorizontal = false
			yMasterLayout.fillVertical = false
			yContent.elements += yMasterLayout
			
			val masterCellstyle = extfactory.createYUiGridLayoutCellStyle
			yContent.cellStyles += masterCellstyle
			masterCellstyle.alignment = YUiAlignment::FILL_FILL
			masterCellstyle.target = yMasterLayout
			
			if(tiles.layout == UiLayoutType::HORIZONTAL){
				modelFactory.createSpanInfo(masterCellstyle, 0, 0, 2, 3)
			} else {
				modelFactory.createSpanInfo(masterCellstyle, 0, 0, 2, 2)
				masterCellstyle.alignment = YUiAlignment::FILL_FILL
			}
			
			if(masterPart != null){
				val content = masterPart.content
				if(content instanceof UiTable){
					val yTable = extfactory.createYUiTable
					yTable.setId("mastertable")
					yMasterLayout.elements += yTable
					
					// style
					val yTableCellstyle = extfactory.createYUiGridLayoutCellStyle
					yTableCellstyle.target = yTable
					yTableCellstyle.alignment = YUiAlignment::FILL_FILL
					yMasterLayout.cellStyles += yTableCellstyle
				}
			}
			
			// prepare detail
			//
			val detailPart = tiles.detail
			val yDetailLayout = extfactory.createYUiGridLayout();
			yDetailLayout.setCssClass("detailPart");
			yDetailLayout.columns = 1
			yDetailLayout.margin=true
			yDetailLayout.spacing=true
			yDetailLayout.fillHorizontal = true
			yDetailLayout.fillVertical = false
			yContent.elements += yDetailLayout
			
			val detailCellstyle = extfactory.createYUiGridLayoutCellStyle
			yContent.cellStyles += detailCellstyle
			detailCellstyle.alignment = YUiAlignment::FILL_FILL
			detailCellstyle.target = yDetailLayout
			
			if(tiles.layout == UiLayoutType::HORIZONTAL){
					modelFactory.createSpanInfo(detailCellstyle, 3, 4, 3, 4)
			} else {
				if(detailPart.layout == UiDetailSize::SMALL){
					modelFactory.createSpanInfo(detailCellstyle, 0, 3, 0, 4)
				}else{
					modelFactory.createSpanInfo(detailCellstyle, 0, 3, 1, 4)
				}
			}
			
			if(detailPart != null){
				for(field : detailPart.content){
					if(field instanceof UiTextField){
						// datadescription
						val uiTextField = field as UiTextField
						val yDatadesc = dtFactory.createYDatadescription
						yDatadesc.label = uiTextField.caption
						
						// field
						val yTextField = extfactory.createYUiTextField
						yDetailLayout.elements += yTextField
						yTextField.datadescription=yDatadesc
						if(uiTextField.bindsTo != null){
							yTextField.bindsTo = uiTextField.bindsTo.name
						}
						// style
						val textCellstyle = extfactory.createYUiGridLayoutCellStyle
						textCellstyle.target = yTextField
						switch(detailPart.textAlign){
							case UiTextAlignment::LEFT:
								textCellstyle.alignment = YUiAlignment::TOP_LEFT
							case UiTextAlignment::CENTER:
								textCellstyle.alignment = YUiAlignment::TOP_CENTER
							case UiTextAlignment::RIGHT:
								textCellstyle.alignment = YUiAlignment::TOP_RIGHT
							case UiTextAlignment::FILL: 
								textCellstyle.alignment = YUiAlignment::TOP_FILL
						}
						yDetailLayout.cellStyles += textCellstyle
					}
				}
			}
		}
		return yView
	}
	
	def YUiView createDefaultView() {
		val YUiView yView = factory.createYUiView();
		
		return yView;
	}

	/**
	 * Applies the layout settings. Horizontal or vertical.
	 * 
	 * @param ymainLayout
	 * @param horizontal
	 */
	def void applyLayout(YUiGridLayout ymainLayout, boolean horizontal) {
		ymainLayout.setColumns(5);
		ymainLayout.setSpacing(true);
		ymainLayout.setFillHorizontal(true);
		ymainLayout.setFillVertical(true);
	}

}