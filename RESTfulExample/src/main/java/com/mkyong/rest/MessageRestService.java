package com.mkyong.rest;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.management.MBeanServerConnection;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import com.mkyong.app.Lead;
import com.mkyong.app.LoginPage;
import com.mkyong.app.Main;
import com.mkyong.app.Pan;
import com.mkyong.app.Pancake;
import com.mkyong.app.RunShellCommandFromJava;
import com.mkyong.app.dao.JDBCPreparedStatement;
import com.mkyong.app.dao.SimpleCacheManager;
import com.sun.management.OperatingSystemMXBean;

import me.tongfei.progressbar.ProgressBar;

//http://localhost:8080/RESTfulExample/rest/message/hello%20world
@Path("/message")
public class MessageRestService {

	@Context
	private ServletContext context;

	@GET
	@Path("/{cpf}")
	@Produces(MediaType.TEXT_HTML+ ";charset=UTF-8") 
	public Response getValidation(@HeaderParam("user-agent") String userAgent, @PathParam("cpf") String cpf) {
		return printMessage(userAgent, cpf, false);
	}

	
	@GET
	@Path("/{cpf}/{skipFirst}")
	@Produces(MediaType.TEXT_HTML+ ";charset=UTF-8") 
	public Response printMessage(@HeaderParam("user-agent") String userAgent, @PathParam("cpf") String cpf, @PathParam("skipFirst") boolean skipFirst) {
		
		
//		RunShellCommandFromJava r = new RunShellCommandFromJava();
		ProgressBar pb = null ;
		Pancake cake = null;

		
		 MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();

		 OperatingSystemMXBean osMBean = null;

		 try {
			osMBean = ManagementFactory.newPlatformMXBeanProxy(
				 mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		 long nanoBefore = System.nanoTime();
		 long cpuBefore = osMBean.getProcessCpuTime();
		 
		 long startTime = System.currentTimeMillis();
		
		 String cached = (String) SimpleCacheManager.getInstance().get(cpf);
			
			if(cached != null){
				return Response.status(200).entity(cached).header("Access-Control-Allow-Origin", "*").build();
			}
			
			String result = "";
		
		try{
			
			
			
			Main m = Main.getInstance();
			
			
			
//			WebDriver firefoxDriver = ((CustomFirefoxDriver) m.firefoxdriver).clone();
			
			WebDriver firefoxDriver =  m.driver;
			
			cake = Pan.cook(cpf);
			
			
			
			cake.login = new LoginPage(firefoxDriver);
			
			
			
			cake.skipFirst = skipFirst;
			
			m.producerThread.addCake(cake);
			
//			m.threadPool.submit(cake);
			
			while(!cake.cooking){
				
				Thread.sleep(500);

			}
			
			
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			cake.tabName = cpf + timestamp.toString();
			
			ArrayList<String> tabs = new ArrayList<String>(cake.login.getDriver().getWindowHandles());
	    	
	    	
	    	String script = "window.open('','" + cake.tabName + "');";
//	    	
			cake.login.getDriver().switchTo().window(tabs.get(0));
	    	
	    	cake.login.getJs().executeScript(script);
	    	  
	    	cake.login.getDriver().switchTo().window(cake.tabName );
	    	
	    	cake.hot = true;
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	pb = new ProgressBar(cake.cpf, 100);
			pb.start(); 
//			
//			
//			
////		    while(cake.result==null){
////		    	pb.stepTo(cake.login.percentage);
////		    	pb.maxHint(10);
////		    	Thread.sleep(100);
////		    }
//		    
////			pb = new ProgressBar(cake.cpf, 100);
//			boolean progressBarStarted = false;
	        for (int second = 0;; second++) {
	            if (second >= 500 * 2){
//	                System.out.println("timeout");
//	                setResult("TIMEOUT!");
	            	cake.cooked  = true;
	    	        cake.cooking = false;

	                cake.result = "TIMEOUT!";
	                System.out.println("Forcing shutdown...");
	                
//	                String tabName = cake.cpf + cake.startTime;
//	    	        getThreadByName(tabName).interrupt();
	                
//	                throw new Exception("TIMEOUT!");
	            }
	            try {
	                if (cake.result != null){
	                	
	                	
	                	
//	    				cake.login.getDriver().switchTo().window(cake.tabName);
//	    				try {
//	    					cake.login.closeTab();
//	    				} catch (Exception e) {
//	    					// TODO Auto-generated catch block
//	    					e.printStackTrace();
//	    				}
//	    				login.getDriver().switchTo().window(tabs.get(0));

	    				pb.stop();
	    				
	                	
	                    break;
	                }
	                }
	            catch (Exception e) {

	            }
	            try {
//	                	if (cake.cooking){
//	            			
//	            			if(!progressBarStarted){
//	            				pb.start();
//	            				progressBarStarted = true;
//	            			}
//	            		}
	    		   
	    		    	pb.stepTo(cake.login.percentage);
	    		    	pb.maxHint(10);

	            	
	    		    	Thread.sleep(500);
	    		    	
		   		} catch (InterruptedException e) {
		   			pb.stop();
		   			cake.result += "Error...";
		   			cake.cooked  = true;
	    	        cake.cooking = false;
	    	        
		   			e.printStackTrace();
		   		}
	        
			
			
		result += cake.result;
		
		}
			

		}catch(Exception e){
			
			result += "Error...";
			e.printStackTrace();
			
			
		}finally{
			
	
			
//	        printUsage();
			 long cpuAfter = osMBean.getProcessCpuTime();
			 long nanoAfter = System.nanoTime();

			 long percent;
			 if (nanoAfter > nanoBefore)
			  percent = ((cpuAfter-cpuBefore)*100L)/
			    (nanoAfter-nanoBefore);
			 else percent = 0;

			 System.out.println("Cpu usage: "+percent+"%");
	        
	        long stopTime = System.currentTimeMillis();
	        long elapsedTime = stopTime - cake.startTime;
	        
//	        long elapsedTime = stopTime - startTime;
	        
//	        System.out.println(elapsedTime);
	        
	        long second = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
	        long minute = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
	        long hour = TimeUnit.MILLISECONDS.toHours(elapsedTime);
	        elapsedTime -= TimeUnit.SECONDS.toMillis(second);
	        System.out.println("Finished + " + cpf  + " in " + String.format("%02d:%02d:%02d:%d", hour, minute, second, elapsedTime));
	        System.out.println("-------------------------------------------------");
			
//	        cake.login = null;
//	        m.firefoxdriver.close();
//			synchronized (m.consumerThread.queue) {
//
//					m.consumerThread.queue.notifyAll(); 
//					
//				}
	        cake.interrupt();
			cake = null;
			System.gc();
//			m.threadPool.shutdownNow();
		}

		
		result = result.replaceAll("script", "script-dummy");
		
		result = result.replaceAll("null", "");
		
		SimpleCacheManager.getInstance().put(cpf, result);
		
		JDBCPreparedStatement dao = new JDBCPreparedStatement();
		dao.insertRecordIntoTable(cpf, result, userAgent);
		
		
		
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}
	
	public Thread getThreadByName(String threadName) {
	    for (Thread t : Thread.getAllStackTraces().keySet()) {
	        if (t.getName().equals(threadName)) return t;
	    }
	    return null;
	}
	
	private static boolean loading = true;
	private synchronized void loading(String msg) throws IOException, InterruptedException {
//	    System.out.println(msg);
	    Thread th = new Thread() {
	        @Override
	        public void run() {
	            try {
	                System.out.write("\r|".getBytes());
//	                long startTime = System.currentTimeMillis();
	                while(loading) {
//	                    System.out.write("-".getBytes());
	                    Thread.sleep(500);
	                }
//	                long stopTime = System.currentTimeMillis();
//	    	        long elapsedTime = stopTime - startTime;
////	    	        System.out.println(elapsedTime);
//	    	        
//	    	        long second = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
//	    	        long minute = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
//	    	        long hour = TimeUnit.MILLISECONDS.toHours(elapsedTime);
//	    	        elapsedTime -= TimeUnit.SECONDS.toMillis(second);
//	    	        System.out.write(("| Done in " + String.format("%02d:%02d:%02d:%d", hour, minute, second, elapsedTime) + "\r\n").getBytes());

	                
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    };
	    th.start();
	}

	
	@GET
	@Path("leads/{param}")
	@Produces(MediaType.TEXT_HTML+ ";charset=UTF-8") 
	public Response getLeads(@PathParam("param") String cpf) {	
		
		String result = "";
		
		JDBCPreparedStatement dao = new JDBCPreparedStatement();
		try {
			result = dao.selectRecordsFromTable(cpf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}
	
	@GET
	@Path("lead/{param}")
	@Produces(MediaType.TEXT_HTML+ ";charset=UTF-8") 
	public Response getLead(@PathParam("param") String cpf) {	
		
//		try {
//			Thread.sleep(30000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
		String result = "";
		
		JDBCPreparedStatement dao = new JDBCPreparedStatement();
		try {
			result = (dao.selectLastRecordsFromTable(cpf)).toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Response.status(200).entity(result).build();

	}
	
	@GET
	@Path("proposta/{cpf}/{email}")
	@Produces(MediaType.TEXT_HTML+ ";charset=UTF-8") 
	public Response getLeadProposta(@PathParam("cpf") String cpf, @PathParam("email") String email) {	
		
//		try {
//			Thread.sleep(30000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
		JSONObject result = null;
		
		JDBCPreparedStatement dao = new JDBCPreparedStatement();
		try {
			result = dao.selectLastRecordsFromTable(cpf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(result.get("analise").equals("Aprovado")){
			
			
			Lead lead = new Lead(email,context);
			lead.start();
			
			
			result.put("analise", "OK");
		}
		
		
		
		return Response.status(200).entity(result.toString()).header("Access-Control-Allow-Origin", "*").build();

	}
	
	@GET
	@Path("debito/{param}")
	public Response getSimulation(@PathParam("param") String valor) {	
		
		String result = "";
		RunShellCommandFromJava r = new RunShellCommandFromJava();

		try{
			result += r.simulate(valor);
//			result  += runSelenium(msg);
		}catch(Exception e){
			
			result += "Error...";
			e.printStackTrace();
		}
		

		
		
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}
	
	@GET
	@Path("all")
	@Produces(MediaType.TEXT_HTML+ ";charset=UTF-8") 
	public Response getAllLeads() {	
		
		String result = "";
		
		JDBCPreparedStatement dao = new JDBCPreparedStatement();
		try {
			result = dao.selectAllRecordsFromTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}
	

	
//    final static String USERNAME = "JC049957";  
//    final static String PASSWORD = "spb91744";
//	
//public String runSelenium(String cpf){
//    	
//    	System.setProperty("javax.net.ssl.trustStore","jssecacerts");
//    	
//    	java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF); /* comment out to turn off annoying htmlunit warnings */
//
//    	System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
//    	System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
////    	
////    	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
//    	System.setProperty("webdriver.gecko.driver","geckodriver");
//    	
//
//    	 DesiredCapabilities caps = new FirefoxOptions().setLogLevel(Level.OFF).addTo(DesiredCapabilities.firefox());
//    	    caps.setCapability("acceptInsecureCerts", true);
//    	    WebDriver firefoxdriver = new FirefoxDriver(caps);
//    	
//    	LoginPage login = new LoginPage(firefoxdriver);
//
//        login.loadLoginPage();
//        login.loginAs(USERNAME, PASSWORD);
//        
////        String cpf = args[0]!=null?args[0]:"18977841801";
//        
////        login.post();
//        String response = login.analyse(cpf);
//        
//        login.quit();
//        
//        return response;
//    }
	
    final static String USERNAME = "JC049957";  
    final static String PASSWORD = "spb91744";
	
	
//	 public String run(String cpf) throws Exception{
//		 long startTime = System.currentTimeMillis();
//	    	System.setProperty("javax.net.ssl.trustStore","jssecacerts");
//
//	    	java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.OFF); /* comment out to turn off annoying htmlunit warnings */
//
//	    	System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
//	    	System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
//
//	    	System.setProperty("webdriver.gecko.driver","geckodriver.exe");
//
//	    	DesiredCapabilities caps = new FirefoxOptions().setLogLevel(Level.OFF).addTo(DesiredCapabilities.firefox());
//
//		    FirefoxBinary firefoxBinary = new FirefoxBinary();
//		    firefoxBinary.addCommandLineOptions("--headless");
//
//
//		    FirefoxOptions firefoxOptions = new FirefoxOptions();
//		    firefoxOptions.setBinary(firefoxBinary);
//		    
//		    firefoxOptions.addTo(caps);
//		    
//		    WebDriver firefoxdriver = new FirefoxDriver(caps);
//	    	
//	    	LoginPage login = new LoginPage(firefoxdriver);
//
//	        login.loadLoginPage(cpf);
//	        login.newLoginAs(USERNAME, PASSWORD);
//	        
////	        String cpf = args[0]!=null?cpf:"18977841801";
////	        String cpf = "18977841801";
////	        login.post();
//	        String result = login.verify(cpf);
//	        
//	        login.quit();
//	        
////	        printUsage();
//	        
//	        long stopTime = System.currentTimeMillis();
//	        long elapsedTime = stopTime - startTime;
////	        System.out.println(elapsedTime);
//	        
//	        long second = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
//	        long minute = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
//	        long hour = TimeUnit.MILLISECONDS.toHours(elapsedTime);
//	        elapsedTime -= TimeUnit.SECONDS.toMillis(second);
//	        System.out.println("Finished in " + String.format("%02d:%02d:%02d:%d", hour, minute, second, elapsedTime));
//	        System.out.println("-------------------------------------------------");
//	        return result;
//	    }
	 
//	 private void printUsage() {
		 


		 // Call an expensive task, or sleep if you are monitoring a remote process


		 
//		 JVMCPUUsage jvmusage = new JVMCPUUsage();
//		 try {
//			 jvmusage.openMBeanServerConnection();
//			 jvmusage. getMXBeanProxyConnections();
//		} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//		}
//		 
//		 float cpuUsage = jvmusage.getJvmCpuUsage();
//		 
//		 System.out.println("CPU USAGE : " + cpuUsage + " % ");
		 
//		 Pragati p = new Pragati();
//		 
//		 long startCPUTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
//		 long start = System.nanoTime();
//         int cpuCount = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
//		 
//		 float cpuPercent = p.calcCPU(startCPUTime, start, cpuCount);
//		 System.out.println("CPU USAGE : " + cpuPercent/10 + " % ");
		 
//		 int mb = 1024*1024;
//         int gb = 1024*1024*1024;
//		 /* PHYSICAL MEMORY USAGE */
////         System.out.println("\n**** Sizes in Mega Bytes ****\n");
//        com.sun.management.OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
//        //RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
//        //operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//        com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean)
//        java.lang.management.ManagementFactory.getOperatingSystemMXBean();
//		 
//		 long physicalMemorySize = os.getTotalPhysicalMemorySize();
//         System.out.println("PHYSICAL MEMORY DETAILS");
//         System.out.println("total physical memory : " + physicalMemorySize / mb + "MB ");
//         long physicalfreeMemorySize = os.getFreePhysicalMemorySize();
//         System.out.println("total free physical memory : " + physicalfreeMemorySize / mb + "MB");
//		}

}