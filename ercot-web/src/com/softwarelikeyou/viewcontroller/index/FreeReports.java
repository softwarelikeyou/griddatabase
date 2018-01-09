package com.softwarelikeyou.viewcontroller.index;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import com.softwarelikeyou.exception.DailyException;
import com.softwarelikeyou.facade.ASCPCDailyFacade;
import com.softwarelikeyou.facade.RTSPPDailyFacade;
import com.softwarelikeyou.model.entity.Daily;

public class FreeReports extends Hlayout implements AfterCompose {
	private static final long serialVersionUID = 1L;

	private BindingListModelList model = new BindingListModelList(new ArrayList<Daily>(), false);

	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		
		model.clear();

		Calendar start = Calendar.getInstance();
		start.setTime(new Date());
		start.add(Calendar.DATE, -1);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		
		Calendar end = Calendar.getInstance();
		end.setTime(new Date());
		end.add(Calendar.DATE, -1);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		
		try {
			List<Daily> list = ASCPCDailyFacade.findByBetweenDates(start.getTime(), end.getTime());
		    if (list != null)
			    model.addAll(list);
		    list = new ArrayList<Daily>();
			for (Daily daily : RTSPPDailyFacade.findByBetweenDates(start.getTime(), end.getTime())) {
				if (daily.getName().startsWith("HB"))
					list.add(daily);
			}
			if (list.size() > 0)
		       model.addAll(list);
		}
		catch (DailyException e) {
			try {
				Messagebox.show("Could not retreive information", "Error", Messagebox.OK, Messagebox.NONE);
			}
			catch( InterruptedException ex ) {
				ex.printStackTrace();
			}
		}
	}

	public ListModelList getModel() {
		return model;
	}
}