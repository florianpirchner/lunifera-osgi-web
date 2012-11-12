package org.vaadin.artur.icepush;

import javax.servlet.ServletContext;

import org.icepush.PushContext;
import org.vaadin.artur.icepush.client.ui.ICEPushRpc;
import org.vaadin.artur.icepush.client.ui.ICEPushState;

import com.vaadin.server.VaadinServiceSession;
import com.vaadin.server.VaadinServletService;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.UI;

/**
 * Server side component for the VICEPush widget.
 */
public class ICEPush extends AbstractComponent {

	private static String codeJavascriptLocation;

	private ICEPushRpc rpc = new ICEPushRpc() {

		public void push() {
			// No need to do anything. The only important thing is that the
			// event is sent
		}
	};

	public ICEPushRpc getRpc() {
		return rpc;
	}

	@Override
	public void beforeClientResponse(boolean initial) {
		super.beforeClientResponse(initial);
		getState().setCodeJavascriptLocation(codeJavascriptLocation);
	}

	@Override
	public ICEPushState getState() {
		return (ICEPushState) super.getState();
	}

	public void push() {
		UI app = getUI();
		if (app == null) {
			throw new RuntimeException(
					"Must be attached to an application to push");
		}

		// Push changes
		PushContext pushContext = getPushContext(app.getSession());
		if (pushContext == null) {
			throw new RuntimeException(
					"PushContext not initialized. Did you forget to use ICEPushServlet?");
		}
		pushContext.push(getState().getPushGroup());
	}

	public static synchronized PushContext getPushContext(
			VaadinServiceSession context) {
		if (context instanceof VaadinServiceSession) {
			VaadinServletService service = (VaadinServletService) context
					.getService();
			ServletContext servletContext = service.getServlet()
					.getServletContext();
			return (PushContext) servletContext.getAttribute(PushContext.class
					.getName());
		} else {
			throw new RuntimeException(
					"Could not find PushContext from session");
		}
	}

	public static void setCodeJavascriptLocation(String url) {
		codeJavascriptLocation = url;
	}

}
