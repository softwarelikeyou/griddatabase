package com.griddatabase.viewcontroller.ercot.rtlmp;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Composer;

@SuppressWarnings("rawtypes")
public class LMPChartComposer implements Composer {

	@Override
	public void doAfterCompose(Component arg0) throws Exception {
		
	}
	
	@Command("showMessage") 
	@NotifyChange("message")
	public void onShowMessage(
			@BindingParam("msg") String message){
		System.out.println(message);
	}

}
