package com.softwarelikeyou.viewcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mazz.i18n.Msg;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.softwarelikeyou.exception.LocalizedException;

public class BaseWindow extends Window {
	protected static final mazz.i18n.Logger logger = mazz.i18n.LoggerFactory.getLogger(BaseController.class);
	protected static final org.apache.log4j.Logger debug = org.apache.log4j.Logger.getLogger(BaseController.class);
	
	public static final String ERROR_MSG_URL = "/error-msg.zul";
	
	static final long serialVersionUID = 1L;
	
	protected void showErrorBox(String msgKey, Object... varargs) { 
		showErrorBox(null, msgKey, varargs);
	}
	
	protected void showErrorBox(Throwable ex, String msgKey, Object... varargs) { 

		if( ex != null ) { 
			logger.error(ex, msgKey, varargs);
		}
		else { 
			logger.error(msgKey, varargs);
		}
		
		String msg; 
		
		if( varargs.length == 0 ) { 
			msg = ELFunctions.getMessage(msgKey);
		}
		else { 
			msg = ELFunctions.getMessageWithParams(msgKey, varargs);
		}
		
		ErrorMessageWindow window = (ErrorMessageWindow) Executions.createComponents(
			ERROR_MSG_URL, null, null
		);
		
		window.setPrimaryCause(msg);
		window.setErrorMessages(new ListModelList(getLocalizedExceptionStrings(ex)));
		
		window.setClosable(true);
		window.setMaximizable(true);
		
		window.doHighlighted();
	}	

	public static List<String> getLocalizedExceptionStrings(Throwable cause) { 
		
		List<String> strings = new ArrayList<String>();
		if( cause == null ) return strings;
	
		Locale locale = SessionUtil.getSelectedLocale();
		
		if( cause instanceof LocalizedException ) { 
			
			Throwable e = cause; 
			
			while( e instanceof LocalizedException ) {
				LocalizedException le = (LocalizedException) e;
				strings.add(Msg.createMsg(locale, le.getMsgKey(), (Object[]) le.getVarArgs()).toString());
				e = e.getCause();
			}
			
			if( e != null ) strings.add(e.getLocalizedMessage());
		}
		else { 
			strings.add(cause.getLocalizedMessage());
		}
		
		return strings;
	}
}
