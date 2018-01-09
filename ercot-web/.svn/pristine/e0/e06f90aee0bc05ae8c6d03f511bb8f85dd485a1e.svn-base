package com.softwarelikeyou.viewcontroller.ercot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;

import com.softwarelikeyou.facade.ASCPCDailyFacade;
import com.softwarelikeyou.facade.ASCPCFacade;
import com.softwarelikeyou.facade.RTSPPDailyFacade;
import com.softwarelikeyou.facade.RTSPPFacade;
import com.softwarelikeyou.model.entity.Daily;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;
import com.softwarelikeyou.viewcontroller.BaseWindow;

public class DownloadWindow extends BaseWindow implements AfterCompose {
	private static final long serialVersionUID = 1L;

	private static DecimalFormat DECIMAL = new DecimalFormat("###0.00");
	private static SimpleDateFormat DASH_LONG_DATE = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss aa");
	private static SimpleDateFormat DASH_SHORT_DATE = new SimpleDateFormat("MM-dd-yyyy");
	
	protected Datebox startDatebox;
	protected Datebox endDatebox;
	protected Radiogroup type;
	
	protected AnnotateDataBinder binder;

	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	    binder = new AnnotateDataBinder(this);
	    binder.loadAll();
	}
	
	public void onClick$download(Event event) {	
		String selection = type.getSelectedItem().getId();
		
		String fileName = selection + DASH_SHORT_DATE.format(startDatebox.getValue()) + "to" + DASH_SHORT_DATE.format(endDatebox.getValue()) + ".csv";
		
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDatebox.getValue());

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDatebox.getValue());

		startCal.set(Calendar.HOUR_OF_DAY, 0);
		startCal.set(Calendar.MINUTE, 0);
		startCal.set(Calendar.SECOND, 0);
    	
		endCal.set(Calendar.HOUR_OF_DAY, 23);
		endCal.set(Calendar.MINUTE, 59);
		endCal.set(Calendar.SECOND, 59);
		
		List<Daily> dailys = null;
		StringBuffer rows = null;

		try {
		    switch (selection) {
		        case "dailyascpc":
		        	dailys = ASCPCDailyFacade.findByBetweenDates(startCal.getTime(), endCal.getTime());
					if (dailys != null) 
						rows = writeDaily(dailys);
			        break;
		        case "ascpc":
					List<ASCPC> ascpc = ASCPCFacade.findBetweenDates(startCal.getTime(), endCal.getTime());
					if (ascpc != null) 
						rows = writeASCPC(ascpc);
			        break;
		        case "dailyrtspp":
		        	dailys = new ArrayList<Daily>();
					for (Daily daily : RTSPPDailyFacade.findByBetweenDates(startCal.getTime(), endCal.getTime())) {
						if (daily.getName().startsWith("HB"))
							dailys.add(daily);
					}
					if (dailys.size() > 0) 
						rows = writeDaily(dailys);
			        break;
	 	        case "rtspp":
					List<RTSPP> rtspp = RTSPPFacade.findBetweenDates(startCal.getTime(), endCal.getTime());
					if (rtspp != null) 
						rows = writeRTSPP(rtspp);
			        break;
		    }   
		}
		catch (Exception e) {
			try {
				Messagebox.show("Could not retrieve " + type.getSelectedItem().getLabel(), "Error", Messagebox.OK, Messagebox.NONE);
			}
			catch( InterruptedException ex ) {
				ex.printStackTrace();
			}
		}
		
		if (rows == null) {
		    try {
				Messagebox.show("No " + type.getSelectedItem().getLabel() + " data was found", "Info", Messagebox.OK, Messagebox.NONE);
			}
			catch( InterruptedException ex ) {
				ex.printStackTrace();
			}
		    return;
		}
			
		try {
		    FileOutputStream fOut = new FileOutputStream(fileName);
		    fOut.write(rows.toString().getBytes());
		    fOut.flush();
		    fOut.close();
		    File file = new File(fileName);
	    	Filedownload.save(file, fileName);
		}
		catch (IOException e) {
		    try {
				Messagebox.show("Unable to download file", "Error", Messagebox.OK, Messagebox.NONE);
			}
			catch( InterruptedException ex ) {
				ex.printStackTrace();
			}
		    return;
		}
	}

	private StringBuffer writeDaily(final List<Daily> list) {
		if (list == null)
			return null;
		
		StringBuffer rows = new StringBuffer();
		rows.append("Pricing, Date, Low, Avg, High, AvgChg" + "\n");
		
		for (Daily row : list) {
		    rows.append(row.getName() + " [" + row.getType() + "]" + ",");
			rows.append(DASH_LONG_DATE.format(row.getIntervalDate()).toString() + ",");
			rows.append(DECIMAL.format(row.getLow()) + ",");
			rows.append(DECIMAL.format(row.getAverage()) + ",");
			rows.append(DECIMAL.format(row.getHigh()) + ",");
			rows.append(DECIMAL.format(row.getAverageChange()));
			rows.append("\n");
		}
		return rows;
	}
	
	private StringBuffer writeASCPC(final List<ASCPC> list) {
		if (list.size() == 0)
			return null;
		
		StringBuffer rows = new StringBuffer();
		rows.append("Date, NSPIN, REGDN, REGUP, RRS" + "\n");
		
		for (ASCPC row : list) {
			rows.append(DASH_LONG_DATE.format(row.getIntervalDate()).toString() + ",");
			rows.append(DECIMAL.format(row.getNSPIN()) + ",");
			rows.append(DECIMAL.format(row.getREGDN()) + ",");
			rows.append(DECIMAL.format(row.getREGUP()) + ",");
			rows.append(DECIMAL.format(row.getRRS()));
			rows.append("\n");
		}
		return rows;
	}
	
	private StringBuffer writeRTSPP(final List<RTSPP> list) {
		if (list == null)
			return null;
		
		StringBuffer rows = new StringBuffer();
		rows.append("Date, Type, Name, Price " + "\n");
		
		for (RTSPP row : list) {
			rows.append(DASH_LONG_DATE.format(row.getIntervalDate()).toString() + ",");
			rows.append(row.getSettlementPointType() + ",");
			rows.append(row.getSettlementPointName() + ",");
			rows.append(DECIMAL.format(row.getSettlementPointPrice()));
			rows.append("\n");
		}
		return rows;
	}
}
