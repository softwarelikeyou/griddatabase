package com.softwarelikeyou.ercot.analyzer.rtlmp;

import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.analyzer.WorkerHelper;
import com.softwarelikeyou.model.entity.File;
import com.softwarelikeyou.model.entity.SettlementPoint;
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
import com.softwarelikeyou.util.ResourceUtil;

public class RTLMPHelper extends WorkerHelper {

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
        .addAnnotatedClass(RTLMPAH5M.class)
        .addAnnotatedClass(RTLMPAH15M.class)
        .addAnnotatedClass(RTLMPAH30M.class)
        .addAnnotatedClass(RTLMPAH1H.class)
        .addAnnotatedClass(RTLMPAH24H.class)
        
        .addAnnotatedClass(RTLMPHU5M.class)
        .addAnnotatedClass(RTLMPHU15M.class)
        .addAnnotatedClass(RTLMPHU30M.class)
        .addAnnotatedClass(RTLMPHU1H.class)
        .addAnnotatedClass(RTLMPHU24H.class)
        
        .addAnnotatedClass(RTLMPLCCRN5M.class)
        .addAnnotatedClass(RTLMPLCCRN15M.class)
        .addAnnotatedClass(RTLMPLCCRN30M.class)
        .addAnnotatedClass(RTLMPLCCRN1H.class)
        .addAnnotatedClass(RTLMPLCCRN24H.class)
        
        .addAnnotatedClass(RTLMPLZ5M.class)
        .addAnnotatedClass(RTLMPLZ15M.class)
        .addAnnotatedClass(RTLMPLZ30M.class)
        .addAnnotatedClass(RTLMPLZ1H.class)
        .addAnnotatedClass(RTLMPLZ24H.class)
        
        .addAnnotatedClass(RTLMPLZDC5M.class)
        .addAnnotatedClass(RTLMPLZDC15M.class)
        .addAnnotatedClass(RTLMPLZDC30M.class)
        .addAnnotatedClass(RTLMPLZDC1H.class)
        .addAnnotatedClass(RTLMPLZDC24H.class)
        
        .addAnnotatedClass(RTLMPPCCRN5M.class)
        .addAnnotatedClass(RTLMPPCCRN15M.class)
        .addAnnotatedClass(RTLMPPCCRN30M.class)
        .addAnnotatedClass(RTLMPPCCRN1H.class)
        .addAnnotatedClass(RTLMPPCCRN24H.class)
        
        .addAnnotatedClass(RTLMPPUN5M.class)
        .addAnnotatedClass(RTLMPPUN15M.class)
        .addAnnotatedClass(RTLMPPUN30M.class)
        .addAnnotatedClass(RTLMPPUN1H.class)
        .addAnnotatedClass(RTLMPPUN24H.class)
        
        .addAnnotatedClass(RTLMPRN5M.class)
        .addAnnotatedClass(RTLMPRN15M.class)
        .addAnnotatedClass(RTLMPRN30M.class)
        .addAnnotatedClass(RTLMPRN1H.class)
        .addAnnotatedClass(RTLMPRN24H.class)

        .addAnnotatedClass(RTLMPSH5M.class)
        .addAnnotatedClass(RTLMPSH15M.class)
        .addAnnotatedClass(RTLMPSH30M.class)
        .addAnnotatedClass(RTLMPSH1H.class)
        .addAnnotatedClass(RTLMPSH24H.class)
        
        .addAnnotatedClass(File.class)
        .addAnnotatedClass(SettlementPoint.class)
        
        .setProperties(hibernateProperties);
        
        return hibernateConfiguration;
	}
}
