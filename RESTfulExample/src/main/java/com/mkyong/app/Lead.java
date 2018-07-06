package com.mkyong.app;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
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

public class Lead extends Thread{

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
	public boolean resultado = false;

	public Lead(String email,ServletContext context){
		this.email= email;
		this.context = context;
		

	}
	public void run(){
		Cliente cliente = null;
		HttpURLConnection conn = null;
	  try {
		  
		  System.out.println("email: " + email);

		URL url = new URL("http://www.nossocartorio.com.br/blog/inbound-api/v1/leads/?key=e650011b8bcc937edbc73d37fee667c5&token=e14e3d974bad2da3945e3947873c22ac&results_per_page=-1&page=1&include_lists[]=26&email="+email);
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
		
//			JSONObject item = new JSONObject(sb.toString());
//			cliente = createCliente(item);

				ObjectMapper mapper = new ObjectMapper();
				DynaBean db = mapper.readValue(sb.toString(), DynaBean.class);
				
				for(Object obj : db.any().keySet()){
					Object object =((LinkedHashMap) db.any().get(obj)).get("meta_data");
					
					cliente = mapper.convertValue(object, Cliente.class);
					
					//System.out.println(cliente);
				}
				
				//System.exit(0);
				String imovelCliente = cliente.getValorImovel().get(0);
				double imovel = getValor(100000.0D, imovelCliente);
				
				System.out.println("Valor Imovel Cliente: " + imovelCliente);
				System.out.println("Valor Imovel: " + imovel);
				
				if(imovel > 150000.0D){

					cliente.setProdutoBB("SFH");
				
				}else{	

					cliente.setProdutoBB("PMCMV");
				
				}
		
		
		List<String> lstEmail = new ArrayList<String>();
		lstEmail.add(email);
		cliente.setEmail(lstEmail);
		
		Proposta example = new Proposta(cliente, context);
		if(resultado ){
			example.resultadoProposta();
	}else{
		
		example.loadLoginPage();
		example.loginAs(USERNAME, PASSWORD);
		
		try{
			example.accessCliente();
		}catch(Exception e){
			salvarProposta(cliente, example);
		}
		
//		
		salvarSAC( cliente,  example);

		
	}
			
		
}else{
	System.out.println("Cliente nao preencheu 2o formulario");
	
	ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
	mailchimp.setEmail(email);
	mailchimp.setAnalise("Aprovado");
	mailchimp.setResultado("Cliente nao preencheu 2o formulario");
	
	mailchimp.start();
}
		
		

		

	  } catch (Exception e) {
		  e.printStackTrace();
		  
		  
		  ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
			mailchimp.setEmail(email);
			try{
				if(cliente.getResultado().equals("Nenhum item encontrado para o proponente, favor verifique o cadastro do proponente.")){
					mailchimp.setAnalise("Cadastrais");
				}else{
					
					mailchimp.setAnalise("Aprovado");
				}
			}catch(NullPointerException n){
				cliente.setResultado(e.getCause().getLocalizedMessage());
				mailchimp.setAnalise("Aprovado");
			}
			mailchimp.setResultado("Houston..");
			mailchimp.setMotivo(cliente.getResultado());
			
			mailchimp.start();

		


	  }finally{
		  conn.disconnect();
	  }
	  
	  

	}
	
	public void salvarProposta(Cliente cliente, Proposta example) throws Exception{
		insertProposta(cliente, example);

		Thread.sleep(1000);
		
			if(getValor(100000.0D, cliente.getValorImovel().get(0)) > 150000.0D){
				example.salvarPropostaSFH();

			
			}else{	
			
				example.salvarPropostaPMCMV();

			
			}
	}
	
	public void salvarSAC(Cliente cliente, Proposta example) throws Exception{

		
		Thread.sleep(1000);
		
			if(NumberFormat.getInstance(new Locale("pt", "BR" )).parse(cliente.getValorImovel().get(0)).doubleValue() > 150000.0D){

				example.makeSACSFH();
			
			}else{	

				example.makeSACPMCMV();
			
			}
	}
	
	public void insertProposta(Cliente cliente, Proposta example) throws Exception{
		String res = example.testHtmlUnitDriver();
		
		System.out.println(res);
		
		result1 = res;
		
		cliente.setResultado(res);
		
		ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
		mailchimp.setEmail(cliente.getEmail().get(0));
		mailchimp.setAnalise("OK");
		
		mailchimp.setResultado(cliente.getResultado());
		mailchimp.start();
		
		
		
	}

	
	public Cliente createCliente(JSONObject item) throws NullPointerException, JSONException, ParseException{
printJsonObject(item);
		
//		System.out.println("Lead.main() " + Double.valueOf(((JSONArray) result).getDouble(0)));
//		System.out.println(((JSONArray) result2).get(0));
//		System.out.println(((JSONArray) result3).get(0));
//		
//		System.out.println(((JSONArray) result4).get(0));
		
		Cliente cliente = new Cliente();
		
		
			


		String valorImovel = result != null?(String) ((JSONArray) result).get(0):"100000.0";
		
		System.out.println("Lead.run() " + valorImovel);
	
		
		double imovel = getValor(100000.0D, result);
		
		cliente.setValorImovel((List<String>) result);
		
		System.out.println("valor imovel: " + imovel);
		
		

			
			
			if(Double.valueOf(cliente.getValorImovel().get(0)) > 150000.0D){
				cliente.setProdutoBB("SFH");
			
			}else{	
			
				cliente.setProdutoBB("PMCMV");
			
			}
			
//			if(result11 != null) cliente.setCommentStatus((String) ((JSONArray) result11).get(0));
			try{
			cliente.setCpf((List<String>) result4);
		}catch(NullPointerException n){
			System.out.println("Lead.run() : CPF em branco.");

		}
			try{
				cliente.setEstadoImovel((List<String>) result3);
		}catch(NullPointerException n){
			System.out.println("Lead.run() : Estado em branco.");

		}
			
			try{
			cliente.setCidade((List<String>) result2);
	}catch(NullPointerException n){
		System.out.println("Lead.run() : Cidade em branco.");

	}
			
			double renda = getValor(0.0D, result5);
			List<String> lstRendaBruta = new ArrayList<String>();
			lstRendaBruta.add(Double.valueOf(renda).toString());
			cliente.setRendaBruta(lstRendaBruta);
			
			
			double vlrfgts = getValor(0.0D, result6);
			List<String> lstFgts = new ArrayList<String>();
			lstFgts.add(Double.valueOf(vlrfgts).toString());
			cliente.setFgts(lstFgts);
			
			
			double recursos = getValor(0.0D, result7);
			List<String> lstRecursos = new ArrayList<String>();
			lstRecursos.add( Double.valueOf(recursos).toString());
			cliente.setRecursosProprios(lstRecursos);

			
			double despesas = getValor(500.0D, result8);
			
			if(despesas == 0.0) despesas = 500.0D;
			
//			System.out.println(despesas);
			List<String> lstDespesas = new ArrayList<String>();
			lstDespesas.add(Double.valueOf(despesas).toString());
			cliente.setDespesas(lstDespesas);
			
			
			String vlrPrazo = result9 != null?(String) ((JSONArray) result9).get(0):"360";
			
			int prazo = 360;
			try{
					prazo = Integer.valueOf(vlrPrazo);
		}catch(NumberFormatException e){
			
		}
			
			if(prazo<100){
				prazo = 120;
			}
			List<String> lstPrazo = new ArrayList<String>();
			lstPrazo.add(Double.valueOf(prazo).toString());
			cliente.setPrazo(lstPrazo);
			try{	
			cliente.setAmortizacao((List<String>) result10);
		}catch(NullPointerException n){
			System.out.println("Lead.run() : Amortizacao em branco.");

		}	
			try{	
				
			String phone = "55" + (String) ((JSONArray) result12).get(0);
			phone = phone.replaceAll("[^0-9]", "");
			//System.out.println("Lead.createCliente() " + phone);
			List<String> lstPhone = new ArrayList<String>();
			lstPhone.add(phone);
			cliente.setPhone(lstPhone);
//			cliente.setPhone("5511981084320");
			
		}catch(NullPointerException n){
			System.out.println("Lead.run() : Phone em branco.");

		}
			
			return cliente;
	}
	
	public double getValor(Double vlrDefault, Object vlr){
		String vlrDespesas = vlr != null?(String) vlr:vlrDefault.toString();
		
		double despesas = vlrDefault;
				try{
					 despesas = formatCurrency(vlrDespesas);
				}catch(ParseException e){
					
				}		
				
				return despesas;
	}
	
    final static String USERNAME = "JC049957";  
    final static String PASSWORD = "spb91744";
	
	public double formatCurrency(String vlr) throws ParseException{
		
		boolean decimals = false;

		
		if (vlr.endsWith(".00")) {
			decimals = true;
		}
//		if( vlr.endsWith(",00"))
//		System.out.println(vlr);
//		System.out.println(decimals);

			vlr = NumberFormat.getInstance(new Locale("pt", "BR" )).parse(vlr).toString();
		
//			System.out.println(vlr);
			
		
		double vlrImovel = Double.valueOf(vlr);

		if(decimals){
			vlrImovel = Double.valueOf(vlr)/100;
		}
		
		
		return new BigDecimal( vlrImovel ).setScale( 2 , RoundingMode.UP ).doubleValue();
	}
	
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
	        if(keyStr.equals("wpleads_shipping_zip")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result = keyvalue;
	        	
//	        	break;
	        }
	        
	        if(keyStr.equals("wpleads_shipping_city")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result2 = keyvalue;
	        	
//	        	break;
	        }
	        
	        if(keyStr.equals("wpleads_shipping_region_name")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result3 = keyvalue;
	        	
//	        	break;
	        }
	        
	        
	        if(keyStr.equals("wpleads_billing_first_name")){
        	
//        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
        	
        	result4 = keyvalue;
        	
//        	break;
        }
	        
	        if(keyStr.equals("wpleads_shipping_first_name")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result5 = keyvalue;
	        	
//	        	break;
	        }
	        
	        if(keyStr.equals("wpleads_shipping_last_name")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result6 = keyvalue;
	        	
//	        	break;
	        }
	        
	        if(keyStr.equals("wpleads_shipping_company_name")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result7 = keyvalue;
	        	
//	        	break;
	        }
	        
	        if(keyStr.equals("wpleads_shipping_country_code")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result8 = keyvalue;
	        	
//	        	break;
	        }
	        
	        
	        if(keyStr.equals("wpleads_shipping_address_line_1")){
        	
//        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
        	
        	result9 = keyvalue;
        	
//        	break;
        }
	        
	        if(keyStr.equals("wpleads_shipping_address_line_2")){
        	
//        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
        	
        	result10 = keyvalue;
        	
//        	break;
        }
	        
	        if(keyStr.equals("comment_status")){
	        	
//	        	System.out.println("key: "+ keyStr + " value: " + keyvalue);
	        	
	        	result11 = keyvalue;
	        	
//	        	break;
	        }
	        
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

//		String[] es = {"vpribeiro8@hotmail.com","efersonchicarelliruiz@gmail.com","jessicak350@gmail.com","sandradods@policiamilitar.sp.gov.br","orlando.ferreira@embrapa.br","sibebe@bol.com.br","ederalexandre180@gmail.com"};
//		String[] es = { "adimargarcia@gmail.com"};
		String[] es = { "wilsianecezario@gmail.com"};
		
		System.out.println(es.length);
		for (int i = 0; i < es.length; i++) {
			String string = es[i];
			limiter.limit();
			
			Lead lead = new Lead(string, null);
			lead.start();
			
		}
		
		
	}
}
