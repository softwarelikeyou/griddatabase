package com.softwarelikeyou.ercot.analyzer.rtspp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.softwarelikeyou.analyzer.WorkerHelper;
import com.softwarelikeyou.facade.RTSPPFacade;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;
import com.softwarelikeyou.model.entity.rtspp.ah.RTSPPAH15M;
import com.softwarelikeyou.model.entity.rtspp.ah.RTSPPAH1H;
import com.softwarelikeyou.model.entity.rtspp.ah.RTSPPAH24H;
import com.softwarelikeyou.model.entity.rtspp.ah.RTSPPAH30M;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU15M;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU1H;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU24H;
import com.softwarelikeyou.model.entity.rtspp.hu.RTSPPHU30M;
import com.softwarelikeyou.model.entity.rtspp.lccrn.RTSPPLCCRN15M;
import com.softwarelikeyou.model.entity.rtspp.lccrn.RTSPPLCCRN1H;
import com.softwarelikeyou.model.entity.rtspp.lccrn.RTSPPLCCRN24H;
import com.softwarelikeyou.model.entity.rtspp.lccrn.RTSPPLCCRN30M;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ15M;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ1H;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ24H;
import com.softwarelikeyou.model.entity.rtspp.lz.RTSPPLZ30M;
import com.softwarelikeyou.model.entity.rtspp.lzdc.RTSPPLZDC15M;
import com.softwarelikeyou.model.entity.rtspp.lzdc.RTSPPLZDC1H;
import com.softwarelikeyou.model.entity.rtspp.lzdc.RTSPPLZDC24H;
import com.softwarelikeyou.model.entity.rtspp.lzdc.RTSPPLZDC30M;
import com.softwarelikeyou.model.entity.rtspp.lzdcew.RTSPPLZDCEW15M;
import com.softwarelikeyou.model.entity.rtspp.lzdcew.RTSPPLZDCEW1H;
import com.softwarelikeyou.model.entity.rtspp.lzdcew.RTSPPLZDCEW24H;
import com.softwarelikeyou.model.entity.rtspp.lzdcew.RTSPPLZDCEW30M;
import com.softwarelikeyou.model.entity.rtspp.lzew.RTSPPLZEW15M;
import com.softwarelikeyou.model.entity.rtspp.lzew.RTSPPLZEW1H;
import com.softwarelikeyou.model.entity.rtspp.lzew.RTSPPLZEW24H;
import com.softwarelikeyou.model.entity.rtspp.lzew.RTSPPLZEW30M;
import com.softwarelikeyou.model.entity.rtspp.pccrn.RTSPPPCCRN15M;
import com.softwarelikeyou.model.entity.rtspp.pccrn.RTSPPPCCRN1H;
import com.softwarelikeyou.model.entity.rtspp.pccrn.RTSPPPCCRN24H;
import com.softwarelikeyou.model.entity.rtspp.pccrn.RTSPPPCCRN30M;
import com.softwarelikeyou.model.entity.rtspp.pun.RTSPPPUN15M;
import com.softwarelikeyou.model.entity.rtspp.pun.RTSPPPUN1H;
import com.softwarelikeyou.model.entity.rtspp.pun.RTSPPPUN24H;
import com.softwarelikeyou.model.entity.rtspp.pun.RTSPPPUN30M;
import com.softwarelikeyou.model.entity.rtspp.rn.RTSPPRN15M;
import com.softwarelikeyou.model.entity.rtspp.rn.RTSPPRN1H;
import com.softwarelikeyou.model.entity.rtspp.rn.RTSPPRN24H;
import com.softwarelikeyou.model.entity.rtspp.rn.RTSPPRN30M;
import com.softwarelikeyou.model.entity.rtspp.sh.RTSPPSH15M;
import com.softwarelikeyou.model.entity.rtspp.sh.RTSPPSH1H;
import com.softwarelikeyou.model.entity.rtspp.sh.RTSPPSH24H;
import com.softwarelikeyou.model.entity.rtspp.sh.RTSPPSH30M;

public class PostWorker {
	private static List<Class<? extends RTSPP>> list = new ArrayList<Class<? extends RTSPP>>();

	private Date intervalDate;
	
	public void setIntervalDate(Date value) {
		intervalDate = value;
	}
	
	public Date getIntervalDate() {
		return intervalDate;
	}
	
	static {
        list.add(RTSPPAH15M.class);
        list.add(RTSPPAH30M.class);
        list.add(RTSPPAH1H.class);
        list.add(RTSPPAH24H.class);
        
        list.add(RTSPPHU15M.class);
        list.add(RTSPPHU30M.class);
        list.add(RTSPPHU1H.class);
        list.add(RTSPPHU24H.class);
        
        list.add(RTSPPLCCRN15M.class);
        list.add(RTSPPLCCRN30M.class);
        list.add(RTSPPLCCRN1H.class);
        list.add(RTSPPLCCRN24H.class);
        
        list.add(RTSPPLZ15M.class);
        list.add(RTSPPLZ30M.class);
        list.add(RTSPPLZ1H.class);
        list.add(RTSPPLZ24H.class);
        
        list.add(RTSPPLZDC15M.class);
        list.add(RTSPPLZDC30M.class);
        list.add(RTSPPLZDC1H.class);
        list.add(RTSPPLZDC24H.class);
        
        list.add(RTSPPLZDCEW15M.class);
        list.add(RTSPPLZDCEW30M.class);
        list.add(RTSPPLZDCEW1H.class);
        list.add(RTSPPLZDCEW24H.class);
        
        list.add(RTSPPLZEW15M.class);
        list.add(RTSPPLZEW30M.class);
        list.add(RTSPPLZEW1H.class);
        list.add(RTSPPLZEW24H.class);
        
        list.add(RTSPPPCCRN15M.class);
        list.add(RTSPPPCCRN30M.class);
        list.add(RTSPPPCCRN1H.class);
        list.add(RTSPPPCCRN24H.class);
        
        list.add(RTSPPPUN15M.class);
        list.add(RTSPPPUN30M.class);
        list.add(RTSPPPUN1H.class);
        list.add(RTSPPPUN24H.class);
        
        list.add(RTSPPRN15M.class);
        list.add(RTSPPRN30M.class);
        list.add(RTSPPRN1H.class);
        list.add(RTSPPRN24H.class);

        list.add(RTSPPSH15M.class);
        list.add(RTSPPSH30M.class);
        list.add(RTSPPSH1H.class);
        list.add(RTSPPSH24H.class);
	}
	
	public boolean execute() {
		if (intervalDate == null)
			return false;
		try {
			for (Class<? extends RTSPP> clazz : list) {
				for (RTSPP rtspp : RTSPPFacade.findByIntervalDate(clazz, intervalDate)) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(intervalDate);
					calendar.add(Calendar.MINUTE, -15);
					RTSPP previous = RTSPPFacade.findById(clazz, calendar.getTime(), rtspp.getSettlementPointName());
					if (previous == null) 
						continue;
                    rtspp.setVelocity(WorkerHelper.formatFloat(rtspp.getSettlementPointPrice() - previous.getSettlementPointPrice()));
                    rtspp.setAcceleration(WorkerHelper.formatFloat(rtspp.getVelocity() - previous.getVelocity()));
                    RTSPPFacade.createOrUpdate(rtspp);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
