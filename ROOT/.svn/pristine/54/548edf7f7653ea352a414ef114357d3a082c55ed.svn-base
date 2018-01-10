package com.griddatabase.util;

import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.impl.InputElement;

public class ZKUtil {
	public static Component getComponentById(Desktop desktop, String id) { 
		
		Component component = null;
		
		for( Object obj : desktop.getComponents() ) {
			component = (Component) obj;
			if( component.getId().equals(id) ) break;
			component = null;
		}
		
		return component;
	}
	
	public static void clearWrongValue( InputElement comp ) {
		Clients.clearWrongValue(comp);
		comp.clearErrorMessage();
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
			target = ((Listbox) target).getItemAtIndex(index);
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
}
