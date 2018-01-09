package com.softwarelikeyou.viewcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.impl.InputElement;

import com.softwarelikeyou.I18NStrings;
import com.softwarelikeyou.exception.LocalizedException;

public class WebUtil {
	protected static final mazz.i18n.Logger logger = mazz.i18n.LoggerFactory.getLogger(WebUtil.class);

	public static Component getFellowIfAnySafe(Component inSameSpaceAsMe, String id, Class<?> expectedClass) {
		Component c = inSameSpaceAsMe.getFellowIfAny(id);
		if (c == null || !(expectedClass.isInstance(c))) return null;
		return c;
	}
	
	public static <T extends Component> List<T> getChildrenByClass(Component comp, Class<T> clazz) {
		return getChildrenByClass(comp, clazz, true);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Component> List<T> getChildrenByClass(Component comp, Class<T> clazz, boolean recursive) {		
		
		List<T> comps = new ArrayList<T>();
		
		for( Component c : (List<Component>) comp.getChildren() ) {			
			
			if( clazz.isAssignableFrom(c.getClass()) ) {
				comps.add((T) c);
			}
			
			if( recursive && c.getChildren().size() > 0 ) { 
				comps.addAll(getChildrenByClass(c, clazz));
			}
			
		}
		
		return comps;
	}
	
	public static <T extends Component> T getFirstChildByClass(Component comp, Class<T> clazz) {		
		return getFirstChildByClass(comp, clazz, -1);
	}
	
	public static <T extends Component> T getFirstChildByClass(Component comp, Class<T> clazz, int maxDepth) { 
		return getFirstChildByClass(comp, clazz, maxDepth, 0);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Component> T getFirstChildByClass(Component comp, Class<T> clazz, int maxDepth, int currDepth) { 
	
		currDepth++;
		
		List<Component> children = new ArrayList<Component>();
		T t = null;
			
		for( Component c : (List<Component>) comp.getChildren() ) {	
			if( clazz.isAssignableFrom(c.getClass()) ) return (T) c;			
			if( c.getChildren().size() > 0 ) children.addAll(c.getChildren());			
		}
		
		if( !children.isEmpty() ) {
			for( Component c : children ) {
				
				if( clazz.isAssignableFrom(c.getClass()) ) return (T) c;
				
				if( maxDepth == -1 || currDepth < maxDepth ) { 
					t = getFirstChildByClass(c, clazz, maxDepth, currDepth);
					if( t != null ) return t;
				}
				
			}			
		}
			
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Component> T getParentByClass(Component comp, Class<T> clazz) { 
		
		Component parent = comp.getParent();
		
		while( parent != null ) {
			if( clazz.isAssignableFrom(parent.getClass()) ) break;			
			parent = parent.getParent();
		}

		return (T) parent;
	}
	
	@SuppressWarnings("unchecked")
	public static Object getRowBindingData(Event event) {
		
		Component target; 
		
		if( event instanceof ForwardEvent ) { 
			target = ((ForwardEvent) event).getOrigin().getTarget();
		}
		else { 
			target = event.getTarget();
		}
		
		if( Listbox.class.isAssignableFrom(target.getClass()) ) { 
			Integer index = ((Listbox) target).getSelectedIndex();
			target = ((Listbox) target).getItemAtIndexApi(index);
			if( target == null ) return null;
		}
		
		try {
			
			while(!(target instanceof Row || target instanceof Listitem)) {
				target = target.getParent(); 
			}
			
			Map<Object, Object> map = (Map<Object,Object>) target.getAttribute("zkplus.databind.TEMPLATEMAP");
			
			return map.get(target.getAttribute("zkplus.databind.VARNAME"));
		} 
		catch (NullPointerException e) {
			return null;
		}
		
	}
	
	public static Component getRowOrListitem(Event event) { 
		
		Component target; 
		
		if( event instanceof ForwardEvent ) { 
			target = ((ForwardEvent) event).getOrigin().getTarget();
		}
		else { 
			target = event.getTarget();
		}
		
		if( target instanceof Listbox ) { 
			Integer index = ((Listbox) target).getSelectedIndex();
			target = ((Listbox) target).getItemAtIndexApi(index);
		}
		
		while( target != null && !(target instanceof Row || target instanceof Listitem)) {
			target = target.getParent(); 
		}
		
		return target;
	}
	
	public static Object getRowOrListitemValue(Event event) {
		Component comp = getRowOrListitem(event);
		if( comp == null) return null;
		return comp instanceof Row ? ((Row) comp).getValue() : ((Listitem) comp).getValue();
	}
	
	public static Component getComponentById(Desktop desktop, String id) { 
		
		Component component = null;
		
		for( Object obj : desktop.getComponents() ) {
			component = (Component) obj;
			if( component.getId().equals(id) ) break;
			component = null;
		}
		
		return component;
	}
	
	public static HttpServletRequest getServletRequest(Execution execution) {
		return (HttpServletRequest)execution.getNativeRequest();
	}
	
	public static String getBrowserNewLine(Execution execution) {
		String userAgent = getServletRequest(execution).getHeader("user-agent");
		return ( userAgent == null || !userAgent.contains("Win") ) ? "\n" : "\r\n";
	}

	public static void clearWrongValue( InputElement comp ) {
		Clients.clearWrongValue(comp);
		comp.clearErrorMessage();
	}	
	
	public static void showErrorBox(boolean suspendExecution, Throwable ex, String msgKey, Object... varargs) {

		Clients.clearBusy();

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

		List<String> exStrings = getLocalizedExceptionStrings(ex);

		window.setErrorMessages(new ListModelList(exStrings));
		window.setClosable(true);
		window.setMaximizable(true);

		if( suspendExecution ) {
			try {
				window.doModal();
			}
			catch( SuspendNotAllowedException e ) {
				logger.error(e, I18NStrings.COULD_NOT_SUSPEND_EXECUTION_TO_DISPLAY_ERROR_BOX);
			}
			catch( InterruptedException e ) {
				logger.error(e, I18NStrings.RECEIVED_INTERRUPT_WHILE_SUSPENDED_DISPLAYING_AN_ERROR);
			}
		}
		else {
			window.doHighlighted();
		}
	}

	public static void showBox(String msgKey, String labelKey, Object... varargs) {
		try {
			Messagebox.show(
				ELFunctions.getMessageWithParams(msgKey, varargs),
				ELFunctions.getLabel(labelKey),
				Messagebox.OK,
				Messagebox.NONE
				);
		}
		catch( InterruptedException ex ) {
			ex.printStackTrace();
		}
	}

	public static void showSuccessBox(String msgKey, Object... varargs) {
		showBox(msgKey, I18NStrings.SUCCESS, varargs);
	}
	
	public static List<String> getLocalizedExceptionStrings(Throwable cause) {

		List<String> strings = new ArrayList<String>();

		if( cause != null ) {

			Locale locale = SessionUtil.getSelectedLocale();

			Throwable e = cause;

			while( e instanceof LocalizedException ) {
				LocalizedException le = (LocalizedException) e;
				strings.add(ELFunctions.getMessageWithParams(locale, le.getMsgKey(), (Object[]) le.getVarArgs()));
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
