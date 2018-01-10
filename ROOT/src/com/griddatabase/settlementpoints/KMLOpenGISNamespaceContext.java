package com.griddatabase.settlementpoints;

import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;

public class KMLOpenGISNamespaceContext implements NamespaceContext {

	@Override
	public String getNamespaceURI(String prefix) {
	    return "http://www.opengis.net/kml/2.2";
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
