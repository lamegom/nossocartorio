package com.mkyong.app;

import java.sql.Timestamp;

public class Consume extends Thread {
    private final Pancake cake;
    
    public Consume(String name, Pancake cake){
    	super(name);
    	this.cake = cake;
    }

    public Consume(Pancake cake){
        this.cake = cake;
    }

    @Override
    public void run() {
 
    	
    	String tabName = cake.cpf + cake.startTime;
    	
    	Thread.currentThread().setName(tabName);
    	
    	try {
			cake.run();
		} catch (Exception e) {
//			cake.result = e.getCause().getLocalizedMessage();
			cake.result = "Error...";
			cake.cooked  = true;
			cake.cooking = false;
			
			e.printStackTrace();
		}
    }
}