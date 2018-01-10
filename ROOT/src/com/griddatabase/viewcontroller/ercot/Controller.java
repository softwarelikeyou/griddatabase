package com.griddatabase.viewcontroller.ercot;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericAutowireComposer;
import org.zkoss.zul.Div;

import com.griddatabase.util.ZKUtil;

@SuppressWarnings("rawtypes")
public class Controller extends GenericAutowireComposer {
	private static final long serialVersionUID = 1L;

	protected static final String DIV = "east";

	public void onDownload(Event event) {
		Desktop desktop = Executions.getCurrent().getDesktop();
		Component parent = ZKUtil.getComponentById(desktop, DIV);
		if( parent == null )
			throw new IllegalArgumentException("Could not identify parent component");
		Div east = (Div) parent;
		east.getChildren().clear();
	    Executions.createComponents("/Portal/ERCOT/Download/download-center.zul", east, null);
	}
	
	public void onForecast(Event event) {
		Desktop desktop = Executions.getCurrent().getDesktop();
		Component parent = ZKUtil.getComponentById(desktop, DIV);
		if( parent == null )
			throw new IllegalArgumentException("Could not identify parent component");
		Div east = (Div) parent;
		east.getChildren().clear();
	    Executions.createComponents("/Portal/ERCOT/Forcast/forcast-box.zul", east, null);
	}
	
	public void onMap(Event event) {

	}
	
	public void onLMP(Event event) {
		Desktop desktop = Executions.getCurrent().getDesktop();
		Component parent = ZKUtil.getComponentById(desktop, DIV);
		if( parent == null )
			throw new IllegalArgumentException("Could not identify parent component");
		Div east = (Div) parent;
		east.getChildren().clear();
	    Executions.createComponents("/Portal/ERCOT/RTLMP/lmp-box.zul", east, null);
	}
	
	public void onSPP(Event event) {
		Desktop desktop = Executions.getCurrent().getDesktop();
		Component parent = ZKUtil.getComponentById(desktop, DIV);
		if( parent == null )
			throw new IllegalArgumentException("Could not identify parent component");
		Div east = (Div) parent;
		east.getChildren().clear();
	    Executions.createComponents("/Portal/ERCOT/RTSPP/spp-box.zul", east, null);
	}
}
