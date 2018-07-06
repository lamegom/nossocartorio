package com.mkyong.app;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Populate extends Thread{

	public Populate(String cpf2) {
		this.cpf = cpf2;
	}

	// http://localhost:8080/RESTfulExample/json/product/get
	public static void main(String[] args) {
		
//		com.mkyong.app.CSVReader csv = new com.mkyong.app.CSVReader();
//		List<String> cpfs = csv.run();
		int i = 0;
		
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
				}, 0, 30000, TimeUnit.MILLISECONDS);
				
				List<String> cpfs = new ArrayList<String>();
				
				cpfs.add("86071954509");
				cpfs.add("011.564.762-75");
				cpfs.add("86071954509");
				cpfs.add("406.330.178-84");
				cpfs.add("084.757.737-61");
				cpfs.add("806.263.432-91");
				cpfs.add("378.412.168-31");
				cpfs.add("766.541.504-06");
				cpfs.add("108.637.530-00");
				cpfs.add("005.013.521-04");
				cpfs.add("025.830.914-84");
				cpfs.add("409.496.208-58");
				cpfs.add("409.496.208-58");
				cpfs.add("105.795.308-33");
				cpfs.add("126.289.087-00");
				cpfs.add("644.286.213-53");
				cpfs.add("034.164.712-88");
				cpfs.add("248.044.708-16");
				cpfs.add("358.412.368-22");
				cpfs.add("008.242.051-38");
				cpfs.add("784.108.782-91");
				cpfs.add("088.676.727-03");
				cpfs.add("025.649.973-09");
				cpfs.add("845.714.118-04");
				cpfs.add("026.096.609-60");
				cpfs.add("050.187.704-57");
				cpfs.add("076.916.287-81");
				cpfs.add("370.293.888-52");
				cpfs.add("298.174.078-45");
				cpfs.add("261.113.478-29");
				cpfs.add("648.059.991-87");
				cpfs.add("392.539.758-22");
				cpfs.add("599.597.387-87");
				cpfs.add("018.521.961-65");
				cpfs.add("444.932.820-53");
				cpfs.add("281.450.998-54");
				cpfs.add("690.627.815-00");
				cpfs.add("105.147.354-30");
				cpfs.add("270.516.439-16");
				cpfs.add("339.597.288-75");
				cpfs.add("290.582.298-82");
				cpfs.add("014.647.059-17");
				cpfs.add("059.178.185-92");
				cpfs.add("007.865.449-10");
				cpfs.add("915.494.341-87");

		
    	for(String cpf:cpfs){
    		limiter.limit();
    		Populate p = new Populate(cpf);
    		p.start();
    		
//    		if(i++>2) break;
		
    	}
		
		
	}
	
	public String cpf;
	
	public void run(){
//		int i = 1;
    	System.out.println("Getting " + cpf);
		insert(cpf);
//    	System.out.println(i++);
	}
	
	public void insert(String cpf){

		try {

			URL url = new URL(
//					"http://www.nossocartorio.com.br/blog/credito/rest/message/" + cpf);//
					"http://localhost:8080/rest/message/" + cpf);//
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//				System.out.println("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			StringBuffer sb =new StringBuffer();
			//System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
				sb.append(output);
			}
			
			System.out.println("Finished : " + cpf);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	
}
