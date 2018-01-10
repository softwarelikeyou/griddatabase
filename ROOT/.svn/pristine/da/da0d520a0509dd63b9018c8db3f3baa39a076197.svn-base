package com.griddatabase.viewcontroller.ercot.rtlmp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;

import com.griddatabase.model.cache.RTLMPCache;
import com.griddatabase.model.cache.RTSPPCache;
import com.griddatabase.model.event.EventConstants;
import com.softwarelikeyou.model.entity.rtlmp.RTLMP;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;

public class LMPDetailDiv extends Div implements AfterCompose  {
	private static final long serialVersionUID = 1L;
	
	protected final static int DEFAULT_MAX_ITEMS_PER_PAGE = 14;
	protected final static int DEFAULT_ITEMS_PER_PAGE = 14;
	
	private int _maxItemsPerPage = DEFAULT_MAX_ITEMS_PER_PAGE;
	private int _itemsPerpage = DEFAULT_ITEMS_PER_PAGE;
	
	protected AnnotateDataBinder binder;
	
	private static final String RESULTS_PER_PAGE_KEY = "usersPerPage";

	private String type;
	private String settlementPoint;
	
	protected Listbox itemListbox;
	protected Paging listPager;
	protected Intbox itemsPerPage;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private BindingListModelList model = new BindingListModelList(new ArrayList<LMP>(), false);
		
	private static String PACKAGE = "com.softwarelikeyou.model.entity";
	private StringBuffer lmpCanonical = new StringBuffer(PACKAGE + ".rtlmp");
	private StringBuffer sppCanonical = new StringBuffer(PACKAGE + ".rtspp");
	
	@SuppressWarnings("rawtypes")
	public ListModelList getModel() {
		return model;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
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
		}
		else {
			Messagebox.show("Settlement point cannot be empty", "Error", Messagebox.OK, Messagebox.NONE);
			return;
		}
		
    	lmpCanonical.append("." + type.toLowerCase() + ".RTLMP" + type + "5M");
    	sppCanonical.append("." + type.toLowerCase() + ".RTSPP" + type + "15M");
    	
		updateListModel();
		
		EventQueues.lookup(EventConstants.LMP_EVENT_QUEUE_NAME, EventQueues.APPLICATION, true).subscribe(
				new EventListener() {
				    public void onEvent(Event event) throws Exception {
		                String eventName = event.getName();
		                if(eventName.equals(EventConstants.LMP_UPDATE_EVENT_NAME)) {
		            		updateListModel();
		                }
					}
	        }
		);
	}	
	
	@SuppressWarnings("unchecked")
	protected void updateListModel() {
		model.clear();
	    model.addAll(getList());
	    binder.loadAll();
	}
	
	public List<LMP> getList() {
		Calendar start = Calendar.getInstance();
		start.setTime(new Date());
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		
		Calendar end = Calendar.getInstance();
		end.setTime(new Date());
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		
	    List<LMP> list = new ArrayList<LMP>();
		
		try {
			Date RTDTimestamp = new Date();
			Float total = 0f;
			LMP lmp = new LMP();
		    for (RTLMP rtlmp : RTLMPCache.getList(settlementPoint)) {
			//for (RTLMP rtlmp : RTLMPFacade.findByBetweenRTDTimestampDatesSettlementPoint(getLMPClass(lmpCanonical.toString()), start.getTime(), end.getTime(), settlementPoint)) {
		    	if (!RTDTimestamp.equals(rtlmp.getRTDTimestamp())) {
		    		RTDTimestamp = rtlmp.getRTDTimestamp();
	    			lmp = new LMP();
	    			lmp.setRTDTimestamp(rtlmp.getRTDTimestamp());
	    			lmp.setSettlementPrice(findSettlementPrice(rtlmp.getIntervalEnding()));
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
			e.printStackTrace();
			Messagebox.show("Could not retrieve data", "Error", Messagebox.OK, Messagebox.NONE);
		}
		
		//Collections.sort(list, new Comparator<LMP>() {
		//	public int compare(LMP l1, LMP l2) {
		//		return l2.getRTDTimestamp().compareTo(l1.getRTDTimestamp());
		//	}
		//});
		
		return list;
	}
	
	@SuppressWarnings("unused")
	private Float findSettlementPrice(Date date) {
		Float result = 0.0f;
		try {
			Integer minute = 0;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			
			switch (calendar.get(Calendar.MINUTE)) {
			case 0:
				minute = 0;
			    break;
			case 5:
				minute = 0;
				break;
			case 10:
				minute = 0;
				break;
			case 15:
				minute = 15;
				break;
			case 20:
				minute = 15;
				break;
			case 25:
				minute = 15;
				break;
			case 30:
				minute = 30;
				break;
			case 35:
				minute = 30;
				break;
			case 40:
				minute = 30;
				break;
			case 45:
				minute = 45;
				break;
			case 50:
				minute = 45;
				break;
			case 55:
				minute = 45;
				break;
			default:
				minute = 0;		
			}
			
			calendar.set(Calendar.MINUTE, minute);
			calendar.set(Calendar.SECOND, 0);
			
			//RTSPP rtspp = RTSPPFacade.findById(getSPPClass(sppCanonical.toString()), calendar.getTime(), settlementPoint);
			RTSPP rtspp = RTSPPCache.get(calendar.getTime(), settlementPoint);

			if (rtspp != null)
				result = rtspp.getSettlementPointPrice();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
	
	protected <T extends Object> List<T> getSubListForPage(Paging pager, Integer itemsPerPage_, List<T> fullList) {
		if (itemsPerPage_ == null )
			return null;
		int itemsPerPage = itemsPerPage_.intValue();
		if (fullList == null || fullList.isEmpty()) {
			pager.setTotalSize(0);
			return new ArrayList<T>();
		}

		pager.setTotalSize(fullList.size());
		
		int firstItem = pager.getActivePage() * itemsPerPage;
		int lastItem = firstItem + itemsPerPage;
		if (lastItem > fullList.size()) 
			lastItem = fullList.size();
		return fullList.subList(firstItem, lastItem);
	}
	
	protected void setItemsPerPage(String resultsPerPageKey, Intbox itemsPerPage, Paging pager, Integer maxItemsPerpage, Integer numberOfItemsPerPage) {
		this._maxItemsPerPage = maxItemsPerpage;
		this._itemsPerpage = numberOfItemsPerPage;
		setItemsPerPage(resultsPerPageKey, itemsPerPage,  pager);
	}
	
	private void setItemsPerPage(String resultsPerPageKey, Intbox itemsPerPage, Paging pager) {
		Integer assignValue = makeItemsPerPageSane(itemsPerPage);
		if (assignValue > getMaxItemsPerPage()) 
			itemsPerPage.setValue(getMaxItemsPerPage());
		itemsPerPage.setValue(assignValue);
		//saveItemsPerPage(resultsPerPageKey, itemsPerPage);
		pager.setActivePage(0);
		pager.setPageSize(itemsPerPage.getValue());
	}	
	
	protected Integer loadItemsPerPage(String resultsPerPageKey, Intbox itemsPerPage, Paging pager, Integer maxItemsPerpage, Integer numberOfItemsPerPage) {
		this._maxItemsPerPage = maxItemsPerpage;
		this._itemsPerpage = numberOfItemsPerPage;
		return loadItemsPerPage(resultsPerPageKey, itemsPerPage,  pager);
	}
	
	private Integer loadItemsPerPage(String resultsPerPageKey, Intbox itemsPerPage, Paging pager) {
		/*
		Integer storedPerPage= 0;
		if (SessionUtil.getUserData(resultsPerPageKey) == null)
			storedPerPage = _itemsPerpage;
		else
			storedPerPage = (Integer.valueOf(SessionUtil.getUserData(resultsPerPageKey).getValue()));
		
		itemsPerPage.setValue((storedPerPage != null) ? storedPerPage : getDefaultItemsPerPage());
		
		setItemsPerPage(resultsPerPageKey, itemsPerPage, pager);
		return itemsPerPage.getValue();
		*/
		return getDefaultItemsPerPage();
	}
	
	private Integer getDefaultItemsPerPage() {
		if (_itemsPerpage < 0 )
			return DEFAULT_ITEMS_PER_PAGE;
		return _itemsPerpage;
	}
	
	private Integer getMaxItemsPerPage() {
		if (_maxItemsPerPage < 0)
			return DEFAULT_MAX_ITEMS_PER_PAGE;
		else
			return _maxItemsPerPage;
	}
	
	private Integer makeItemsPerPageSane(Intbox itemsPerPage) {
		if (itemsPerPage == null || itemsPerPage.getValue() == null) return getDefaultItemsPerPage();
		if (itemsPerPage.getValue() > getMaxItemsPerPage()) return getMaxItemsPerPage();
		if (itemsPerPage.getValue() <= 0) return getDefaultItemsPerPage();
		return itemsPerPage.getValue();
	}
}
