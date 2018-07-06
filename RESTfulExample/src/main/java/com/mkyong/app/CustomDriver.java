package com.mkyong.app;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CustomDriver extends RemoteWebDriver implements Cloneable {



	public CustomDriver(DesiredCapabilities caps) {
		super(caps);
	}

		@Override
	    public CustomDriver clone() {
	        final CustomDriver clone;
	        try {
	            clone = (CustomDriver) super.clone();
	        }
	        catch (CloneNotSupportedException ex) {
	            throw new RuntimeException("superclass messed up", ex);
	        }

	        return clone;
	    }


}
