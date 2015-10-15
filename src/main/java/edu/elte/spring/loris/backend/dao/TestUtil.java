package edu.elte.spring.loris.backend.dao;

public class TestUtil extends org.apache.hadoop.conf.Configuration {
	@Override
	public <U> Class<? extends U> getClass(String name, Class<? extends U> defaultValue, Class<U> xface) {
		try {
			Class<?> theClass = getClass(name, defaultValue);
			/*if (theClass != null && !xface.isAssignableFrom(theClass))
				throw new RuntimeException(theClass + " not " + xface.getName());
			else */if (theClass != null)
				return theClass.asSubclass(xface);
			else
				return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
