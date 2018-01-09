package com.softwarelikeyou.viewcontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.zkoss.zk.ui.Execution;

public class ThemeProvider implements org.zkoss.zk.ui.util.ThemeProvider {
	 
    protected String themeName = "gray";
    
    protected static final String fileList = "grid.css.dsp,colorbox.css.dsp,columnlayout.css.dsp,fisheye.css,portallayout.css.dsp,tablelayout.css,box.css.dsp,calendar.css.dsp,grid.css.dsp,combo.css.dsp,input.css.dsp,slider.css.dsp,borderlayout.css.dsp,menu.css.dsp,frozen.css.dsp,paging.css.dsp,listbox.css.dsp,tree.css.dsp,tabbox.css.dsp,a.css.dsp,button.css.dsp,caption.css,groupbox.css.dsp,popup.css.dsp,progressmeter.css.dsp,separator.css.dsp,toolbar.css.dsp,panel.css.dsp,window.css.dsp,ext.css.dsp,norm.css.dsp";
 
    public ThemeProvider() {

    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<String> getThemeURIs(Execution exec, List uris) {
	
		List<String> newUris = new ArrayList<String>();
	 
	    for( String uri : (List<String>) uris ) {
	    	if (uri.startsWith("~./")) uri = "~./" + themeName + "/" + uri.substring(3);
	        newUris.add(uri);
	    }
	 
	    return newUris;
	}

	public String beforeWCS(Execution exec, String uri) {
		return uri;
	}

	public String beforeWidgetCSS(Execution exec, String uri) {

		String fileName = uri.substring(uri.lastIndexOf("/") + 1);
		
		if( !fileName.equalsIgnoreCase("layout.css.dsp") && fileList.indexOf(fileName) != -1 ) {
			uri = "~./" + themeName + "/" + uri.substring(3);
		}
		
		return uri;		
	}

	public int getWCSCacheControl(Execution exec, String uri) {
		return -1;
	}
 
}
