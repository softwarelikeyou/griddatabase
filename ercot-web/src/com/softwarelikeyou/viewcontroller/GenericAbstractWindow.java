package com.softwarelikeyou.viewcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mazz.i18n.Msg;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.softwarelikeyou.I18NStrings;
import com.softwarelikeyou.exception.LocalizedException;

public abstract class GenericAbstractWindow extends Window {

	static final long serialVersionUID = 1L;
	
	protected static final mazz.i18n.Logger logger = mazz.i18n.LoggerFactory.getLogger(GenericAbstractWindow.class);
	protected static final org.apache.log4j.Logger debug = org.apache.log4j.Logger.getLogger(GenericAbstractWindow.class);
	
	protected void showErrorBox(String msgKey, Object... varargs) { 
		showErrorBox(null, msgKey, varargs);
	}
	
	protected void showErrorBox(boolean suspendExecution, String msgKey, Object... varargs) { 
		showErrorBox(suspendExecution, null, msgKey, varargs);
	}
	
	protected void showErrorBox(Throwable ex, String msgKey, Object... varargs) {
		showErrorBox(false, ex, msgKey, varargs);
	}
	
	protected void showErrorBox(boolean suspendExecution, Throwable ex, String msgKey, Object... varargs) { 

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
			BaseController.ERROR_MSG_URL, null, null
		);
		
		window.setPrimaryCause(msg);
		window.setErrorMessages(new ListModelList(getLocalizedExceptionStrings(ex)));
		
		window.setClosable(true);
		window.setMaximizable(true);
		
		if( suspendExecution ) { 
			try {
				window.doModal();
			} 
			catch (SuspendNotAllowedException e) {
				logger.error(e, I18NStrings.COULD_NOT_SUSPEND_EXECUTION_TO_DISPLAY_ERROR_BOX);
			} 
			catch (InterruptedException e) {
				logger.error(e, I18NStrings.RECEIVED_INTERRUPT_WHILE_SUSPENDED_DISPLAYING_AN_ERROR);
			}
		}
		else { 
			window.doHighlighted();
		}
	}	

	public List<String> getLocalizedExceptionStrings(Throwable cause) { 
		
		List<String> strings = new ArrayList<String>();
		
		if( cause != null ) {
	
			Locale locale = SessionUtil.getSelectedLocale();
			
			Throwable e = cause;
				
			while( e instanceof LocalizedException ) {
				LocalizedException le = (LocalizedException) e;
				strings.add(Msg.createMsg(locale, le.getMsgKey(), (Object[]) le.getVarArgs()).toString());
				e = e.getCause();
			}
	
			while( e != null ) {
				strings.add(e.getLocalizedMessage());
				e = e.getCause();
			}
			
		}
		
		return strings;
	}	
	
}
