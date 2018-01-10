package com.griddatabase.viewcontroller.freemium.ercot;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zrss.RssBinder;
import org.zkoss.zrss.RssFeed;
import org.zkoss.zrss.RssFeedEntry;
import org.zkoss.zul.Window;

public class RSSWindow extends Window implements AfterCompose {
	private static final long serialVersionUID = 1L;
	protected AnnotateDataBinder binder;
	private RssFeed selected;
    private RssFeedEntry selectEntry;
 
    public RssFeed getSelectedFeed() {
        return selected;
    }
 
    public RssFeedEntry getSelectedEntry() {
        return selectEntry;
    }
 
    public void setSelectedEntry(RssFeedEntry selectedEntry) {
        this.selectEntry = selectedEntry;
    }

	@SuppressWarnings("deprecation")
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		binder = new AnnotateDataBinder(this);
		binder.loadAll();
		
        try {
			selected = new RssBinder().lookUpFeed("http://www.ercot.com/feed/show_rss.rxml?feed=allmispub");
		} 
        catch (Exception e) {
			e.printStackTrace();
		}
        selectEntry = selected.getFeedEntries().get(0);	
	}
}
