
package org.lunifera.web.vaadin.designstudy01;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class Designstudy01StandaloneSetup extends Designstudy01StandaloneSetupGenerated{

	public static void doSetup() {
		new Designstudy01StandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

