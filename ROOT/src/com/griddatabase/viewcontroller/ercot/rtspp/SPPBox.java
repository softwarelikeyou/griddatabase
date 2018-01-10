package com.griddatabase.viewcontroller.ercot.rtspp;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Vbox;

import com.griddatabase.util.ZKUtil;

public class SPPBox extends Vbox implements AfterCompose {
	private static final long serialVersionUID = 1L;

	protected AnnotateDataBinder binder;

	protected Combobox displayCombobox;
		
	protected Combobox typeCombobox;
	
	protected static final String CENTER_PANEL = "sppPanel";
	
	public void onSelect$typeCombobox(Event event) {
		//send();
	}
	
	public void onChange$typeCombobox(Event event) {
		send();
	}
	
	public void onChange$displayCombobox(Event event) {
		send();
	}
	
	private void send() {
		Desktop desktop = Executions.getCurrent().getDesktop();
		Component parent = ZKUtil.getComponentById(desktop, CENTER_PANEL);
		if( parent == null )
			throw new IllegalArgumentException("Could not identify map panel");
		Panelchildren center = (Panelchildren) parent;
		center.getChildren().clear();
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", String.valueOf(typeCombobox.getSelectedItem().getValue()));
	    if (String.valueOf(displayCombobox.getSelectedItem().getValue()).equals("Chart"))
		    Executions.createComponents("/Portal/ERCOT/RTSPP/spp-chart-box.zul", center, map);
	    else
	    	Executions.createComponents("/Portal/ERCOT/RTSPP/spp-detail-box.zul", center, map);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		binder = new AnnotateDataBinder(this);
		binder.loadAll();

		displayCombobox.setSelectedIndex(0);
		typeCombobox.setSelectedIndex(0);
		send();
	}
}
