package com.softwarelikeyou.ercot.analyzer.rtlmp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.softwarelikeyou.analyzer.WorkerHelper;
import com.softwarelikeyou.facade.RTLMPFacade;
import com.softwarelikeyou.model.entity.rtlmp.RTLMP;
import com.softwarelikeyou.model.entity.rtlmp.ah.RTLMPAH15M;
import com.softwarelikeyou.model.entity.rtlmp.ah.RTLMPAH1H;
import com.softwarelikeyou.model.entity.rtlmp.ah.RTLMPAH24H;
import com.softwarelikeyou.model.entity.rtlmp.ah.RTLMPAH30M;
import com.softwarelikeyou.model.entity.rtlmp.ah.RTLMPAH5M;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU15M;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU1H;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU24H;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU30M;
import com.softwarelikeyou.model.entity.rtlmp.hu.RTLMPHU5M;
import com.softwarelikeyou.model.entity.rtlmp.lccrn.RTLMPLCCRN15M;
import com.softwarelikeyou.model.entity.rtlmp.lccrn.RTLMPLCCRN1H;
import com.softwarelikeyou.model.entity.rtlmp.lccrn.RTLMPLCCRN24H;
import com.softwarelikeyou.model.entity.rtlmp.lccrn.RTLMPLCCRN30M;
import com.softwarelikeyou.model.entity.rtlmp.lccrn.RTLMPLCCRN5M;
import com.softwarelikeyou.model.entity.rtlmp.lz.RTLMPLZ15M;
import com.softwarelikeyou.model.entity.rtlmp.lz.RTLMPLZ1H;
import com.softwarelikeyou.model.entity.rtlmp.lz.RTLMPLZ24H;
import com.softwarelikeyou.model.entity.rtlmp.lz.RTLMPLZ30M;
import com.softwarelikeyou.model.entity.rtlmp.lz.RTLMPLZ5M;
import com.softwarelikeyou.model.entity.rtlmp.lzdc.RTLMPLZDC15M;
import com.softwarelikeyou.model.entity.rtlmp.lzdc.RTLMPLZDC1H;
import com.softwarelikeyou.model.entity.rtlmp.lzdc.RTLMPLZDC24H;
import com.softwarelikeyou.model.entity.rtlmp.lzdc.RTLMPLZDC30M;
import com.softwarelikeyou.model.entity.rtlmp.lzdc.RTLMPLZDC5M;
import com.softwarelikeyou.model.entity.rtlmp.pccrn.RTLMPPCCRN15M;
import com.softwarelikeyou.model.entity.rtlmp.pccrn.RTLMPPCCRN1H;
import com.softwarelikeyou.model.entity.rtlmp.pccrn.RTLMPPCCRN24H;
import com.softwarelikeyou.model.entity.rtlmp.pccrn.RTLMPPCCRN30M;
import com.softwarelikeyou.model.entity.rtlmp.pccrn.RTLMPPCCRN5M;
import com.softwarelikeyou.model.entity.rtlmp.pun.RTLMPPUN15M;
import com.softwarelikeyou.model.entity.rtlmp.pun.RTLMPPUN1H;
import com.softwarelikeyou.model.entity.rtlmp.pun.RTLMPPUN24H;
import com.softwarelikeyou.model.entity.rtlmp.pun.RTLMPPUN30M;
import com.softwarelikeyou.model.entity.rtlmp.pun.RTLMPPUN5M;
import com.softwarelikeyou.model.entity.rtlmp.rn.RTLMPRN15M;
import com.softwarelikeyou.model.entity.rtlmp.rn.RTLMPRN1H;
import com.softwarelikeyou.model.entity.rtlmp.rn.RTLMPRN24H;
import com.softwarelikeyou.model.entity.rtlmp.rn.RTLMPRN30M;
import com.softwarelikeyou.model.entity.rtlmp.rn.RTLMPRN5M;
import com.softwarelikeyou.model.entity.rtlmp.sh.RTLMPSH15M;
import com.softwarelikeyou.model.entity.rtlmp.sh.RTLMPSH1H;
import com.softwarelikeyou.model.entity.rtlmp.sh.RTLMPSH24H;
import com.softwarelikeyou.model.entity.rtlmp.sh.RTLMPSH30M;
import com.softwarelikeyou.model.entity.rtlmp.sh.RTLMPSH5M;
import com.softwarelikeyou.util.Util;

public class PostWorker {
	private static List<Class<? extends RTLMP>> list = new ArrayList<Class<? extends RTLMP>>();
	
	private Date RTDTimestamp;
	
	public void setRTDTimestamp(Date value) {
		RTDTimestamp = value;
	}
	
	public Date getRTDTimestamp() {
		return RTDTimestamp;
	}
	
	static {
        list.add(RTLMPAH5M.class);
        list.add(RTLMPAH15M.class);
        list.add(RTLMPAH30M.class);
        list.add(RTLMPAH1H.class);
        list.add(RTLMPAH24H.class);
        
        list.add(RTLMPHU5M.class);
        list.add(RTLMPHU15M.class);
        list.add(RTLMPHU30M.class);
        list.add(RTLMPHU1H.class);
        list.add(RTLMPHU24H.class);
        
        list.add(RTLMPLCCRN5M.class);
        list.add(RTLMPLCCRN15M.class);
        list.add(RTLMPLCCRN30M.class);
        list.add(RTLMPLCCRN1H.class);
        list.add(RTLMPLCCRN24H.class);
        
        list.add(RTLMPLZ5M.class);
        list.add(RTLMPLZ15M.class);
        list.add(RTLMPLZ30M.class);
        list.add(RTLMPLZ1H.class);
        list.add(RTLMPLZ24H.class);
        
        list.add(RTLMPLZDC5M.class);
        list.add(RTLMPLZDC15M.class);
        list.add(RTLMPLZDC30M.class);
        list.add(RTLMPLZDC1H.class);
        list.add(RTLMPLZDC24H.class);
        
        list.add(RTLMPPCCRN5M.class);
        list.add(RTLMPPCCRN15M.class);
        list.add(RTLMPPCCRN30M.class);
        list.add(RTLMPPCCRN1H.class);
        list.add(RTLMPPCCRN24H.class);
        
        list.add(RTLMPPUN5M.class);
        list.add(RTLMPPUN15M.class);
        list.add(RTLMPPUN30M.class);
        list.add(RTLMPPUN1H.class);
        list.add(RTLMPPUN24H.class);
        
        list.add(RTLMPRN5M.class);
        list.add(RTLMPRN15M.class);
        list.add(RTLMPRN30M.class);
        list.add(RTLMPRN1H.class);
        list.add(RTLMPRN24H.class);

        list.add(RTLMPSH5M.class);
        list.add(RTLMPSH15M.class);
        list.add(RTLMPSH30M.class);
        list.add(RTLMPSH1H.class);
        list.add(RTLMPSH24H.class);
	}
	
	public boolean execute() {
		if (RTDTimestamp == null)
			return false;
		try {
			for (Class<? extends RTLMP> clazz : list) {
				for (RTLMP rtlmp : RTLMPFacade.findByRTDTimestamp(clazz, RTDTimestamp)) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(RTDTimestamp);
					calendar.add(Calendar.MINUTE, -5);
					RTLMP previous = RTLMPFacade.findById(clazz, calendar.getTime(), rtlmp.getIntervalEnding(), rtlmp.getSettlementPoint());
					if (previous == null) 
						continue;
                    rtlmp.setVelocity(WorkerHelper.formatFloat(rtlmp.getLMP() - previous.getLMP()));
                    rtlmp.setAcceleration(WorkerHelper.formatFloat(rtlmp.getVelocity() - previous.getVelocity()));
                    RTLMPFacade.createOrUpdate(rtlmp);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static void main(String[] args) {
		try {
	        if (args.length != 2) {
		        System.out.println("Usage: java Worker date (format: yyyyMMdd) time (format: kk:mm:ss");
		        System.exit(1);
		    }
	        
	        String date = args[0];
			if (!WorkerHelper.validateDate(date)) {
				System.out.println("Date " + date + " is of incorrect format, must be yyyymmdd");
				System.exit(1);
			}
			
	        String time = args[1];
			if (!validateTime(time)) {
				System.out.println("Date " + date + " is of incorrect format, must be yyyymmdd");
				System.exit(1);
			}
	        
	        if (!Worker.initialize()) {
		        System.out.println("Could not initialize hibernate");
		        System.exit(1);
		    }
	        
	        PostWorker worker = new PostWorker();
	        worker.setRTDTimestamp(Util.stringToDate(date + " " + time, "yyyyMMdd kk:mm:ss"));
	        worker.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static boolean validateTime(final String time) {
		boolean results = true;
		try {
			SimpleDateFormat format = new SimpleDateFormat("kk:mm:ss");
			format.parse(time);
		}
		catch (ParseException e) {
			results = false;
		}
		return results;
	}
}
