package com.griddatabase.viewcontroller;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Captcha;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

import com.griddatabase.viewcontroller.user.RandomStringGenerator;

public class Contact  extends Div implements AfterCompose {
	private static final long serialVersionUID = 1L;

	protected AnnotateDataBinder binder;

	private RandomStringGenerator rsg = new RandomStringGenerator(4);

	private String captcha = rsg.getRandomString();
	private Textbox captchaTextbox;
	
	private Label label;
	private Captcha captchaComp;
	private Button contactButton;
	
    public String getCaptcha() {
        return captcha;
    }
 
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
    
	@SuppressWarnings("deprecation")
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		binder = new AnnotateDataBinder(this);
		binder.loadAll();
	}
	
	public void onClick$contactButton(Event event) {
		 captchaTextbox.getValue();
		 if (captchaTextbox.getValue().equals(new String(captcha.getBytes()))) {
			 captchaComp.setVisible(false);
			 captchaTextbox.setVisible(false);
			 contactButton.setVisible(false);
			 label.setValue("Steve.Thomas@griddatabase.com or 714-444-3489");
		 }
	}
}
