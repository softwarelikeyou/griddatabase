package com.softwarelikeyou.viewcontroller.ercot;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;

import com.softwarelikeyou.facade.RTLMPFacade;
import com.softwarelikeyou.model.entity.rtlmp.RTLMP;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU5M;
import com.softwarelikeyou.model.entity.rtlmp.lccrn.RTLMPLCCRN5M;
import com.softwarelikeyou.model.entity.rtlmp.lz.RTLMPLZ5M;
import com.softwarelikeyou.model.entity.rtlmp.lzdc.RTLMPLZDC5M;
import com.softwarelikeyou.model.entity.rtlmp.pccrn.RTLMPPCCRN5M;
import com.softwarelikeyou.model.entity.rtlmp.pun.RTLMPPUN5M;
import com.softwarelikeyou.model.entity.rtlmp.rn.RTLMPRN5M;
import com.softwarelikeyou.util.Util;
import com.softwarelikeyou.viewcontroller.GenericAbstractListWindow;

public class LMPDetailWindow extends GenericAbstractListWindow implements AfterCompose  {
	private static final long serialVersionUID = 1L;

	protected AnnotateDataBinder binder;
	
	private static final String RESULTS_PER_PAGE_KEY = "usersPerPage";

	private String type;
	private String settlementPoint;
	
	protected Listbox itemListbox;
	protected Paging listPager;
	protected Intbox itemsPerPage;
	
	private BindingListModelList model = new BindingListModelList(new ArrayList<LMP>(), false);
	
	private Date date = new Date();
	
	private static Map<String, Class<? extends RTLMP>> classMap = new HashMap<String, Class<? extends RTLMP>>();
	
	static {
	    classMap.put("HU", RTLMPHU5M.class);	
	    classMap.put("LCCRN", RTLMPLCCRN5M.class);
	    classMap.put("LZ", RTLMPLZ5M.class);								
	    classMap.put("LZDC", RTLMPLZDC5M.class);							
	    classMap.put("PCCRN", RTLMPPCCRN5M.class);							
	    classMap.put("PUN", RTLMPPUN5M.class);							
	    classMap.put("RN", RTLMPRN5M.class);	
	}
	
	public ListModelList getModel() {
		return model;
	}
	
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		binder = new AnnotateDataBinder(this);
		binder.loadAll();
		
		loadItemsPerPage(RESULTS_PER_PAGE_KEY, itemsPerPage, listPager, DEFAULT_MAX_ITEMS_PER_PAGE, DEFAULT_ITEMS_PER_PAGE);
		
		if (Executions.getCurrent().getArg() != null) {
			type = String.valueOf(Executions.getCurrent().getArg().get("type"));
			settlementPoint = String.valueOf(Executions.getCurrent().getArg().get("settlementPoint"));
			try {
			    date = Util.stringToDate(String.valueOf(Executions.getCurrent().getArg().get("date")), "yyyy-MM-dd");
		    }
			catch (ParseException e) {
				try {
					e.printStackTrace();
					Messagebox.show("Problem reading date " + date, "Error", Messagebox.OK, Messagebox.NONE);
				}
				catch( InterruptedException ex ) {
					ex.printStackTrace();
				}
			}
		}
		
		updateListModel();
	}	
	
	protected void updateListModel() {
		model.clear();
	    model.addAll(getList());
	    binder.loadAll();
	}
	
	public List<LMP> getList() {
		Calendar start = Calendar.getInstance();
		start.setTime(date);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		
		Calendar end = Calendar.getInstance();
		end.setTime(date);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		
	    List<LMP> list = new ArrayList<LMP>();
		
		try {
			Date RTDTimestamp = new Date();
			Float total = 0f;
			LMP lmp = new LMP();
		    for (RTLMP rtlmp : RTLMPFacade.findByBetweenRTDTimestampDatesSettlementPoint(classMap.get(type), start.getTime(), end.getTime(), settlementPoint)) {
		    	if (!RTDTimestamp.equals(rtlmp.getRTDTimestamp())) {
		    		RTDTimestamp = rtlmp.getRTDTimestamp();
	    			lmp = new LMP();
	    			lmp.setRTDTimestamp(rtlmp.getRTDTimestamp());
		    	}
		    	total += rtlmp.getLMP();
		    	switch (rtlmp.getIntervalId()) {
		    		case 1:
		    			lmp.setInterval1(rtlmp.getLMP());
		    			break;
		    		case 2:
		    			lmp.setInterval2(rtlmp.getLMP());
		    			break;
		    		case 3:
		    			lmp.setInterval3(rtlmp.getLMP());
		    			break;
		    		case 4:
		    			lmp.setInterval4(rtlmp.getLMP());
		    			break;
		    		case 5:
		    			lmp.setInterval5(rtlmp.getLMP());
		    			break;
		    		case 6:
		    			lmp.setInterval6(rtlmp.getLMP());
		    			break;
		    		case 7:
		    			lmp.setInterval7(rtlmp.getLMP());
		    			break;
		    		case 8:
		    			lmp.setInterval8(rtlmp.getLMP());
		    			break;
		    		case 9:
		    			lmp.setInterval9(rtlmp.getLMP());
		    			break;
		    		case 10:
		    			lmp.setInterval10(rtlmp.getLMP());
		    			break;
		    		case 11:
		    			lmp.setInterval11(rtlmp.getLMP());
		    			lmp.setActualLMP(total/11);
		    			total = 0f;
		    			list.add(lmp);
		    			break;
		    	}
		    }
		}
		catch (Exception e) {
			try {
				Messagebox.show("Could not retrieve data", "Error", Messagebox.OK, Messagebox.NONE);
			}
			catch( InterruptedException ex ) {
				ex.printStackTrace();
			}
		}
		return list;
	}
	
	public void onOK$itemsPerPage( Event event ) {
		Events.echoEvent("onItemsPerPageChanged", this, null);
	}

	public void onChange$itemsPerPage( Event event ) {
		Events.echoEvent("onItemsPerPageChanged", this, null);
	}

	public void onItemsPerPageChanged(Event event) {
		setItemsPerPage(RESULTS_PER_PAGE_KEY, itemsPerPage, listPager, DEFAULT_MAX_ITEMS_PER_PAGE, DEFAULT_ITEMS_PER_PAGE);
		updateListModel();
		Events.echoEvent("onClearBusy", this, null);
	}
}
