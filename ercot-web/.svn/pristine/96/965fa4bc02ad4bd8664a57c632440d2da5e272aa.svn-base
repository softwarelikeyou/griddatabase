package com.softwarelikeyou.viewcontroller.ercot;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Center;
import org.zkoss.zul.Vbox;

import com.softwarelikeyou.viewcontroller.WebUtil;

public class Actions extends Vbox implements AfterCompose {
	static final long serialVersionUID = 1L;
	
	protected static final String CENTER_PANEL = "centerPanel";

	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	}
	
	public void onClick$lmp(Event event) {
		Desktop desktop = Executions.getCurrent().getDesktop();
		Component parent = WebUtil.getComponentById(desktop, CENTER_PANEL);
		if( parent == null )
			throw new IllegalArgumentException("Could not identify map panel");
		Center center = (Center) parent;
		center.getChildren().clear();
	    Executions.createComponents("/ERCOT/lmp-window.zul", center, null);
	}
	
	public void onClick$download(Event event) {
		Desktop desktop = Executions.getCurrent().getDesktop();
		Component parent = WebUtil.getComponentById(desktop, CENTER_PANEL);
		if( parent == null )
			throw new IllegalArgumentException("Could not identify map panel");
		Center center = (Center) parent;
		center.getChildren().clear();
	    Executions.createComponents("/ERCOT/download-window.zul", center, null);
	}
	
	public void onClick$lmphilo(Event event) {
		Desktop desktop = Executions.getCurrent().getDesktop();
		Component parent = WebUtil.getComponentById(desktop, CENTER_PANEL);
		if( parent == null )
			throw new IllegalArgumentException("Could not identify map panel");
		Center center = (Center) parent;
		center.getChildren().clear();
	    Executions.createComponents("/ERCOT/lmp-highlow-chart-window.zul", center, null);
	}
}
