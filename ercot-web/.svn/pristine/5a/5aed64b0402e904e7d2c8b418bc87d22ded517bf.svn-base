package com.softwarelikeyou.client.component.zul;

public class SimpleLabel extends org.zkoss.zk.ui.HtmlBasedComponent {
	private static final long serialVersionUID = 1L;
	
	private String _value = ""; // a data member
	
	public String getValue() {
	    return _value;
	}
	
	public void setValue(String value) {
	    if (!_value.equals(value)) {
	        _value = value;
	        smartUpdate("value", _value);
	    }
	}
	
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer) throws java.io.IOException {
	    super.renderProperties(renderer);
	    render(renderer, "value", _value);
	}
}