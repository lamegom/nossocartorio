package com.mkyong.app;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CustomFirefoxDriver extends FirefoxDriver implements Cloneable {



	public CustomFirefoxDriver(DesiredCapabilities caps) {
		super(caps);
	}

		@Override
	    public CustomFirefoxDriver clone() {
	        final CustomFirefoxDriver clone;
	        try {
	            clone = (CustomFirefoxDriver) super.clone();
	        }
	        catch (CloneNotSupportedException ex) {
	            throw new RuntimeException("superclass messed up", ex);
	        }

	        return clone;
	    }


}
