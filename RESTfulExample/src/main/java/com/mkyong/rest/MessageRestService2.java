package com.mkyong.rest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.ecwid.maleorang.method.v3_0.lists.members.MemberInfo;
import com.mkyong.app.ExistingMethodExample;
import com.mkyong.app.Lead;
import com.mkyong.app.Lead2;
import com.mkyong.app.TwilioTest;
import com.mkyong.app.TwilioTest2;
import com.mkyong.app.dao.JDBCPreparedStatement;
import com.mkyong.bean.InbuondLead;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.voice.Say.Language;

//http://localhost:8080/RESTfulExample/rest/message/hello%20world
@Path("/hook")
public class MessageRestService2 {

	@Context
	private ServletContext context;
	
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
	@Path("resultado/{email}")
//	@Produces(MediaType.TEXT_HTML+ ";charset=UTF-8") 
	public void getLeadResultado(@PathParam("email") String email) {
		System.out.println("MessageRestService2.getLeadResultado() " + email);
		
		Lead lead = new Lead(email, context);
		lead.resultado = true;
		lead.start();
		
		
	}
	
	
	@GET
	@Path("proposta/{cpf}/{email}")
//	@Produces(MediaType.TEXT_HTML+ ";charset=UTF-8") 
	public JSONObject getLeadProposta(@PathParam("cpf") String cpf, @PathParam("email") String email) {	
		
		System.out.println("MessageRestService2.getLeadProposta() " + cpf + " : " + email);
		
		
		JSONObject result = null;
		
		JDBCPreparedStatement dao = new JDBCPreparedStatement();
		try {
			result = dao.selectLastRecordsFromTable(cpf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(result.get("analise").equals("Aprovado")){
			
			
			Lead lead = new Lead(email, context);
			lead.start();
			
			
//			result.put("analise", "OK");
		}else if(result.get("analise").equals("Cadastrais")){
			
			
			Lead2 lead2 = new Lead2(email, context);
			lead2.start();
			
			
//			result.put("analise", "OK");
		}
		
		
		
		return result;
		
//		return Response.status(200).entity(result.toString()).header("Access-Control-Allow-Origin", "*").build();

	}

	
	@GET
	@POST
	@Path("mailchimp")
	public void getWebhook(final MultivaluedMap<String, String> formParams) throws Exception {

		
		 for(String param : formParams.keySet()){
//		    	System.out.println(param + " --> " + formParams.getFirst(param));
		    	String res = param + "\"}";
		    	//System.out.println(res);
				ObjectMapper mapper = new ObjectMapper();
				InbuondLead cliente = mapper.readValue(res, InbuondLead.class);
				
				//System.out.println(cliente);
				String date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
				System.out.println("MessageRestService2.getWebhook() -> " + cliente.getCpf() + " : " + date);
					
			    ScheduledExecutorService scheduler
			    = Executors.newSingleThreadScheduledExecutor();

					Runnable task = new Runnable() {
						public void run() {
							
							ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
					    	mailchimp.setEmail(cliente.getEmail());
					    	mailchimp.setNome(cliente.getNome());
					    	mailchimp.setSobrenome(cliente.getSobrenome());
					    	mailchimp.setPhone(cliente.getPhone());
					    	mailchimp.setCpf(cliente.getCpf());
					    	mailchimp.setAnalise("N/A");
					    	mailchimp.start();
				
				}
			};

			int delay = 1;
			scheduler.schedule(task, delay, TimeUnit.MINUTES);
			scheduler.shutdown();
			
			
			System.out.println("Scheduling mailchimp insertion to " + cliente.getCpf() + " at " + date + " for " + delay + " minutes");
			
			
			getResult(cliente.getCpf(), cliente.getEmail());
		    
		 
		 }
		

	}
	
	@GET
	@Path("call/{phone}")
	@Produces(MediaType.TEXT_HTML+ ";charset=UTF-8") 
	public Response call(@PathParam("phone") String phone) {
		
		System.out.println("MessageRestService2.call() " + phone);
		
		

		 TwilioTest t = new TwilioTest(); 
		 String sid = "";
//		 sid = t.dial(phone);
		 
		 return Response.status(200).entity(sid).header("Access-Control-Allow-Origin", "*").build();
		  
	}
	
	@GET
	@Path("call2/{phone}")
	public Response call2(@PathParam("phone") String phone) {
		
		System.out.println("MessageRestService2.call2() " + phone);
		
		

		 TwilioTest2 t = new TwilioTest2(); 
		 String sid = "";
//		 sid = t.dial(phone);
	
		 return Response.status(200).entity(sid).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Path("call3")
	@Produces(MediaType.APPLICATION_XML+ ";charset=UTF-8") 
	public Response call3(@QueryParam("Digits") String cpf) throws TwiMLException {
//		Digits=18977841801
		System.out.println("MessageRestService2.call()3 " + cpf);
		
		
//		JSONObject result = null;
//		
//		JDBCPreparedStatement dao = new JDBCPreparedStatement();
//		try {
//			result = dao.selectLastRecordsFromTable(cpf);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		StringBuffer sb = new StringBuffer();
//		
//		sb.append("<Response>");
//		sb.append("<Say voice=\"alice\" language=\"pt-BR\">A análise do Banco do Brasil retornou : " + (String) result.get("analise") + "</Say>");
//		sb.append("<Say voice=\"alice\" language=\"pt-BR\">Aguarde que estamos tranferindo sua ligação ... </Say>");
//		sb.append("<Dial callerId=\"+551149502277\">");
//		sb.append("<Number>");
//		sb.append("5511981084320");
//		sb.append("</Number>");
//		sb.append("</Dial>");
//		sb.append("</Response>");
		
		
		Say say = new Say.Builder("Olá! Cartório Postal Brooklin ").voice(Say.Voice.ALICE)
	            .language(Language.PT_BR).loop(2).build();
	        VoiceResponse response = new VoiceResponse.Builder().say(say).build();

	        try {
	            System.out.println(response.toXml());
	        } catch (TwiMLException e) {
	            e.printStackTrace();
	        }
		
		
		return Response.status(200).entity(response.toXml()).header("Access-Control-Allow-Origin", "*").build();
		  
	}
	
	@GET
	@Path("call4")
	@POST 
	public void call4(@QueryParam("AnsweredBy") String answeredBy, @QueryParam("CallStatus") String callStatus) {
	    
		System.out.println("MessageRestService2.call4()");
		//AnsweredBy=human
		System.out.println("AnsweredBy: " + answeredBy);
		//CallStatus=completed
		System.out.println("CallStatus: " + callStatus);
	}
	
	@GET
	@Path("members")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=UTF-8") 
	public List<MemberInfo> members() {

//		System.out.println("MessageRestService2.members()");
		
		
		ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");

		
		return mailchimp.getList();
		  
	}
	
	
	@POST 
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8")
	@GET
	@Path("create")
	public void create(final MultivaluedMap<String, String> formParams) {
//	    for(String param : formParams.keySet()){
//	    	
//	    	System.out.println(param + " : " + formParams.getFirst(param));
//	    	
//	    }
	    
	    
	    String email = formParams.getFirst("data[email]");
	    String cpf = formParams.getFirst( "data[merges][MMERGE6]");
	    
	    
	    getResult(cpf, email);
	    
	    
	    
	}
	
	
	private final static String USER_AGENT = "Mozilla/5.0";
	
	public void getResult(String cpf, String email){
		
		String date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
	    
//	    System.out.println("MessageRestService2.create() - cpf: " + cpf + " : email: " + email + " - " + date);
	    if(cpf!=null){
	    
	    	ScheduledExecutorService scheduler
	    		= Executors.newSingleThreadScheduledExecutor();

			Runnable task = new Runnable() {
				public void run() {
					String analise = "N/A";
					try{
					   JSONObject result = getLeadProposta(cpf, email);
					   analise = (String) result.get("analise");
					   String nomeCompleto = (String) result.get("nomeCompleto");
					   
					   if(analise.equals("Erro")){
						   throw new Exception("analise = Erro");
					   }
					   
					   
					   setCliente(email, nomeCompleto, analise);
					   
					   ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
				    	mailchimp.setEmail(email);
				    	mailchimp.setAnalise(analise);
				    	mailchimp.start();
					   
					}catch(Exception e){
						e.printStackTrace();
						MessageRestService mrs = new MessageRestService();
						mrs.getValidation(USER_AGENT, cpf) ;
						getResult(cpf, email);
					}
					  
					  //System.out.println("analise:  " + analise);
					    
					    
				    	
				}
			};
			
			int delay = 10;
			scheduler.schedule(task, delay, TimeUnit.MINUTES);
			scheduler.shutdown();
	    
	  
			String date2 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
			System.out.println("Scheduling mailchimp update to " + cpf + " at " + date2 + " for " + delay + " minutes");
	    }
	}
	
	public void setCliente(String email, String nomeCompleto, String analise) throws Exception{

		System.out.println("MessageRestService2.setCliente()");
		
		HttpURLConnection conn = null;
	 
		  
		String modifyUrl = "http://www.nossocartorio.com.br/blog/inbound-api/v1/leads/modify/?key=e650011b8bcc937edbc73d37fee667c5&token=e14e3d974bad2da3945e3947873c22ac&results_per_page=-1&page=1&email=" + URLEncoder.encode(email, "UTF-8") ;
		modifyUrl += "&meta_data[wpleads_billing_last_name]=" + URLEncoder.encode(nomeCompleto, "UTF-8") +  "&meta_data[wpleads_website]=" + URLEncoder.encode(analise, "UTF-8");
		
		//System.out.println(modifyUrl);
		
		URL url = new URL(modifyUrl);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		System.out.println("email: " + email );
		System.out.println("nomeCompleto: " + nomeCompleto );
		System.out.println("analise: " + analise );
		/*BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		StringBuffer sb = new StringBuffer();
//		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
//			System.out.println(output);
			sb.append(output);
		}
		
		if(!sb.toString().equals("null")){
		

				ObjectMapper mapper = new ObjectMapper();
				DynaBean db = mapper.readValue(sb.toString(), DynaBean.class);
				
				for(Object obj : db.any().keySet()){
					Object object =((LinkedHashMap) db.any().get(obj)).get("meta_data");
					
					clienteResponse = mapper.convertValue(object, Cliente.class);
					
					//System.out.println(cliente);
				}
	
	    		
		}
		return clienteResponse;*/
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