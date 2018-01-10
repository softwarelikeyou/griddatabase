package com.griddatabase.viewcontroller.user;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Components;
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

import com.griddatabase.model.entity.User;
import com.griddatabase.model.entity.UserData;
import com.griddatabase.model.event.EventConstants;
import com.griddatabase.model.facade.UserFacade;
import com.griddatabase.util.ZKUtil;
import com.softwarelikeyou.exception.UserException;

public class ListDiv extends Div implements AfterCompose  {
	private static final long serialVersionUID = 1L;

	protected AnnotateDataBinder binder;
	
	private static final String RESULTS_PER_PAGE_KEY = "usersPerPage";

	protected final static int DEFAULT_MAX_ITEMS_PER_PAGE = 25;
	protected final static int DEFAULT_ITEMS_PER_PAGE = 10;
	
	private int _maxItemsPerPage = DEFAULT_MAX_ITEMS_PER_PAGE;
	private int _itemsPerpage = DEFAULT_ITEMS_PER_PAGE;

	protected Listbox itemListbox;
	protected Paging listPager;
	protected Intbox itemsPerPage;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private BindingListModelList usersListModel = new BindingListModelList(new ArrayList<User>(), false);
	
	@SuppressWarnings("rawtypes")
	public ListModelList getUsersModel() {
		return usersListModel;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);

		binder = new AnnotateDataBinder();
		binder.loadAll();
		
		loadItemsPerPage(RESULTS_PER_PAGE_KEY, itemsPerPage, listPager, DEFAULT_MAX_ITEMS_PER_PAGE, DEFAULT_ITEMS_PER_PAGE);
		
		updateUsersListModel();
		
		EventQueues.lookup(EventConstants.USER_EVENT_QUEUE_NAME, EventQueues.APPLICATION, true).subscribe(
		    new EventListener() {
		        public void onEvent(Event event) throws Exception {
                    String eventName = event.getName();
                    if(eventName.equals(EventConstants.USER_CREATE_EVENT_NAME))
					    onUserCreated(event);
					else if(eventName.equals(EventConstants.USER_UPDATE_EVENT_NAME))
						onUserUpdated(event);
					else if(eventName.equals(EventConstants.USER_DELETE_EVENT_NAME) )
						onUserDeleted(event);
					else if(eventName.equals(EventConstants.USERDATA_UPDATE_EVENT_NAME) )
						onUserDataUpdated(event);
				}
			}
		);
	}
	
	@SuppressWarnings("unchecked")
	protected void updateUsersListModel() {
		usersListModel.clear();
	    usersListModel.addAll(getFullUsersList());
	}
	
	protected List<User> getFullUsersList() {
		List<User> users = new ArrayList<User>();
		try {
		    users = UserFacade.findAll();
		    if (users == null)
				Messagebox.show("Could not retrieve users", "Error", Messagebox.OK, Messagebox.NONE);
		}
		catch (UserException e) {
			Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.NONE);
		}
		return users;
	}
	
	@SuppressWarnings("unchecked")
	protected void onUserCreated(Event event) {
		User user = (User) event.getData();
		if (!usersListModel.contains(user)) {
			usersListModel.add(user);
			binder.loadAll();
		}
	}

	@SuppressWarnings("unchecked")
	protected void onUserUpdated(Event event) {
		User user = (User) event.getData();
		if (usersListModel.contains(user)) {
			usersListModel.set(usersListModel.indexOf(user), user);
			binder.loadAll();
		}
	}

	protected void onUserDeleted(Event event) {
		User user = (User) event.getData();
		if (usersListModel.contains(user)) {
			usersListModel.remove(user);
			binder.loadAll();
		}
	}
	
	@SuppressWarnings("unused")
	protected void onUserDataUpdated(Event event) {
		UserData userData = (UserData) event.getData();
	}
	
	public void onClick$disableButton(Event event) {
		Events.postEvent(new Event("onDisableSetup", null, (User) ZKUtil.getRowBindingData(event)));
	}
	
	public void onClick$enableButton(Event event) {
		Events.postEvent(new Event("onEnableSetup", null, (User) ZKUtil.getRowBindingData(event)));
	}
	
	public void onOK$itemsPerPage( Event event ) {
		Events.echoEvent("onItemsPerPageChanged", this, null);
	}

	public void onChange$itemsPerPage( Event event ) {
		Events.echoEvent("onItemsPerPageChanged", this, null);
	}

	public void onItemsPerPageChanged(Event event) {
		setItemsPerPage(RESULTS_PER_PAGE_KEY, itemsPerPage, listPager, DEFAULT_MAX_ITEMS_PER_PAGE, DEFAULT_ITEMS_PER_PAGE);
		updateUsersListModel();
		Events.echoEvent("onClearBusy", this, null);
	}
	
	public void onClick$resetButton(Event event) {
		Events.postEvent(new Event("onResetSetup", null, (User) ZKUtil.getRowBindingData(event)));
	}
	
	public void onClick$typeChangeButton(Event event) {
		Events.postEvent(new Event("onTypeChangeSetup", null, (User) ZKUtil.getRowBindingData(event)));
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
