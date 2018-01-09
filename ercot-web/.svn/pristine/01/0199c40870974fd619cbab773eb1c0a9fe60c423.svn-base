package com.softwarelikeyou.viewcontroller.ercot;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.XYDataset;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.softwarelikeyou.exception.RTLMPException;
import com.softwarelikeyou.facade.RTLMPFacade;
import com.softwarelikeyou.model.entity.rtlmp.RTLMP;
import com.softwarelikeyou.util.Util;

public class LMPChartWindow extends Window implements AfterCompose  {
	private static final long serialVersionUID = 1L;

	protected AnnotateDataBinder binder;
	
	private Date date = new Date();
	private String interval;
	private String type;
	private String settlementPoint;
	private String canonical;
	private static String PACKAGE = "com.softwarelikeyou.model.entity.rtlmp";
	
	protected Image image;
	
	@SuppressWarnings("unchecked")
	private Class<? extends RTLMP> getClass(String value) throws ClassNotFoundException {
		return (Class<? extends RTLMP>) Class.forName(value);
	}

	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		
		binder = new AnnotateDataBinder(this);
		binder.loadAll();
	
		if (Executions.getCurrent().getArg() != null) {
			type = String.valueOf(Executions.getCurrent().getArg().get("type"));
			interval = String.valueOf(Executions.getCurrent().getArg().get("interval"));
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
		
		try {
	    	canonical = PACKAGE + "." + type.toLowerCase() + ".RTLMP" + type + interval;
			JFreeChart chart = getChart(getDataSet(RTLMPFacade.findByBetweenIntervalEndingDatesSettlementPoint(getClass(canonical), start.getTime(), end.getTime(), settlementPoint)));
			image.setContent(getImage(chart));
			
		    //image.setContent(hiloChart());

		}
		catch (RTLMPException e) {
			try {
				e.printStackTrace();
				Messagebox.show("Could not retrieve data", "Error", Messagebox.OK, Messagebox.NONE);
			}
			catch( InterruptedException ex ) {
				ex.printStackTrace();
			}
		}
		catch (IOException e) {
			try {
				e.printStackTrace();
				Messagebox.show("Could not create chart", "Error", Messagebox.OK, Messagebox.NONE);
			}
			catch( InterruptedException ex ) {
				ex.printStackTrace();
			}
		}
		catch (Exception e) {
			try {
				e.printStackTrace();
				Messagebox.show(e.getCause().getMessage(), "Error", Messagebox.OK, Messagebox.NONE);
			}
			catch( InterruptedException ex ) {
				ex.printStackTrace();
			}
		}
	}
	
	private Integer getOpen(Set<Integer> set) {
		Integer value = 9999;
		for (Integer i : set) {
			if (i < value)
			    value = i;
		}
		return value;
	}
	
	private Integer getClose(Set<Integer> set) {
		Integer value = -9999;
		for (Integer i : set) {
			if (i > value)
			    value = i;
		}
		return value;
	}
	
	private float getLow(Map<Integer, Float> map) {
		float value = 9999.9f;
		for (Integer id : map.keySet()) {
    		if (map.get(id) < value)
    			value = map.get(id);
		}
		return value;
	}
	
	private float getHigh(Map<Integer, Float> map) {
		float value = -9999.9f;
		for (Integer id : map.keySet()) {
    		if (map.get(id) > value)
    			value = map.get(id);
		}
		return value;
	}
	
	private AImage getImage(JFreeChart chart) throws IOException {
		BufferedImage bi = chart.createBufferedImage(1050, 500, BufferedImage.TRANSLUCENT , null);
		byte[] bytes = EncoderUtil.encode(bi, ImageFormat.PNG, true);
		return new AImage(settlementPoint, bytes);
	}
	
	private long intervalConv(String interval) {
		long results = 5L;
		switch (interval) {
		    case "5M":
		    	results = 5;
		    	break;
		    case "15M":
		    	results = 15;
		    	break;
		    case "30M":
		    	results = 30;
		    	break;
		    case "1H":
		    	results = 60;
		    	break;
		}
		return results * 60 * 1000L;
	}
	
	private JFreeChart getChart(DefaultHighLowDataset dataSet) {
		JFreeChart chart = ChartFactory.createHighLowChart("High/Low/Moving Avg. \n " + settlementPoint + " - " + interval, "Time", "Price", dataSet, true);
        DateAxis axis = (DateAxis) chart.getXYPlot().getDomainAxis();
        axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        XYDataset dataset2 = MovingAverage.createMovingAverage(dataSet, "AVG",  intervalConv(interval), 0L);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, new StandardXYItemRenderer());
		return chart;
	}
	
	private DefaultHighLowDataset getDataSet(List<RTLMP> rtlmps) {
		Map<Date, Map<Integer, Float>> map = new TreeMap<Date, Map<Integer, Float>>();
	    
		for (RTLMP rtlmp : rtlmps) {
			if (map.containsKey(rtlmp.getIntervalEnding()))
				map.get(rtlmp.getIntervalEnding()).put(rtlmp.getIntervalId(), rtlmp.getLMP());
			else {
			    Map<Integer, Float> values = new HashMap<Integer, Float>();
				values.put(rtlmp.getIntervalId(), rtlmp.getLMP());
				map.put(rtlmp.getIntervalEnding(), values);
			}
	    }	
	    
		int size = map.size();
        Date[] date = new Date[size];
        double[] high = new double[size];
        double[] low = new double[size];
        double[] open = new double[size];
        double[] close = new double[size];
        double[] volume = new double[size];
        
        int i = 0;
        
	    SortedSet<Date> keys = new TreeSet<Date>(map.keySet());
	    for (Date key : keys) {
	    	date[i] = key;
	    	high[i] = map.get(key).get(getOpen(map.get(key).keySet()));
	    	low[i] = getLow(map.get(key));
	    	close[i] = map.get(key).get(getClose(map.get(key).keySet()));
	    	high[i] = getHigh(map.get(key));
	    	volume[i] = map.get(key).size();
	    	i++;
	    }
	    
	    return new DefaultHighLowDataset("LMP ", date, high, low, open, close, volume);
	}
}
