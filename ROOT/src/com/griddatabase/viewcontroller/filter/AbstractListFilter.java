package com.griddatabase.viewcontroller.filter;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractListFilter <T  extends Serializable> implements ListFilter<T> {

	protected Map<String, List<Filter<?>>> filterMap = new HashMap<String, List<Filter<?>>>();
	protected Map<String, List<Method>> methodCache = new HashMap<String,List<Method>>();
	
	public void addPropertyFilter(String property, Filter<?> filter) {
		if( !filterMap.containsKey(property) ) filterMap.put(property, new ArrayList<Filter<?>>());
		filterMap.get(property).add(filter);
	}
	
	public boolean hasPropertyFilter(String property) { 
		return filterMap.containsKey(property);
	}
	
	public List<Filter<?>> getPropertyFilters(String property) { 
		return filterMap.get(property);
	}
	
	public List<T> filter(List<T> list) {
		
		List<T> filtered = new ArrayList<T>();
		
		for( T d : list ) { 
			if( matches(d) ) filtered.add(d);			
		}
		
		return filtered;
	}
	
	public boolean matches(T data) { 

		boolean matched = true;
		
		for( String property : filterMap.keySet() ) {		
			for( Filter<?> filter : filterMap.get(property) ) { 
				if( !propertyMatches(data, property, filter) ) { 
					matched = false;
					break;
				}
			}
		}		
	
		return matched;
	}
	
	protected boolean propertyMatches(T data, String property, Filter<?> filter) { 
		
		Object value = data;
				
		try {
			
			List<Method> getters = getMethodChain(property);
			
			for( Method getter : getters ) {
				value = getter.invoke(value);
				if( value == null ) break;
			}
			
		} 
		catch (SecurityException e) {
			throw new RuntimeException(e);
		} 
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} 
		catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} 
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} 
		catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		return filter.matches(value);
	}
	
	protected List<Method> getMethodChain(String methodName) throws NoSuchMethodException { 
		
		if( methodCache.containsKey(methodName) ) return methodCache.get(methodName);
		
		List<Method> methods = getMethodChain(getListClass(), methodName);
		methodCache.put(methodName, methods);
		
		return methods;
	}
	
	protected List<Method> getMethodChain(Class<?> clazz, String methodName) throws NoSuchMethodException { 
	
		String[] methodTokens = methodName.split("\\.");
		
		String thisMethod;
		
		thisMethod = methodTokens[0];
		String getterString = "get" + thisMethod.substring(0, 1).toUpperCase() + thisMethod.substring(1);

		Method method = null;
		List<Method> chain = new ArrayList<Method>();
		
		while( method == null && clazz != null ) { 
			
			try {
				method = clazz.getDeclaredMethod(getterString);
			} 
			catch (SecurityException e) {
				throw new RuntimeException(e);
			} 
			catch (NoSuchMethodException e) {
				clazz = clazz.getSuperclass();
			}
			
		}
		
		if( method == null ) throw new NoSuchMethodException(getterString);

		chain.add(method);
		
		if( methodTokens.length > 1 ) {
			String nextMethod = methodName.substring(methodName.indexOf(".")+1);					
			chain.addAll(getMethodChain(method.getReturnType(), nextMethod));
		}
		
		return chain;
	}
	
	protected abstract Class<?> getListClass();
	
}
