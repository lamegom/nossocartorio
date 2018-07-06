package com.mkyong.app;

import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.WebDriver;

public class Pancake extends Thread{
	
	public String cpf;
	public String result;
	public boolean cooked = false;
	public boolean cooking = false;
	public boolean hot = false;
	public long startTime;
	public LoginPage login;
	public boolean skipFirst;
	public String tabName;
	
	public Pancake(String cpf){
		
			this.cpf = cpf;
			
			
			long startTime = System.currentTimeMillis();
			this.startTime = startTime;
			
			this.cooking = false;
			this.result = null;
			
	//		String script = "window.open('','');";
			
			

		 
		 
	}

	public void run() {
		



		
		synchronized(login.getDriver()){

			cooking = true;
			while(!hot){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			String date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
			
			
			System.out.println("");
			System.out.println("Pancake.eat() " + cpf + " at " + date  );

			
	//		result = run(cpf);
			result = get(cpf);
//			System.out.println(result);
			
			cooked  = true;
			cooking = false;

			
		}
		

		
		
		
		
	}
	
	
	public synchronized String get(String cpf){
		
		
		
		String res = "";
		
		login.percentage = 0;
		 
//		LoginPage login = m.checkSessionExpired();
		
//		String originalHandle = login.getDriver().getWindowHandle();
//    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
////    	
//    	String tabName = cpf + timestamp.toString();
//    	ArrayList<String> tabs = new ArrayList<String>(login.getDriver().getWindowHandles());
////    	login.getDriver().switchTo().window(tabs.get(0));
//    	
//    	String script = "window.open('','" + tabName + "');";
////		String script = "window.open('','');";
//    	login.getJs().executeScript(script);
//    	  
////    	String tabName = login.getDriver().getWindowHandle();
		
		
		login.getDriver().switchTo().window(tabName);
		
		login.percentage = 1;

		
		try { 
			if(skipFirst){
				throw new Exception("skipFirst: " + skipFirst);
			}

			login.loadLoginPage(cpf);
			
	//		System.out.println(m.login.getUrl());
			
			
			
			if("https://portalcredito.bb.com.br/portal/autenticar".equals(login.getUrl())){

				login.newLoginAs(USERNAME, PASSWORD);
//				if(!"https://portalcredito.bb.com.br/portal/home".equals(login.getUrl())){
//					res = "Error... Portal de Credito BB outage.";
//					login.percentage = 10;
//					login.closeTab();
//					login.getDriver().switchTo().window(tabs.get(0));
//		//			String script2 = "window.close('','" + tabName + "');";
//		//	    	m.login.getJs().executeScript(script2);
//		//			printUsage();
//					return res;
					
					
//					login.analyse(cpf);
					
					
//				}
			}
			

		

	
			login.getPage();
		

			res = login.verify(cpf);
			
//			login.getDriver().switchTo().window(tabName);
			login.closeTab();
			
			return res;
			
		} catch (Exception e) {
//			res = "Error... ";
			e.printStackTrace();
//			System.out.println(e.getCause().getLocalizedMessage());
//			login.percentage = 10;
//			login.closeTab();
//			login.getDriver().switchTo().window(tabs.get(0));
//			String script2 = "window.close('','" + tabName + "');";
//	    	m.login.getJs().executeScript(script2);
//			printUsage();
			
			try {

				login.loadLoginPage();

				login.loginAs(USERNAME, PASSWORD);
				

				res = login.analyse(cpf);
				
//				login.getDriver().switchTo().window(tabName);
				login.closeTab();
				
				
				return res;
				
			} catch (Exception e1) {
				e1.printStackTrace();
				res = "Error... ";
				
				try {
//					login.getDriver().switchTo().window(tabName);
//					login.closeTab();
				
//					login.getDriver().switchTo().window(tabs.get(0));
				
				
				
//					System.out.println("");
//					System.out.println("1o. portal de credito");
//					System.out.println("------------------------");
//	//				e.printStackTrace();
//					System.out.println(e.getCause().getMessage());
//					
//					
//					System.out.println("");
//					System.out.println("2o. portal de credito");
//					System.out.println("------------------------");
//	//				e1.printStackTrace();
//					System.out.println(e1.getCause().getMessage());
					
//					login.getDriver().switchTo().window(tabName);
					login.closeTab();
					
					return res;
				
				} catch (Exception e2) {
//					System.out.println(e2.getCause().getMessage());
//					e2.printStackTrace();
				}
				
				
				
				
				
				
			}finally{
				
			}
			
			
			
			
		}finally{
			

		}
		
//		login.getDriver().switchTo().window(tabName);
//		try {
//			login.closeTab();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		login.getDriver().switchTo().window(tabs.get(0));
		
		return res;

//		String script3 = "window.close('','" + tabName + "');";
//    	m.login.getJs().executeScript(script3);
    	
//    	login.closeTab();
//		login.getDriver().switchTo().window(tabs.get(0));
		
//		result = res;
//		cooked  = true;

        
//        m.login.percentage = 100;

	}
	
	   final static String USERNAME = "JC049957";  
	    final static String PASSWORD = "spb91744";
		
		 
		 private static void printUsage() {
			 Pragati p = new Pragati();
			 
			 long startCPUTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			 long start = System.nanoTime();
	         int cpuCount = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
			 
			 float cpuPercent = p.calcCPU(startCPUTime, start, cpuCount);
			 System.out.println("");
			 System.out.println("CPU USAGE : " + cpuPercent/10 + " % ");
			 
			 int mb = 1024*1024;
	         int gb = 1024*1024*1024;
			 /* PHYSICAL MEMORY USAGE */
//	         System.out.println("\n**** Sizes in Mega Bytes ****\n");
	        com.sun.management.OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
	        //RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
	        //operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	        com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean)
	        java.lang.management.ManagementFactory.getOperatingSystemMXBean();
			 
			 long physicalMemorySize = os.getTotalPhysicalMemorySize();
	         System.out.println("PHYSICAL MEMORY DETAILS");
	         System.out.println("total physical memory : " + physicalMemorySize / mb + "MB ");
	         long physicalfreeMemorySize = os.getFreePhysicalMemorySize();
	         System.out.println("total free physical memory : " + physicalfreeMemorySize / mb + "MB");
			}

}
