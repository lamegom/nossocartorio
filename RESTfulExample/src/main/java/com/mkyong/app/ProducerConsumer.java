package com.mkyong.app;

import java.util.*;

//@author : rootTraveller, June 2017

public class ProducerConsumer {
public static void main(String[] args) throws Exception {
    Queue<Pancake> queue = new LinkedList<>();
    Integer buffer = new Integer(10);  //Important buffer or queue size, change as per need.

    Producer producerThread = new Producer(queue, buffer, "PRODUCER");
    Consumer consumerThread = new Consumer(queue, buffer, "CONSUMER");

    producerThread.start();  
    consumerThread.start();
}   
}


