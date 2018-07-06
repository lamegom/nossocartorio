package com.mkyong.listener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mkyong.app.Main;

public class MyAppServletContextListener
               implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");

		Main m = Main.getInstance();
//		m.login.quit();
//		getThreadByName("PRODUCER").interrupt();
//		getThreadByName("CONSUMER").interrupt();
		
		
		
		m.producerconsumer.shutdown();
		
		
//		m.firefoxdriver.quit();
		m.driver.quit();
		
		
		m.clear();
		
		
		
	}
	
	public Thread getThreadByName(String threadName) {
	    for (Thread t : Thread.getAllStackTraces().keySet()) {
	        if (t.getName().equals(threadName)) return t;
	    }
	    return null;
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");
		Main m = Main.getInstance();
		
		
		try {
			
			ServletContext context = arg0.getServletContext();
			
			m.getLoginPage(context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		Login login = 
//		
//		while(m.login==null){
//			
//			
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
	}
}
