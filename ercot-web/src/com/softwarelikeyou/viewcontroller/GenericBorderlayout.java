package com.softwarelikeyou.viewcontroller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Borderlayout;

public class GenericBorderlayout extends Borderlayout implements AfterCompose {

	static final long serialVersionUID = 1L;

	static final Pattern[] SUPPORTED_BROWSER_EXPRESSIONS = new Pattern[] {
		Pattern.compile("^.*(MSIE)\\s(\\d+)\\..*$"),
		Pattern.compile("^.*(Firefox)/(\\d+)\\..*$"),
		Pattern.compile("^.*(Chrome)/(\\d+)\\..*$")
	};


	static final Map<String,Integer> MIN_BROWSER_VERSIONS = new HashMap<String,Integer>();

	static {
		MIN_BROWSER_VERSIONS.put("MSIE", 8);
		MIN_BROWSER_VERSIONS.put("Firefox", 4);
		MIN_BROWSER_VERSIONS.put("Chrome", 11);
	}

	public static final String SHOW_UNSUPPORTED_BROWSER_TIP = "showUnsupportedBrowserTip";

	public static final String BROWSER_CHECK_URL = "/browser-check.zul";

	public void afterCompose() {

		Components.wireVariables(this, this);
		Components.addForwards(this, this);

	//	doBrowserCheck();
	}

	public void doBrowserCheck() {

		Boolean displayTip = Boolean.valueOf(SessionUtil.getUserData(SHOW_UNSUPPORTED_BROWSER_TIP).getValue());
		if( displayTip != null && !displayTip ) return;

		String userAgentString = Executions.getCurrent().getUserAgent();

		for( Pattern pattern : SUPPORTED_BROWSER_EXPRESSIONS ) {

			Matcher matcher = pattern.matcher(userAgentString);

			if( matcher.matches() ) {

				String userAgent = matcher.group(1);
				String verString = matcher.group(2);
				int version = Integer.parseInt(verString);

				if( MIN_BROWSER_VERSIONS.containsKey(userAgent) && version > MIN_BROWSER_VERSIONS.get(userAgent) ) {
					return;
				}

			}
		}

		ToolTipWindow window = (ToolTipWindow) Executions.createComponents(BROWSER_CHECK_URL, null, null);
		window.setUserDataKey(SHOW_UNSUPPORTED_BROWSER_TIP);
		window.doHighlighted();
	}

}