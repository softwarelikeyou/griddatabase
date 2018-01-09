package com.softwarelikeyou.viewcontroller.ercot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Window;

import com.softwarelikeyou.facade.RTLMPFacade;
import com.softwarelikeyou.viewcontroller.WebUtil;

public class LMPWindow extends Window implements AfterCompose {
	private static final long serialVersionUID = 1L;

	protected AnnotateDataBinder binder;

	protected Combobox displayCombobox;
	
	protected Datebox datebox;
	
	protected Combobox intervalCombobox;
	
	protected Combobox typeCombobox;

	private ListModelList settlementPointsModel = new BindingListModelList(new ArrayList<String>(), true);
	protected Combobox settlementPointsCombobox;
	
	protected static final String CENTER_PANEL = "lmpPanel";

	@SuppressWarnings("unchecked")
	public List<String> getSettlementPoints() {
		return (List<String>) settlementPointsModel.getInnerList();
	}
	
	public ListModelList getSettlementPointsModel() {
		return settlementPointsModel;
	}
	
	public void setSettlementPointsModel(ListModelList model) {
		this.settlementPointsModel = model;
	}
	
	private String formatDate(Date date) {
		String value = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			value = format.format(date);
		}
		catch (Exception e) {
			
		}
		return value;
	}
	
	public void onSelect$typeCombobox(Event event) {
		try {				
			List<String> list = RTLMPFacade.findUniqueSettlementPoints(String.valueOf(typeCombobox.getSelectedItem().getValue()));			
			if (list.size() > 0) {
		        settlementPointsCombobox.setSelectedIndex(-1);
				settlementPointsCombobox.getItems().clear();
				settlementPointsModel.clear();
		        settlementPointsModel.addAll(list);
				binder.loadAll();
			}
		}
		catch (Exception e) {
			try {
				e.printStackTrace();
				Messagebox.show("Could not retrieve data", "Error", Messagebox.OK, Messagebox.NONE);
			}
			catch( InterruptedException ex ) {
				ex.printStackTrace();
			}
		}	
	}
	
	public void onChange$typeCombobox(Event event) {
		try {				
			List<String> list = RTLMPFacade.findUniqueSettlementPoints(String.valueOf(typeCombobox.getSelectedItem().getValue()));			
			if (list.size() > 0) {
		        settlementPointsCombobox.setSelectedIndex(-1);
				settlementPointsCombobox.getItems().clear();
				settlementPointsModel.clear();
		        settlementPointsModel.addAll(list);
				binder.loadAll();
			}
		}
		catch (Exception e) {
			try {
				e.printStackTrace();
				Messagebox.show("Could not retrieve data", "Error", Messagebox.OK, Messagebox.NONE);
			}
			catch( InterruptedException ex ) {
				ex.printStackTrace();
			}
		}
	}
	
	private void updateSettlementPoints() {
		try {				
			List<String> list = RTLMPFacade.findUniqueSettlementPoints(String.valueOf(typeCombobox.getSelectedItem().getValue()));
			if (list.size() > 0) {
		        settlementPointsModel.addAll(list);
			}
		}
		catch (Exception e) {
			try {
				e.printStackTrace();
				Messagebox.show("Could not retrieve data", "Error", Messagebox.OK, Messagebox.NONE);
			}
			catch( InterruptedException ex ) {
				ex.printStackTrace();
			}
		}
	}
	
	public void onChange$displayCombobox(Event event) {
		if (settlementPointsCombobox.getSelectedItem() == null)
			return;
		if (displayCombobox.getSelectedItem().getValue().equals("Chart"))
			intervalCombobox.setDisabled(false);
		else
			intervalCombobox.setDisabled(true);

		send();
	}
	
	public void onChange$settlementPointsCombobox(Event event) {
		if (settlementPointsCombobox.getSelectedItem() == null)
			return;
		send();
	}
	
	public void onChange$datebox(Event event) {
		if (settlementPointsCombobox.getSelectedItem() == null)
			return;
		send();
	}
	
	public void onChange$intervalCombobox(Event event) {
		if (settlementPointsCombobox.getSelectedItem() == null)
			return;
		send();
	}
	
	private void send() {
		Desktop desktop = Executions.getCurrent().getDesktop();
		Component parent = WebUtil.getComponentById(desktop, CENTER_PANEL);
		if( parent == null )
			throw new IllegalArgumentException("Could not identify map panel");
		Panelchildren center = (Panelchildren) parent;
		center.getChildren().clear();
		Map<String, String> map = new HashMap<String, String>();
		map.put("interval", String.valueOf(intervalCombobox.getSelectedItem().getValue()));
		map.put("type", String.valueOf(typeCombobox.getSelectedItem().getValue()));
		map.put("settlementPoint", String.valueOf(settlementPointsCombobox.getSelectedItem().getValue()));
	    map.put("date", formatDate(datebox.getValue()));
	    if (String.valueOf(displayCombobox.getSelectedItem().getValue()).equals("Chart"))
		    Executions.createComponents("/ERCOT/lmp-chart-window.zul", center, map);
	    else
	    	Executions.createComponents("/ERCOT/lmp-detail-window.zul", center, map);
	}
	
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		binder = new AnnotateDataBinder(this);
		binder.loadAll();

		displayCombobox.setSelectedIndex(0);
		intervalCombobox.setSelectedIndex(0);
		typeCombobox.setSelectedIndex(0);
		updateSettlementPoints();
	}
}
