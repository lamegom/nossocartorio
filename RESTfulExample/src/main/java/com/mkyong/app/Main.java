package com.mkyong.app;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import javax.servlet.ServletContext;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Main {
	public ExecutorService producerconsumer =  Executors.newCachedThreadPool();
	public ExecutorService threadPool = Executors.newFixedThreadPool(1);
	public Producer producerThread;
	public Consumer consumerThread;
//	public WebDriver firefoxdriver;
	public WebDriver driver;
	
	
	   final static String USERNAME = "JC049957";  
	    final static String PASSWORD = "spb91744";
	
	
	   private static Main myObj;
	    /**
	     * Create private constructor
	     */
	    private Main(){
	    	
	    	Queue<Pancake> queue = new LinkedList<>();
	        Integer buffer = new Integer(10);  //Important buffer or queue size, change as per need.
//	        Integer producer_buffer = new Integer(10);

	        producerThread = new Producer(queue, buffer, "PRODUCER");
	        consumerThread = new Consumer(queue, buffer, "CONSUMER");

//	        producerThread.start();  
//	        consumerThread.start();
	        
	        producerconsumer.submit(producerThread);
	        producerconsumer.submit(consumerThread);

	        
	    }
	    
	    public void clear(){
	    	myObj = null;
	    }
	    
	    public void useRemoteChrome(String url){
			
			
			java.util.logging.Logger.getLogger("org.openqa.selenium.remote.ProtocolHandshake").setLevel(java.util.logging.Level.OFF); /* comment out to turn off annoying htmlunit warnings */
			
			final ChromeOptions chromeOptions = new ChromeOptions();
	        chromeOptions.addArguments("headless");
	        chromeOptions.addArguments("window-size=1200x600");

	        DesiredCapabilities capability = DesiredCapabilities.chrome();
	        capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

//			    System.out.println("-------------------------------------------------");
				
				 String date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
					System.out.println("Starting Chrome Remote at " + date  );
					
					
//					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),  
					try {
//						driver = new RemoteWebDriver(new URL("http://35.161.203.86:4444/wd/hub"),
						driver = new RemoteWebDriver(new URL(url), 
								 
						        capability);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
		
		public void useHtmlUnit(){
			driver = new HtmlUnitDriver(true);
			
			((HtmlUnitDriver) driver).setJavascriptEnabled(true);
		}
		
		public void useHtmlUnitRemote(){
			DesiredCapabilities capabilities = DesiredCapabilities.htmlUnit();


		    
//			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),  
			try {
//				driver = new RemoteWebDriver(new URL("http://35.161.203.86:4444/wd/hub"),//windows  
//				driver = new RemoteWebDriver(new URL("http://34.212.255.98:4444/wd/hub"),//01
//				driver = new RemoteWebDriver(new URL("http://54.69.24.196:4444/wd/hub"),//04
				driver = new RemoteWebDriver(new URL("http://52.26.164.96:4444/wd/hub"),//03
						capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			((HtmlUnitDriver) driver).setJavascriptEnabled(true);
		}
		
		public void useFirefoxRemote(String url){
//			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
//	    	System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

//	    	System.setProperty("webdriver.gecko.driver","geckodriver.exe");

	    	DesiredCapabilities caps = DesiredCapabilities.firefox();

//		    FirefoxBinary firefoxBinary = caps.
//		    firefoxBinary.addCommandLineOptions("--headless");

		    
//			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),  
			try {
//				driver = new RemoteWebDriver(new URL("http://35.161.203.86:4444/wd/hub"),//windows  
//				driver = new RemoteWebDriver(new URL("http://34.212.255.98:4444/wd/hub"),//01
//				driver = new RemoteWebDriver(new URL("http://54.69.24.196:4444/wd/hub"),//04
//				driver = new RemoteWebDriver(new URL("http://52.26.164.96:4444/wd/hub"),//03
				driver = new RemoteWebDriver(new URL(url),//balancer
						caps);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
		
		public void useFirefox(){
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
	    	System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

	    	System.setProperty("webdriver.gecko.driver","geckodriver.exe");

	    	DesiredCapabilities caps = DesiredCapabilities.firefox();

		    FirefoxBinary firefoxBinary = new FirefoxBinary();
//		    firefoxBinary.addCommandLineOptions("--headless");


		    FirefoxOptions firefoxOptions = new FirefoxOptions();
		    firefoxOptions.setBinary(firefoxBinary);
		    
		    firefoxOptions.addTo(caps);
		    
		    driver = new FirefoxDriver(caps);
		}
	    
	    public void getLoginPage(ServletContext context) throws Exception{
	    	 java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.OFF); /* comment out to turn off annoying htmlunit warnings */
   
			    
			    System.out.println("-------------------------------------------------");
				
				 String date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
					System.out.println("Starting Selenium Webdriver at " + date  );
				
					
					
					
					
					
//					useHtmlUnit();
					
//					useFirefox();
					
					//TODO: web.xml driver path and type
					if(context.getInitParameter("type1").equals("firefox")){
						useFirefoxRemote(context.getInitParameter("url1"));
					}else{
						useRemoteChrome(context.getInitParameter("url1"));
					}

					
					 
						
					 String date2 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
						System.out.println("Selenium Webdriver lauched at " + date2  );
						
						System.out.println("-------------------------------------------------");
		    	
					
					

					

			        
	    }


	    
	    /**
	     * Create a static method to get instance.
	     */
	    public synchronized static Main getInstance(){
	        if(myObj == null){
	            myObj = new Main();
	        }
	        return myObj;
	    }
	    
	    public void getSomeThing(){
	        // do something here
	        System.out.println("I am here....");
	    }
	    
//	    public static void main(String a[]){
//	    	Main st = Main.getInstance();
//	        st.getSomeThing();
//	    }
//	
//	public Main(){
//        //The numbers are just silly tune parameters. Refer to the API.
//        //The important thing is, we are passing a bounded queue.
//        consumer = new ThreadPoolExecutor(1,4,30,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(100));
//
//        //No need to bound the queue for this executor.
//        //Use utility method instead of the complicated Constructor.
//        ExecutorService producer = Executors.newSingleThreadExecutor();
//	}
	
	
    public static void main(String[] args) {

    	Main m = new Main();
    	
//        Runnable produce = new Produce(consumer);
//        producer.submit(produce); 
        
        
    	String[] cpfs = {"18977841801","000.090.963-70","000.127.924-62","000.184.201-33"};
    	for (int i = 0; i < cpfs.length; i++) {
			String cpf = cpfs[i];
			
			Pancake cake = Pan.cook(cpf);
	        Runnable consume = new Consume(cake);
//	        m.consumer.submit(consume);
		}
        

    }
}

class Produce implements Runnable {
    private final ExecutorService consumer;

    public Produce(ExecutorService consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {


    	String[] cpfs = {"18977841801","18977841801","18977841801","18977841801"};
    	for (int i = 0; i < cpfs.length; i++) {
			String cpf = cpfs[i];
			
			Pancake cake = Pan.cook(cpf);
	        Runnable consume = new Consume(cake);
	        consumer.submit(consume);
		}

    }
}

