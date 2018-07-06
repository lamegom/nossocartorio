package com.mkyong.csv;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mkyong.app.RateLimiter;

import sun.net.www.protocol.http.HttpURLConnection;



public class RequestBinTutorial extends Thread{
	private final static String USER_AGENT = "Mozilla/5.0";
	private String urlParameters;
	public RequestBinTutorial(String string) {

			this.urlParameters= string;

	}

	public static void main(String[] args){

		
		
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
		}, 0, 2, TimeUnit.MINUTES);

		String[] es = {
				"data[email]=carlosaclaudino@gmail.com&data[merges][MMERGE6]=44.856.748-27",//404
				"data[email]=thaismelo1000@yahoo.com.br&data[merges][MMERGE6]=132.172.497-71",//404
				"data[email]=eduardofrancateixeira@hotmail.com&data[merges][MMERGE6]=078.361.667-83",//404
				"data[email]=JoelmaRafael12@gmail.com&data[merges][MMERGE6]=287.829.818-76",//404
				"data[email]=alvaneteteixeira@gmail.com&data[merges][MMERGE6]=276.425.688-43",//404
				"data[email]=cristina_kristal.2012@hotmail.com&data[merges][MMERGE6]=181.418.288-86",//404
				"data[email]=gisellecarvalhodesouza@yahoo.com.br&data[merges][MMERGE6]=473.263.598-90",//404
				"data[email]=santossineide2018@gmail.com&data[merges][MMERGE6]=390.802.488-94",//404
				"data[email]=sa.barradas@uol.com.br&data[merges][MMERGE6]=390.583.361-15",//404
				"data[email]=mariosantostadeu@hotmail.com&data[merges][MMERGE6]=026.836.026-00",//404
				"data[email]=joceliamj-18@hotmail.com&data[merges][MMERGE6]=091.774.226-51",//404
				"data[email]=gcipriano2802@gmail.com&data[merges][MMERGE6]=125.678.069-38",//404
				"data[email]=nathalia.franco21119@gmail.com&data[merges][MMERGE6]=085.257.039-23",//404
				"data[email]=silktec@uol.com.br&data[merges][MMERGE6]=192.628.718-56",//404
				"data[email]=claudinhamagalhaes2010@hotmail.com&data[merges][MMERGE6]=455.916.283-20"};
		
		System.out.println(es.length);
		for (int i = 0; i < es.length; i++) {
			String string = es[i];
			limiter.limit();
			
			RequestBinTutorial request = new RequestBinTutorial(string);
			request.start();
			
		}
		
		
	}
	
	public void run(){
	  String url = "http://www.nossocartorio.com.br/blog/credito/rest/hook/create/";
//		String url = "http://localhost:8080/rest/hook/create";
		URL obj = null;
		try {
			obj = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) obj.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//add reuqest header
		try {
			con.setRequestMethod("POST");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

//		String urlParameters = "data[email]=mateus.pp@hotmail.com&data[merges][MMERGE6]=184.686.898-07";
//		String urlParameters = "data[email]=edsonmarcospds@gmail.com&data[merges][MMERGE6]=913.046.528-15";
//		String urlParameters = "data[email]=maarcelo84@hotmail.com&data[merges][MMERGE6]=893.768.035-15";
//		String urlParameters = "data[email]=marinaluiz50@gmail.com&data[merges][MMERGE6]=123.681.696-01";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr;
		StringBuffer response = new StringBuffer();
		
		
		try {
			wr = new DataOutputStream(con.getOutputStream());
			
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			
			
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		
		//print result
		System.out.println(response.toString());
  }
}