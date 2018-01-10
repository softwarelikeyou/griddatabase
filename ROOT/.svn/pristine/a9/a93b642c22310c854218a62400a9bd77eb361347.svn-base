package com.griddatabase.viewcontroller.ercot.rtlmp;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Area;
import org.zkoss.zul.Chart;
import org.zkoss.zul.Div;
import org.zkoss.zul.HiLoModel;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleHiLoModel;
import org.zkoss.zul.Timer;
import org.zkoss.zul.event.ChartAreaListener;

import com.griddatabase.model.cache.RTLMPCache;
import com.griddatabase.model.cache.RTSPPCache;
import com.softwarelikeyou.exception.RTLMPException;
import com.softwarelikeyou.model.entity.rtlmp.RTLMP;
import com.softwarelikeyou.util.Util;

public class LMPChartDiv extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;

	private static String PACKAGE = "com.softwarelikeyou.model.entity";

	protected AnnotateDataBinder binder;
	
	private String type;
	private String settlementPoint;
	private StringBuffer lmpCanonical = new StringBuffer(PACKAGE + ".rtlmp");
	private StringBuffer sppCanonical = new StringBuffer(PACKAGE + ".rtspp");

	protected Chart lmpChart;
	private HiLoModel model = new SimpleHiLoModel();
	private LMPChartEngine engine = new LMPChartEngine();
	
	protected Timer timer;
	
	private Map<Date, Map<Integer, Float>> map = new TreeMap<Date, Map<Integer, Float>>();
	
	public void onTimer$timer(Event event) {
		try {
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
			
	    	engine.setRTSPP(RTSPPCache.getList(settlementPoint));
	    	initModel(new Date());
	    	
	    	lmpChart.setYAxis("Price");
	    	lmpChart.setFgAlpha(255);
	        lmpChart.setPaneColor("#ffffff");
	    	lmpChart.setType("highlow");
	    	lmpChart.setEngine(engine);
	    	lmpChart.setModel(model);
	    	
    	    binder.loadAll();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "deprecation" })
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		
		binder = new AnnotateDataBinder(this);
		binder.loadAll();
	
		if (Executions.getCurrent().getArg() != null) {
			type = String.valueOf(Executions.getCurrent().getArg().get("type"));
			settlementPoint = String.valueOf(Executions.getCurrent().getArg().get("settlementPoint"));
		}
		else {
			Messagebox.show("Settlement point cannot be empty", "Error", Messagebox.OK, Messagebox.NONE);
			return;
		}
		try {
	    	lmpCanonical.append("." + type.toLowerCase() + ".RTLMP" + type + "5M");
	    	sppCanonical.append("." + type.toLowerCase() + ".RTSPP" + type + "15M");
	    	
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
						
	    	engine.setRTSPP(RTSPPCache.getList(settlementPoint));
	    	//engine.setRTSPP(RTSPPFacade.findByIntervalDatesSettlementPoint(getSPPClass(sppCanonical.toString()), start.getTime(), end.getTime(), settlementPoint));
	    	initModel(new Date());
	    	
	    	lmpChart.setYAxis("Price");
	    	lmpChart.setFgAlpha(255);
	        lmpChart.setPaneColor("#ffffff");
	    	lmpChart.setType("highlow");
	    	lmpChart.setEngine(engine);
	    	lmpChart.setModel(model);
	    	
			lmpChart.setAreaListener(new ChartAreaListener() { 
			    @Override 
				public void onRender(Area area, Object data) throws Exception {
			    	if (map.size() == 0 || area.getAttribute("date") == null) return;
			    	Date intervalDate = Util.stringToDate(String.valueOf(area.getAttribute("date")), "E MMM dd hh:mm:ss z yyyy");
				    StringBuffer tooltip = new StringBuffer("Date - " + intervalDate.toString() + "\n"); 
				    Map<Integer, Float> values = map.get(intervalDate);
				    if (values == null) return;
				    for (Integer key : values.keySet())
				    	tooltip.append(key + " - " + values.get(key) + "\n");
				    area.setTooltiptext(tooltip.toString());
				}
			}); 
		}
		catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getCause().getMessage(), "Error", Messagebox.OK, Messagebox.NONE);
		}
		
		/*
		EventQueues.lookup(EventConstants.LMP_EVENT_QUEUE_NAME, EventQueues.APPLICATION, true).subscribe(
			new EventListener() {
			    public void onEvent(Event event) throws Exception {
	                String eventName = event.getName();
	                if(eventName.equals(EventConstants.LMP_UPDATE_EVENT_NAME)) {
	                	Date RTDTimestamp = (Date) event.getData(); 
	                	refreshModel(RTDTimestamp);
	                }
				}
		    }
		);
		*/
		
		/*
		EventQueues.lookup(EventConstants.SPP_EVENT_QUEUE_NAME, EventQueues.APPLICATION, true).subscribe(
			new EventListener() {
				@SuppressWarnings("unused")
				public void onEvent(Event event) throws Exception {
		            String eventName = event.getName();
		            if(eventName.equals(EventConstants.SPP_UPDATE_EVENT_NAME)) {
		                Date intervalDate = (Date) event.getData(); 
		                List<RTSPP> rtspps = RTSPPFacade.findByIntervalDate(getSPPClass(sppCanonical.toString()), intervalDate);
		            }
				}
			}
		);
		*/
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
	
    private void initModel(final Date seed) throws RTLMPException, ClassNotFoundException {
		Calendar start = Calendar.getInstance();
		start.setTime(seed);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		
		Calendar end = Calendar.getInstance();
		end.setTime(seed);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		
    	List<RTLMP> rtlmps = RTLMPCache.getList(settlementPoint);
    	
		model.clear();

		updateModel(rtlmps);
    }
    
    private void updateModel(List<RTLMP> rtlmps) {
    		    
		for (RTLMP rtlmp : rtlmps) {
			if (map.containsKey(rtlmp.getIntervalEnding()))
				map.get(rtlmp.getIntervalEnding()).put(rtlmp.getIntervalId(), rtlmp.getLMP());
			else {
			    Map<Integer, Float> values = new HashMap<Integer, Float>();
				values.put(rtlmp.getIntervalId(), rtlmp.getLMP());
				map.put(rtlmp.getIntervalEnding(), values);
			}
	    }	
                
	    SortedSet<Date> intervals = new TreeSet<Date>(map.keySet());
	    for (Date interval : intervals) {
	    	Calendar time = Calendar.getInstance();
	    	time.setTime(interval);
	    	model.addValue(interval, 
 			       map.get(interval).get(getOpen(map.get(interval).keySet())), 
 			       getHigh(map.get(interval)), 
 			       getLow(map.get(interval)), 
 			       map.get(interval).get(getClose(map.get(interval).keySet())), 
 			       map.get(interval).size());
	    }	    	    
	}
}
