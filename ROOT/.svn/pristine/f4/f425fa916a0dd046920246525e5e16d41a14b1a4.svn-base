package com.griddatabase.viewcontroller.ercot.rtspp.crr;

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
import org.zkoss.zul.Vbox;

import com.griddatabase.util.ZKUtil;
import com.softwarelikeyou.facade.RTSPPFacade;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU1H;

public class SPPBox_seperation extends Vbox implements AfterCompose {
	private static final long serialVersionUID = 1L;

	protected AnnotateDataBinder binder;
	
	protected Datebox datebox;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ListModelList settlementPointsModel = new BindingListModelList(new ArrayList<String>(), true);
	protected Combobox settlementPointsComboboxA;
	protected Combobox settlementPointsComboboxB;
	
	protected static final String CENTER_PANEL = "sppPanel";

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
	
	@SuppressWarnings("unchecked")
	private void updateSettlementPoints() {
		try {				
			List<String> list = RTSPPFacade.findUniqueSettlementPoints(RTSPPHU1H.class);
			if (list.size() > 0) {
		        settlementPointsModel.addAll(list);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Messagebox.show("Could not retrieve data", "Error", Messagebox.OK, Messagebox.NONE);
		}
	}
	
	public void onChange$settlementPointsComboboxA(Event event) {
		if (settlementPointsComboboxB.getSelectedItem() == null)
			return;
		send();
	}
	
	public void onChange$settlementPointsComboboxB(Event event) {
		if (settlementPointsComboboxA.getSelectedItem() == null)
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
		map.put("settlementPointA", String.valueOf(settlementPointsComboboxA.getSelectedItem().getValue()));
		map.put("settlementPointB", String.valueOf(settlementPointsComboboxB.getSelectedItem().getValue()));	    
		map.put("date", formatDate(datebox.getValue()));
		Executions.createComponents("/ERCOT/RTSPP/spp-chart-box.zul", center, map);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		binder = new AnnotateDataBinder(this);
		binder.loadAll();

		updateSettlementPoints();
	}
}
