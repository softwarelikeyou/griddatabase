package com.softwarelikeyou.ercot.analyzer.rtspp;

import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.analyzer.WorkerHelper;
import com.softwarelikeyou.model.entity.File;
import com.softwarelikeyou.model.entity.SettlementPoint;
import com.softwarelikeyou.model.entity.rtspp.RTSPPDaily;
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
import com.softwarelikeyou.util.ResourceUtil;

public class RTSPPHelper extends WorkerHelper {

	public static Configuration getHibernateConfiguration() {
	    Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(Environment.DRIVER, ResourceUtil.get().getString("hibernate.connection.driver_class"));
        hibernateProperties.setProperty(Environment.URL, ResourceUtil.get().getString("hibernate.connection.url"));
        hibernateProperties.setProperty(Environment.USER, ResourceUtil.get().getString("hibernate.connection.username"));
	    hibernateProperties.setProperty(Environment.PASS, ResourceUtil.get().getString("hibernate.connection.password"));
	    hibernateProperties.setProperty(Environment.DIALECT, ResourceUtil.get().getString("hibernate.dialect"));
	    hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, ResourceUtil.get().getString("hibernate.hbm2ddl.auto"));
	    hibernateProperties.setProperty(Environment.SHOW_SQL, ResourceUtil.get().getString("hibernate.show_sql"));

        Configuration hibernateConfiguration = new Configuration()
        .addAnnotatedClass(RTSPPAH15M.class)
        .addAnnotatedClass(RTSPPAH30M.class)
        .addAnnotatedClass(RTSPPAH1H.class)
        .addAnnotatedClass(RTSPPAH24H.class)
        
        .addAnnotatedClass(RTSPPHU15M.class)
        .addAnnotatedClass(RTSPPHU30M.class)
        .addAnnotatedClass(RTSPPHU1H.class)
        .addAnnotatedClass(RTSPPHU24H.class)
        
        .addAnnotatedClass(RTSPPLCCRN15M.class)
        .addAnnotatedClass(RTSPPLCCRN30M.class)
        .addAnnotatedClass(RTSPPLCCRN1H.class)
        .addAnnotatedClass(RTSPPLCCRN24H.class)
       
        .addAnnotatedClass(RTSPPLZ15M.class)
        .addAnnotatedClass(RTSPPLZ30M.class)
        .addAnnotatedClass(RTSPPLZ1H.class)
        .addAnnotatedClass(RTSPPLZ24H.class)
        
        .addAnnotatedClass(RTSPPLZDC15M.class)
        .addAnnotatedClass(RTSPPLZDC30M.class)
        .addAnnotatedClass(RTSPPLZDC1H.class)
        .addAnnotatedClass(RTSPPLZDC24H.class)
                
        .addAnnotatedClass(RTSPPLZDCEW15M.class)
        .addAnnotatedClass(RTSPPLZDCEW30M.class)
        .addAnnotatedClass(RTSPPLZDCEW1H.class)
        .addAnnotatedClass(RTSPPLZDCEW24H.class)
        
        .addAnnotatedClass(RTSPPLZEW15M.class)
        .addAnnotatedClass(RTSPPLZEW30M.class)
        .addAnnotatedClass(RTSPPLZEW1H.class)
        .addAnnotatedClass(RTSPPLZEW24H.class)
        
        .addAnnotatedClass(RTSPPPCCRN15M.class)
        .addAnnotatedClass(RTSPPPCCRN30M.class)
        .addAnnotatedClass(RTSPPPCCRN1H.class)
        .addAnnotatedClass(RTSPPPCCRN24H.class)
        
        .addAnnotatedClass(RTSPPPUN15M.class)
        .addAnnotatedClass(RTSPPPUN30M.class)
        .addAnnotatedClass(RTSPPPUN1H.class)
        .addAnnotatedClass(RTSPPPUN24H.class)
        
        .addAnnotatedClass(RTSPPRN15M.class)
        .addAnnotatedClass(RTSPPRN30M.class)
        .addAnnotatedClass(RTSPPRN1H.class)
        .addAnnotatedClass(RTSPPRN24H.class)
        
        .addAnnotatedClass(RTSPPSH15M.class)
        .addAnnotatedClass(RTSPPSH30M.class)
        .addAnnotatedClass(RTSPPSH1H.class)
        .addAnnotatedClass(RTSPPSH24H.class)
        
        .addAnnotatedClass(SettlementPoint.class)
        .addAnnotatedClass(File.class)
        .addAnnotatedClass(RTSPPDaily.class)
        
        .setProperties(hibernateProperties);
        
        return hibernateConfiguration;
	}
}
