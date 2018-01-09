package com.softwarelikeyou.viewcontroller;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Window;

import com.softwarelikeyou.client.component.zul.ImageLabel;

public class ErrorMessageWindow extends Window implements AfterCompose {

	static final long serialVersionUID = 1L;
	
	protected ImageLabel primaryCause;
	protected Grid errorMessageGrid;

	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);		
	}
	
	public void setErrorMessages(ListModel messages) { 
		errorMessageGrid.setModel(messages);
	}

	public void setPrimaryCause(String primaryCause) { 
		this.primaryCause.setLabel(primaryCause);
	}
	
	public void onClick$okButton(Event event) { 
		this.detach();
	}	
}
