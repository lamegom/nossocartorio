package com.mkyong.app;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mkyong.bean.Cliente;
import com.mkyong.bean.DynaBean;

public class Lead2 extends Thread{

	private  Object result;
	private  Object result3;
	private  Object result2;
	private  Object result4;
	
	String email = null;
	ServletContext context = null;
	
	String result1 = null;
	private Object result5;
	private Object result6;
	private Object result7;
	private Object result8;
	private Object result9;
	private Object result10;
	private Object result11;
	private Object result12;

	public Lead2(String email,ServletContext context){
		this.email= email;
		this.context = context;
		

	}
	public void run(){
		Cliente cliente = null;
		HttpURLConnection conn = null;
	  try {
		  
		  System.out.println("email: " + email);

		URL url = new URL("http://www.nossocartorio.com.br/blog/inbound-api/v1/leads/?key=e650011b8bcc937edbc73d37fee667c5&token=e14e3d974bad2da3945e3947873c22ac&results_per_page=-1&page=1&include_lists[]=42&email="+email);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		StringBuffer sb = new StringBuffer();
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
//			System.out.println(output);
			sb.append(output);
		}
		
		if(!sb.toString().equals("null")){
		
//		JSONObject item = new JSONObject(sb.toString());
//		cliente = createCliente(item);

			ObjectMapper mapper = new ObjectMapper();
			DynaBean db = mapper.readValue(sb.toString(), DynaBean.class);
			
			for(Object obj : db.any().keySet()){
				Object object =((LinkedHashMap) db.any().get(obj)).get("meta_data");
				
				cliente = mapper.convertValue(object, Cliente.class);
				
				//System.out.println(cliente);
			}
			
			//System.exit(0);
		
		List<String> lstEmail = new ArrayList<String>();
		lstEmail.add(email);
		cliente.setEmail(lstEmail);
		//TODO: Cadastro
		Cadastro example = new Cadastro(cliente, context);
		
		example.loadLoginPage();
		example.loginAs(USERNAME, PASSWORD);
		example.testHtmlUnitDriver();

			
			
		
}else{
	System.out.println("Cliente nao preencheu formulario Cadastro");
	
	ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
	mailchimp.setEmail(email);
	mailchimp.setAnalise("Cadastrais");
	mailchimp.setResultado("Cliente nao preencheu formulario Cadastro");
	
	mailchimp.start();
}
		
		

		

	  } catch (Exception e) {
		  System.out.println(e.getCause().getLocalizedMessage());
		  
		  
		  ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
			mailchimp.setEmail(email);
				cliente.setResultado(e.getCause().getLocalizedMessage());
				mailchimp.setAnalise("Cadastrais");

			mailchimp.setResultado("Houston..");
			mailchimp.setMotivo(cliente.getResultado());
			
			mailchimp.start();

		


	  }finally{
		  conn.disconnect();
	  }
	  
	  

	}


	
	public Cliente createCliente(JSONObject item) throws NullPointerException, JSONException, ParseException{
printJsonObject(item);
		
//		System.out.println("Lead.main() " + Double.valueOf(((JSONArray) result).getDouble(0)));
//		System.out.println(((JSONArray) result2).get(0));
//		System.out.println(((JSONArray) result3).get(0));
//		
//		System.out.println(((JSONArray) result4).get(0));
		
		Cliente cliente = new Cliente();
		
		
			

try{

		
		cliente.setNomeCompleto( (List<String>) result);

	}catch(NullPointerException n){
		System.out.println("Lead2.run() : Nome Completo em branco.");

	}

			

			try{
			cliente.setCpf((List<String>) result4);
		}catch(NullPointerException n){
			System.out.println("Lead2.run() : CPF em branco.");

		}
			try{
			cliente.setEstado((List<String>) result3);
		}catch(NullPointerException n){
			System.out.println("Lead2.run() : Estado em branco.");

		}
			
			try{
			cliente.setMunicipio((List<String>) result2);
	}catch(NullPointerException n){
		System.out.println("Lead2.run() : Municipio em branco.");

	}
			
			try{
			cliente.setEndereco((List<String>) result5);
	}catch(NullPointerException n){
		System.out.println("Lead2.run() : Endereco em branco.");

	}
			
			
			try{
			cliente.setBairro((List<String>) result6);;
	}catch(NullPointerException n){
		System.out.println("Lead2.run() : Bairro em branco.");

	}	
			
			try{
			cliente.setTipoPropriedade((List<String>) result7);
	}catch(NullPointerException n){
		System.out.println("Lead2.run() : Tipo Propriedade em branco.");

	}
			
			try{
			cliente.setResideDesde((List<String>) result8);
	}catch(NullPointerException n){
		System.out.println("Lead2.run() : Reside desde em branco.");

	}
			
			
			try{
			cliente.setDataNascimento((List<String>) result10);
	}catch(NullPointerException n){
		System.out.println("Lead2.run() : Data Nascimento em branco.");

	}
			
			try{
			cliente.setCep((List<String>) result9);
	}catch(NullPointerException n){
		System.out.println("Lead2.run() : CEP em branco.");

	}
			
			try{	
				
				String phone = "55" + (String) ((JSONArray) result12).get(0);
				phone = phone.replaceAll("[^0-9]", "");
				//System.out.println("Lead.createCliente() " + phone);
				List<String> lstPhone = new ArrayList<String>();
				lstPhone.add(phone);
				cliente.setPhone(lstPhone);
//				cliente.setPhone("5511981084320");
				
			}catch(NullPointerException n){
				System.out.println("Lead.run() : Phone em branco.");

			}
			
			

			
			return cliente;
	}
	
	
	
    final static String USERNAME = "JC049957";  
    final static String PASSWORD = "spb91744";
	
	
	
	public static Object getJsonObject(JSONArray jArr) {
		    try {

		        for(int i = 0; i < jArr.length();i++) {
		        	
		        	System.out.println(jArr.get(i));
		        	
		            JSONObject innerObj = jArr.getJSONObject(i);
		            for(Iterator it = innerObj.keys(); it.hasNext(); ) {
		                String key = (String)it.next();
		                System.out.println(key + ":" + innerObj.get(key));
		            }
		        }
		    }
		    catch (JSONException e) {
		        e.printStackTrace();
		    }
			return null;

		}
	
	public  Object printJsonObject(JSONObject jsonObj) {
		
		Object keyvalue = null;
		
		
	    for (Object key : jsonObj.keySet()) {
	        //based on you key types
	        String keyStr = (String)key;
	        keyvalue = jsonObj.get(keyStr);
	        
	        
	        

	        //Print key and value
	        if(keyStr.equals("wpleads_billing_last_name")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result = keyvalue;
	        	
//	        	break;
	        }
	        
	        if(keyStr.equals("wpleads_billing_city")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result2 = keyvalue;
	        	
//	        	break;
	        }
	        
	        if(keyStr.equals("wpleads_billing_region_name")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result3 = keyvalue;
	        	
//	        	break;
	        }
	        
	        
	        if(keyStr.equals("wpleads_billing_first_name")){
        	
//        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
        	
        	result4 = keyvalue;
        	
//        	break;
        }
	        
	        if(keyStr.equals("wpleads_billing_address_line_1")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result5 = keyvalue;
	        	
//	        	break;
	        }
	        
	        if(keyStr.equals("wpleads_billing_address_line_2")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result6 = keyvalue;
	        	
//	        	break;
	        }
	        
	        if(keyStr.equals("wpleads_company_name")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result7 = keyvalue;
	        	
//	        	break;
	        }
	        
	        if(keyStr.equals("wpleads_billing_country_code")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result8 = keyvalue;
	        	
//	        	break;
	        }
	        
	        
	        if(keyStr.equals("wpleads_shipping_zip")){
        	
//        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
        	
        	result9 = keyvalue;
        	
//        	break;
        }
	        
	          if(keyStr.equals("wpleads_job_title")){
        	
//        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
        	
        	result10 = keyvalue;
        	
//        	break;
        }
	        
	          /*  if(keyStr.equals("comment_status")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result11 = keyvalue;
	        	
//	        	break;
	        }*/
	        
	        if(keyStr.equals("wpleads_work_phone")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result12 = keyvalue;
	        	
//	        	break;
	        }
        	
        	//for nested objects iteration if required
	        if (keyvalue instanceof JSONObject)
	            printJsonObject((JSONObject)keyvalue);
	        
	    }
		return keyvalue;
	}
	
	public  Object printJsonObject(JSONObject jsonObj, String keyForm) {
	    for (Object key : jsonObj.keySet()) {
	        //based on you key types
	        String keyStr = (String)key;
	        Object keyvalue = jsonObj.get(keyStr);

	        //Print key and value
	        if(keyStr.equals(keyForm)){
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	return keyvalue;
	        }
	        

	        //for nested objects iteration if required
	        if (keyvalue instanceof JSONObject)
	            printJsonObject((JSONObject)keyvalue);
	    }
		return null;
	}

	public static void get( JSONArray jArr) {
	    try {

	        for(int i = 0; i < jArr.length();i++) {
	            JSONObject innerObj = jArr.getJSONObject(i);
	            for(Iterator it = innerObj.keys(); it.hasNext(); ) {
	                String key = (String)it.next();
	                System.out.println(key + ":" + innerObj.get(key));
	            }
	        }
	    }
	    catch (JSONException e) {
	        e.printStackTrace();
	    }

	}
	
	public static void main(String[] args){


//		String email = "lisleye@hotmail.com";
//		Lead lead = new Lead(email);
//		lead.start();
		
		
		// capacity of 10 and a rate of 1/secon
		final RateLimiter limiter = new RateLimiter(1, 1);

		// schedule rate limiter ticks every 100 milliseconds
		ScheduledExecutorService scheduler = Executors
				.newSingleThreadScheduledExecutor();
		
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				limiter.tick();
			};
		}, 0, 5, TimeUnit.MINUTES);

		String[] es = {
				"edilainebretas@hotmail.com","onaldourze@hotmail.com","mdbconstrutora@hotmail.com","tacianacamposrm@gmail.com","suelenbrasileira347@gmail.com","antoniorogerio646@gmail.com","wvigano@hotmail.com","angela.larah@gmail.com","ELIANEOURIVES36@GMAIL.COM","motafelipe2@gmail.com"};
//		String[] es = { "lau.rodrigues45@gmail.com"};
		
		
		System.out.println(es.length);
		for (int i = 0; i < es.length; i++) {
			String string = es[i];
			limiter.limit();
			
			Lead2 lead = new Lead2(string, null);
			lead.start();
			
		}
		
		
	}
}
