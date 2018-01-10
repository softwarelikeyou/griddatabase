package com.griddatabase.viewcontroller.freemium.ercot;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;

import com.griddatabase.util.ZKUtil;

public class ReportsTabbox extends Tabbox implements AfterCompose {
	private static final long serialVersionUID = 1L;
	
	protected Tab NSPIN;
	protected Tabpanel NSPINPanel;
	protected Tab REGUP;
	protected Tabpanel REGUPPanel;
	protected Tab REGDN;
	protected Tabpanel REGDNPanel;
	protected Tab RRS;
	protected Tabpanel RRSPanel;
	
	protected Tab HB_HOUSTON;
	protected Tabpanel HB_HOUSTONPanel;
	protected Tab HB_NORTH;
	protected Tabpanel HB_NORTHPanel;
	protected Tab HB_SOUTH;
	protected Tabpanel HB_SOUTHPanel;
	protected Tab HB_WEST;
	protected Tabpanel HB_WESTPanel;

	protected Tab LZ_HOUSTON;
	protected Tabpanel LZ_HOUSTONPanel;
	protected Tab LZ_NORTH;
	protected Tabpanel LZ_NORTHPanel;
	protected Tab LZ_SOUTH;
	protected Tabpanel LZ_SOUTHPanel;
	protected Tab LZ_WEST;
	protected Tabpanel LZ_WESTPanel;
	
	public void onSelect$NSPIN(Event event) {
		clearPanels();
		send("NSPIN");
	}
	
	public void onSelect$REGUP(Event event) {
		clearPanels();
		send("REGUP");
	}
	
	public void onSelect$REGDN(Event event) {
		clearPanels();
		send("REGDN");
	}
	
	public void onSelect$RRS(Event event) {
		clearPanels();
		send("RRS");
	}
	
	public void onSelect$HB_HOUSTON(Event event) {
		clearPanels();
		send("HB_HOUSTON");
	}
	
	public void onSelect$HB_NORTH(Event event) {
		clearPanels();
		send("HB_NORTH");
	}
	
	public void onSelect$HB_SOUTH(Event event) {
		clearPanels();
		send("HB_SOUTH");
	}
	
	public void onSelect$HB_WEST(Event event) {
		clearPanels();
		send("HB_WEST");
	}
	
	public void onSelect$LZ_HOUSTON(Event event) {
		clearPanels();
		send("LZ_HOUSTON");
	}
	
	public void onSelect$LZ_NORTH(Event event) {
		clearPanels();
		send("LZ_NORTH");
	}
	
	public void onSelect$LZ_SOUTH(Event event) {
		clearPanels();
		send("LZ_SOUTH");
	}
	
	public void onSelect$LZ_WEST(Event event) {
		clearPanels();
		send("LZ_WEST");
	}
	
	private void send(String type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", type);
		Executions.createComponents("/Freemium/reports-window.zul", getPanel(type + "Panel"), map);
	}
	
	private Tabpanel getPanel(String type) {
	    Desktop desktop = Executions.getCurrent().getDesktop();
	    Component parent = ZKUtil.getComponentById(desktop, type);
	    if( parent == null )
		    throw new IllegalArgumentException("Could not identify map panel");
	    Tabpanel panel = (Tabpanel) parent;
	    panel.getChildren().clear();
	    return panel;
	}

	private void clearPanels() {
		NSPINPanel.getChildren().clear();
		REGUPPanel.getChildren().clear();
		REGDNPanel.getChildren().clear();
		RRSPanel.getChildren().clear();
		HB_HOUSTONPanel.getChildren().clear();
		HB_NORTHPanel.getChildren().clear();
		HB_SOUTHPanel.getChildren().clear();
		HB_WESTPanel.getChildren().clear();
		LZ_HOUSTONPanel.getChildren().clear();
		LZ_NORTHPanel.getChildren().clear();
		LZ_SOUTHPanel.getChildren().clear();
		LZ_WESTPanel.getChildren().clear();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);	
		
		send("HB_HOUSTON");
	}
}
