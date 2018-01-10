package com.griddatabase.viewcontroller.ercot.rtspp;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
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

import com.griddatabase.model.cache.RTSPPCache;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;

public class SPPDetailBox extends Div implements AfterCompose  {
	private static final long serialVersionUID = 1L;

	protected final static int DEFAULT_MAX_ITEMS_PER_PAGE = 25;
	protected final static int DEFAULT_ITEMS_PER_PAGE = 10;
	
	private int _maxItemsPerPage = DEFAULT_MAX_ITEMS_PER_PAGE;
	private int _itemsPerpage = DEFAULT_ITEMS_PER_PAGE;
	
	protected AnnotateDataBinder binder;
	
	private static final String RESULTS_PER_PAGE_KEY = "usersPerPage";

	private String type;
	
	protected Listbox itemListbox;
	protected Paging listPager;
	protected Intbox itemsPerPage;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private BindingListModelList model = new BindingListModelList(new ArrayList<RTSPP>(), false);
	
	@SuppressWarnings("rawtypes")
	public ListModelList getModel() {
		return model;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		binder = new AnnotateDataBinder(this);
		binder.loadAll();
		
		loadItemsPerPage(RESULTS_PER_PAGE_KEY, itemsPerPage, listPager, DEFAULT_MAX_ITEMS_PER_PAGE, DEFAULT_ITEMS_PER_PAGE);
		
		if (Executions.getCurrent().getArg() != null) 
			type = String.valueOf(Executions.getCurrent().getArg().get("type"));
		
		updateListModel();
	}	
	
	@SuppressWarnings("unchecked")
	protected void updateListModel() {
		model.clear();
	    model.addAll(getList());
	    binder.loadAll();
	}
	
	public List<RTSPP> getList() {
	    List<RTSPP> list = new ArrayList<RTSPP>();
		try {
		    list = RTSPPCache.getListByType(type);
		}
		catch (Exception e) {
			e.printStackTrace();
			Messagebox.show("Could not retrieve data", "Error", Messagebox.OK, Messagebox.NONE);
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
