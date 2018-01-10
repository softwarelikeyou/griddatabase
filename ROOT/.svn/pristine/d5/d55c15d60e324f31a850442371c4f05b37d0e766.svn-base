package com.griddatabase.viewcontroller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Iframe;

import com.griddatabase.util.ZKUtil;

public class Header extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;

	protected AnnotateDataBinder binder;

	@SuppressWarnings("deprecation")
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		binder = new AnnotateDataBinder(this);
		binder.loadAll();
	}
	
	public void onClick$login(Event event) {
		Desktop desktop = Executions.getCurrent().getDesktop();
		Component parent = ZKUtil.getComponentById(desktop, "right");
		if( parent == null )
			throw new IllegalArgumentException("Could not identify map panel");
		Div right = (Div) parent;
		right.getChildren().clear();
		Iframe iframe = new Iframe();
		iframe.setSrc("/login.zul");
		iframe.setWidth("100%");
		iframe.setHeight("100%");
		right.appendChild(iframe);
		binder.loadAll();
	}

}
