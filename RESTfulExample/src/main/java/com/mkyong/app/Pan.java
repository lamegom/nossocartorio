package com.mkyong.app;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

public class Pan {

	public static Pancake cook(String cpf) {
		
		
		
		
		
		String date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
		System.out.println("");
		System.out.println("Pan.cook() " + cpf + " at " + date  );
		
		Pancake cake = new Pancake(cpf);
		
		return cake;
	}
	
	

}
