package com.softwarelikeyou.viewcontroller;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.Intbox;
import org.zkoss.zul.Paging;

import com.softwarelikeyou.model.entity.UserData;

public abstract class GenericAbstractListWindow extends GenericAbstractWindow {

	protected final static int DEFAULT_MAX_ITEMS_PER_PAGE = 25;
	protected final static int DEFAULT_ITEMS_PER_PAGE = 10;
	
	private int _maxItemsPerPage = DEFAULT_MAX_ITEMS_PER_PAGE;
	private int _itemsPerpage = DEFAULT_ITEMS_PER_PAGE;
	
	private static final long serialVersionUID = 1L;
	
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
		saveItemsPerPage(resultsPerPageKey, itemsPerPage);
		pager.setActivePage(0);
		pager.setPageSize(itemsPerPage.getValue());
	}	
	
	protected Integer loadItemsPerPage(String resultsPerPageKey, Intbox itemsPerPage, Paging pager, Integer maxItemsPerpage, Integer numberOfItemsPerPage) {
		this._maxItemsPerPage = maxItemsPerpage;
		this._itemsPerpage = numberOfItemsPerPage;
		return loadItemsPerPage(resultsPerPageKey, itemsPerPage,  pager);
	}
	
	private Integer loadItemsPerPage(String resultsPerPageKey, Intbox itemsPerPage, Paging pager) {
		Integer storedPerPage= 0;
		if (SessionUtil.getUserData(resultsPerPageKey) == null)
			storedPerPage = _itemsPerpage;
		else
			storedPerPage = (Integer.valueOf(SessionUtil.getUserData(resultsPerPageKey).getValue()));
		
		itemsPerPage.setValue((storedPerPage != null) ? storedPerPage : getDefaultItemsPerPage());
		
		setItemsPerPage(resultsPerPageKey, itemsPerPage, pager);
		return itemsPerPage.getValue();
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
	
	private void saveItemsPerPage(String resultsPerPageKey, Intbox itemsPerPage) { 
		UserData userData = new UserData();
		userData.setUserId(SessionUtil.getCurrentUser().getId());
		userData.setProperty(resultsPerPageKey);
		userData.setValue(itemsPerPage.getText());
		SessionUtil.setUserData(userData);
	}
}