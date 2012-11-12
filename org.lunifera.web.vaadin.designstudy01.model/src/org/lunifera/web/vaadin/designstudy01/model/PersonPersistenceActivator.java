package org.lunifera.web.vaadin.designstudy01.model;

import javax.persistence.EntityManagerFactory;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

public class PersonPersistenceActivator implements BundleActivator {

	public static final String PERSISTENCE_UNIT = "org.lunifera.web.vaadin.designstudy01";

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		PersonPersistenceActivator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		PersonPersistenceActivator.context = null;
	}

	public static EntityManagerFactory getEMF() {
		return lookupEMF(PERSISTENCE_UNIT);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static EntityManagerFactory lookupEMF(String unitName) {
		if(context == null){
			return null;
		}
		ServiceReference[] refs = null;
		try {
			refs = context.getServiceReferences(
					EntityManagerFactory.class.getName(),
					String.format("(osgi.unit.name=%s)", unitName));
		} catch (InvalidSyntaxException isEx) {
			throw new RuntimeException("Filter error", isEx);
		}
		return (refs == null) ? null : (EntityManagerFactory) context
				.getService(refs[0]);

	}

}
