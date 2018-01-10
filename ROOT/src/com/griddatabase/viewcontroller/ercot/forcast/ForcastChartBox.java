package com.griddatabase.viewcontroller.ercot.forcast;

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
import org.zkoss.zul.Chart;
import org.zkoss.zul.Div;
import org.zkoss.zul.HiLoModel;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleHiLoModel;
import org.zkoss.zul.Timer;

import com.softwarelikeyou.exception.RTLMPException;
import com.softwarelikeyou.facade.RTLMPFacade;
import com.softwarelikeyou.model.entity.rtlmp.RTLMP;

public class ForcastChartBox extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;
	private HiLoModel model = new SimpleHiLoModel();
    private CandlestickChartEngine engine;
    private String type;
    private String settlementPoint;
	private String canonical;
	private static String PACKAGE = "com.softwarelikeyou.model.entity.rtlmp";
    	
	protected AnnotateDataBinder binder;

	protected Timer timer;
	protected Chart candlestick;
	
	@SuppressWarnings("deprecation")
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
	    binder = new AnnotateDataBinder(this);
	    binder.loadAll();
	    
	   	try {
    		if (Executions.getCurrent().getArg() != null) {
    			type = String.valueOf(Executions.getCurrent().getArg().get("type"));
    			settlementPoint = String.valueOf(Executions.getCurrent().getArg().get("settlementPoint"));
    	    	canonical = PACKAGE + "." + type.toLowerCase() + ".RTLMP" + type + "5M";
    		}
    		else {
				Messagebox.show("Settlement point cannot be empty", "Error", Messagebox.OK, Messagebox.NONE);
				return;
    		}
            engine = new CandlestickChartEngine();
            updateModel(new Date());
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public void onTimer$timer(Event event) {
		try {
            updateModel(new Date());
    	    binder.loadAll();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	@SuppressWarnings("unchecked")
	private Class<? extends RTLMP> getClass(String value) throws ClassNotFoundException {
		return (Class<? extends RTLMP>) Class.forName(value);
	}
 
    public CandlestickChartEngine getEngine() {
        return engine;
    }
 
    public HiLoModel getModel() {
        return model;
    }
    
    private void updateModel(final Date seed) throws RTLMPException, ClassNotFoundException {
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
		
    	List<RTLMP> rtlmps = RTLMPFacade.findByBetweenIntervalEndingDatesSettlementPoint(getClass(canonical), start.getTime(), end.getTime(), settlementPoint);
    	
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
                
		model.clear();
	    SortedSet<Date> intervals = new TreeSet<Date>(map.keySet());
	    for (Date interval : intervals) {
	    	Calendar time = Calendar.getInstance();
	    	time.setTime(interval);
	    	model.addValue(interval, 
 			       map.get(interval).get(getOpen(map.get(interval).keySet())), 
 			       getHigh(map.get(interval)), 
 			       getLow(map.get(interval)), 
 			       map.get(interval).get(getClose(map.get(interval).keySet())), 
 			       0f);
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
}
