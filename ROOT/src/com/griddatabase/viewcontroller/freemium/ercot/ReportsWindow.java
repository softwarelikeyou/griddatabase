package com.griddatabase.viewcontroller.freemium.ercot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.softwarelikeyou.exception.ASCPCException;
import com.softwarelikeyou.exception.DailyException;
import com.softwarelikeyou.exception.RTSPPException;
import com.softwarelikeyou.facade.ASCPCDailyFacade;
import com.softwarelikeyou.facade.ASCPCFacade;
import com.softwarelikeyou.facade.RTSPPDailyFacade;
import com.softwarelikeyou.facade.RTSPPFacade;
import com.softwarelikeyou.model.entity.Daily;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU15M;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ15M;

public class ReportsWindow extends Window implements AfterCompose {
	private static final long serialVersionUID = 1L;

	protected AnnotateDataBinder binder;

	protected String type;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private BindingListModelList model = new BindingListModelList(new ArrayList<Daily>(), false);
	
	protected Datebox startDate;
	protected Datebox endDate;
	
	protected Listbox listBox;
	
	protected Combobox displayCombobox;
	
	protected Image chart;
	
	private JFreeChart jfChart;
	
	private Calendar start = Calendar.getInstance();
	private Calendar end = Calendar.getInstance();

	@SuppressWarnings({ "deprecation" })
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		binder = new AnnotateDataBinder(this);
		binder.loadAll();
		
		if (Executions.getCurrent().getArg().get("type") != null) {
			type = String.valueOf(Executions.getCurrent().getArg().get("type"));
			binder.bindBean("type", type);
		}
		else {
			Messagebox.show("Type cannot be empty", "Error", Messagebox.OK, Messagebox.NONE);
			return;
		}
				
		start.setTime(startDate.getValue());
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		
		end.setTime(endDate.getValue());
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		
		updateModel();
	}

	public String getType() {
		return type;
	}
	
	@SuppressWarnings("rawtypes")
	public ListModelList getModel() {
		return model;
	}
	
	public void onChange$displayCombobox(Event event) {
	    if (String.valueOf(displayCombobox.getSelectedItem().getValue()).equals("Chart")) {
	    	listBox.setVisible(false);
	    	chart.setVisible(true);
	    }
	    else {
	    	listBox.setVisible(true);
	    	chart.setVisible(false);
	    }
	}
	
	public void onChange$startDate(Event event) {
		start.setTime(startDate.getValue());
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		
		end.setTime(endDate.getValue());
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		updateModel();
	}
	
	public void onChange$endDate(Event event) {
		start.setTime(startDate.getValue());
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		
		end.setTime(endDate.getValue());
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		updateModel();
	}
	
	private AImage getImage(JFreeChart chart) throws IOException {
		BufferedImage bi = chart.createBufferedImage(1000, 440, BufferedImage.TRANSLUCENT , null);
		byte[] bytes = EncoderUtil.encode(bi, ImageFormat.PNG, true);
		return new AImage(type, bytes);
	}
	
	@SuppressWarnings("unchecked")
	private XYDataset getDataSet(List<?> list, String type) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Map<Date, Float> map = new TreeMap<Date, Float>();
		
		if (type.startsWith("HB")) {
			for (RTSPP rtspp : (List<RTSPP>) list) 
				map.put(rtspp.getIntervalDate(), rtspp.getSettlementPointPrice());
		}
		else if (type.startsWith("LZ")) {
			for (RTSPP rtspp : (List<RTSPP>) list) 
				map.put(rtspp.getIntervalDate(), rtspp.getSettlementPointPrice());
		}
		else {
		    for (ASCPC ascpc : (List<ASCPC>) list) {
			    Field field = ascpc.getClass().getDeclaredField(type);
			    field.setAccessible(true);
			    map.put(ascpc.getIntervalDate(), field.getFloat(ascpc));
		    }
		}
		
		TimeSeriesCollection dataSet = new TimeSeriesCollection();
		TimeSeries series = new TimeSeries("Price");
		for (Date key : map.keySet())
			series.addOrUpdate(new Hour(key), map.get(key));
		dataSet.addSeries(series);
		return dataSet;
	}
	
	private JFreeChart getChart(XYDataset dataSet) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart("", "", type, dataSet, false, false, false);
        DateAxis axis = (DateAxis) chart.getXYPlot().getDomainAxis();
        axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        chart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(1.2f));
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

		return chart;
	}
	
	private List<Daily> getDailys(String type) throws DailyException {
		if (type.startsWith("HB") || type.startsWith("LZ"))
			return RTSPPDailyFacade.findByBetweenDatesName(start.getTime(), end.getTime(), type);
		
		return ASCPCDailyFacade.findByBetweenDatesName(start.getTime(), end.getTime(), type);		
	}
	
	private List<?> getData(String type) throws ASCPCException, RTSPPException {
		if (type.startsWith("LZ"))
			return RTSPPFacade.findByIntervalDatesSettlementPoint(RTSPPLZ15M.class, start.getTime(), end.getTime(), type);
		else if (type.startsWith("HB")) 
			return RTSPPFacade.findByIntervalDatesSettlementPoint(RTSPPHU15M.class, start.getTime(), end.getTime(), type);
		
		return ASCPCFacade.findBetweenDates(start.getTime(), end.getTime());

	}
	
	@SuppressWarnings("unchecked")
	private void updateModel() {
		model.clear();

		try {
			List<Daily> list = getDailys(type);
			if (list.size() > 0) {
				Collections.sort(list, new Comparator<Daily>() {
					public int compare(Daily l1, Daily l2) {
						return l2.getIntervalDate().compareTo(l1.getIntervalDate());
					}
				});
				model.addAll(list);
			}
			
			jfChart = getChart(getDataSet(getData(type),type));
			chart.setContent(getImage(jfChart));
		}
		catch (DailyException e) {
		    Messagebox.show("Could not retreive information", "Error", Messagebox.OK, Messagebox.NONE);
			e.printStackTrace();
		}
		catch (ASCPCException e) {
		    Messagebox.show("Could not retreive information", "Error", Messagebox.OK, Messagebox.NONE);
			e.printStackTrace();
		}
		catch (RTSPPException e) {
		    Messagebox.show("Could not retreive information", "Error", Messagebox.OK, Messagebox.NONE);
			e.printStackTrace();
		}	
		catch (IOException e) {
		    Messagebox.show("Could not retreive information", "Error", Messagebox.OK, Messagebox.NONE);
			e.printStackTrace();
		} 
		catch (NoSuchFieldException e) {
		    Messagebox.show("Could not retreive information", "Error", Messagebox.OK, Messagebox.NONE);
			e.printStackTrace();
		} 
		catch (SecurityException e) {
		    Messagebox.show("Could not retreive information", "Error", Messagebox.OK, Messagebox.NONE);
			e.printStackTrace();
		} 
		catch (IllegalArgumentException e) {
		    Messagebox.show("Could not retreive information", "Error", Messagebox.OK, Messagebox.NONE);
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
		    Messagebox.show("Could not retreive information", "Error", Messagebox.OK, Messagebox.NONE);
			e.printStackTrace();
		}
	}
}
