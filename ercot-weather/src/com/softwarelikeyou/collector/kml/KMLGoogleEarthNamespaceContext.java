package com.softwarelikeyou.collector.kml;

import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;

public class KMLGoogleEarthNamespaceContext implements NamespaceContext {

	@Override
	public String getNamespaceURI(String prefix) {
	    return "http://earth.google.com/kml/2.1";
	}

	@Override
	public String getPrefix(String namespaceURI) {
		return "kml";
	}

	@Override
	public Iterator<?> getPrefixes(String namespaceURI) {
		throw new UnsupportedOperationException();
	}
}
