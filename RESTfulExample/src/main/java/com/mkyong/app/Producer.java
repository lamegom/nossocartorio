package com.mkyong.app;

import java.util.Queue;

import com.sun.swing.internal.plaf.synth.resources.synth;

public class Producer extends Thread {
	public Queue<Pancake> queue;
	private int queueSize ;
	private Pancake cake;



	public Pancake getCake() {
		return cake;
	}

	public void setCake(Pancake cake) {
		this.cake = cake;
	}

	public Producer (Queue<Pancake> queueIn, int queueSizeIn, String ThreadName){
	    super(ThreadName);
	    this.queue = queueIn;
	    this.queueSize = queueSizeIn;
	}

	public void run() {
	    while(true){
	        poll(cake);
	    }
	}

	public void poll(Pancake cake){
		synchronized (queue) {
	        while(queue.size() == queueSize){
//	            System.out.println(Thread.currentThread().getName() + " FULL         : waiting...\n");
	            try{
	                queue.wait();   //Important
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }

	        //queue empty then produce one, add and notify  
//	        int randomInt = new Random().nextInt(); 
	       if(cake!=null){
	        System.out.println(Thread.currentThread().getName() + " producing... : " + cake.cpf); 
	        queue.add(cake); 
	        setCake(null);
	        queue.notifyAll();  //Important
	       }
	    } //synchronized ends here : NOTE
	}
	
	
	public synchronized void addCake(Pancake cake){
		
		synchronized (queue) {
	        while(queue.size() == queueSize){
	            System.out.println(Thread.currentThread().getName() + " FULL         : waiting...\n");
	            try{
	                queue.wait();   //Important
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }

	        //queue empty then produce one, add and notify  
	        System.out.println(Thread.currentThread().getName() + " producing... : " + cake.cpf); 
	        queue.add(cake); 
	        queue.notifyAll();  //Important
	    } //synchronized ends here : NOTE
        
	}

	}


