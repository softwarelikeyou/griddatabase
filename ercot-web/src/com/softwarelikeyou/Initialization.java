package com.softwarelikeyou;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.softwarelikeyou.facade.PasswordEncoder;
import com.softwarelikeyou.facade.UserFacade;
import com.softwarelikeyou.model.entity.User;
import com.softwarelikeyou.model.entity.UserData;
import com.softwarelikeyou.model.entity.ascpc.ASCPC;
import com.softwarelikeyou.model.entity.ascpc.ASCPCDaily;
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
import com.softwarelikeyou.util.ActiveMQUtil;
import com.softwarelikeyou.util.HibernateUtil;
import com.softwarelikeyou.util.ResourceUtil;

public class Initialization implements ServletContextListener {
	private static Logger logger = Logger.getLogger(Initialization.class);

	public void contextDestroyed(ServletContextEvent sce) {
	    try {	
	 	    if (ActiveMQUtil.isStarted())
		        ActiveMQUtil.stop();
			if (!HibernateUtil.getSessionFactory().isClosed())
			    HibernateUtil.shutdown();
		} 
	    catch (Exception e) {
			logger.error(e.getCause(), e);
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {   		  
		    Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty(Environment.DRIVER, ResourceUtil.get().getString("hibernate.connection.driver_class"));
	        hibernateProperties.setProperty(Environment.URL, ResourceUtil.get().getString("hibernate.connection.url"));
	        hibernateProperties.setProperty(Environment.USER, ResourceUtil.get().getString("hibernate.connection.username"));
		    hibernateProperties.setProperty(Environment.PASS, ResourceUtil.get().getString("hibernate.connection.password"));
		    hibernateProperties.setProperty(Environment.DIALECT, ResourceUtil.get().getString("hibernate.dialect"));
		    hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, ResourceUtil.get().getString("hibernate.hbm2ddl.auto"));
		    hibernateProperties.setProperty(Environment.SHOW_SQL, ResourceUtil.get().getString("hibernate.show_sql"));
		    hibernateProperties.setProperty("hibernate.connection.autoreconnect", "true");
			hibernateProperties.setProperty("hibernate.connection.autoreconnectforpools", "true");
			hibernateProperties.setProperty("hibernate.c3p0.idle_test_period", "30");
			hibernateProperties.setProperty(Environment.C3P0_MIN_SIZE, "10");
			hibernateProperties.setProperty("hibernate.connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider");
		    
	        Configuration hibernateConfiguration = new Configuration()
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(UserData.class)
            .addAnnotatedClass(ASCPCDaily.class)
            .addAnnotatedClass(ASCPC.class)
            .addAnnotatedClass(RTSPPDaily.class)
                    
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
            .setProperties(hibernateProperties);
	        
            logger.info("Initializing Hibernate");
            HibernateUtil.getInstance(hibernateConfiguration);
            if (!HibernateUtil.test())
        	    logger.error("Hibernate could not be initialized");

			User user = UserFacade.findByUsername("admin");
			if (user == null) {
				logger.info("Creating Administrator (admin) Account");
				user = new User();
			    user.setUsername("admin");
			    user.setDisplay("Administrator Account");
			    user.setPassword(new PasswordEncoder().encodePassword("admin", null));
			    user.setType(User.UserType.ADMINISTRATOR);
			    user = UserFacade.createOrUpdate(user);
			}
			
            logger.info("Initializing ActiveMQ");
			ActiveMQUtil.getInstance();
			
		}
		catch (Exception e) {
		    logger.error(e.getCause(), e);
		}
	}
}
