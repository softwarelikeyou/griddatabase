package com.griddatabase.viewcontroller.ercot.forcast;

import java.util.ArrayList;
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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Vbox;

import com.griddatabase.util.ZKUtil;
import com.softwarelikeyou.facade.RTLMPFacade;

public class ForcastBox extends Vbox implements AfterCompose {
	private static final long serialVersionUID = 1L;

	protected AnnotateDataBinder binder;
	
	protected Combobox typeCombobox;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ListModelList settlementPointsModel = new BindingListModelList(new ArrayList<String>(), true);
	protected Combobox settlementPointsCombobox;
	
	protected static final String CENTER_PANEL = "forcastPanel";

	@SuppressWarnings("unchecked")
	public List<String> getSettlementPoints() {
		return (List<String>) settlementPointsModel.getInnerList();
	}
	
	@SuppressWarnings("rawtypes")
	public ListModelList getSettlementPointsModel() {
		return settlementPointsModel;
	}
	
	@SuppressWarnings("rawtypes")
	public void setSettlementPointsModel(ListModelList model) {
		this.settlementPointsModel = model;
	}
	
	@SuppressWarnings("unchecked")
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
		    e.printStackTrace();
			Messagebox.show("Could not retrieve data", "Error", Messagebox.OK, Messagebox.NONE);
		}	
	}
	
	@SuppressWarnings("unchecked")
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
			e.printStackTrace();
			Messagebox.show("Could not retrieve data", "Error", Messagebox.OK, Messagebox.NONE);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void updateSettlementPoints() {
		try {				
			List<String> list = RTLMPFacade.findUniqueSettlementPoints(String.valueOf(typeCombobox.getSelectedItem().getValue()));
			if (list.size() > 0) {
		        settlementPointsModel.addAll(list);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Messagebox.show("Could not retrieve data", "Error", Messagebox.OK, Messagebox.NONE);
		}
	}
	
	public void onChange$settlementPointsCombobox(Event event) {
		if (settlementPointsCombobox.getSelectedItem() == null)
			return;
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
		map.put("settlementPoint", String.valueOf(settlementPointsCombobox.getSelectedItem().getValue()));
		Executions.createComponents("/Portal/ERCOT/Forcast/forcast-chart-box.zul", center, map);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		binder = new AnnotateDataBinder(this);
		binder.loadAll();

		typeCombobox.setSelectedIndex(0);
		updateSettlementPoints();
	}
}
