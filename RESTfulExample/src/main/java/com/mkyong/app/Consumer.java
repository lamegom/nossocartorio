package com.mkyong.app;

import java.util.Queue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Consumer extends Thread {
	public Queue<Pancake> queue;
	private int queueSize;
	private String result;
	
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Consumer(Queue<Pancake> queueIn, int queueSizeIn, String ThreadName){
	    super (ThreadName);
	    this.queue = queueIn;
	    this.queueSize = queueSizeIn;
	}

	public void run() {
	    while(true){
	        synchronized (queue) {
	            while(queue.isEmpty()){
//	                System.out.println(Thread.currentThread().getName() + " Empty        : waiting...\n");
	                try {
	                    queue.wait();  //Important
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
//	                Main m = Main.getInstance();
//	                m.consumerThread.queue.add(m.producerThread.queue.remove());
	                
	            }
	            
	            
	            

		            //queue empty then consume one and notify
		            Pancake cake = queue.remove();
//		            System.out.println(Thread.currentThread().getName() + " consuming... : " + cake.cpf);
		            try {
		            	
		            	
						doPancake(cake);
	//	            	cake.eat();
	
	
					} catch (Exception e) {
						
						cake.result = e.getCause().getLocalizedMessage();
						cake.cooked  = true;
						cake.cooking = false;
						
						e.printStackTrace();
						
	
					}
		            queue.notifyAll();

	            
	        } //synchronized ends here : NOTE
	    }
	}
	
	public void doPancake(Pancake cake) throws Exception{
		Main m = Main.getInstance();
		
//        Runnable consume = new Consume( cake);
        m.threadPool.submit(cake);

//        
//        notifyAll();
        
       
        
//		cake.eat();
//		while(!cake.cooked){
//			Thread.sleep(1000);
//			
//		}
		


		
		
        
//        for (int second = 0;; second++) {
//            if (second >= 500){
////                System.out.println("timeout");
////                setResult("TIMEOUT!");
//                cake.result = "TIMEOUT!";
//                throw new Exception("TIMEOUT!");
//            }
//            try {
//                if (cake.cooked){
//                
//                    break;
//                }
//                }
//            catch (Exception e) {
//
//            }
//            try {
//
//    			
//            	Thread.currentThread().sleep(1000);
//	   		} catch (InterruptedException e) {
//	   			// TODO Auto-generated catch block
//	   			e.printStackTrace();
//	   		}
//        }
        
//        System.out.println(cake.result);
//        setResult(cake.result);
	}
	
	
	public Thread getThreadByName(String threadName) {
	    for (Thread t : Thread.getAllStackTraces().keySet()) {
	        if (t.getName().equals(threadName)) return t;
	    }
	    return null;
	}
	
	}
