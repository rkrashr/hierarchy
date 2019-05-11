package com.jsonar.data_gen;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.joda.time.DateTime;



public class App 
{
	static final BlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(10);
	static final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(2);

	static final HandlerToStream<Integer> h2s = new HandlerToStream<Integer>();
	
	static void proc() {
		h2s.stream().forEach(System.out::println);
	}
	
	public static void main( String[] args ) throws InterruptedException, IOException
    {

    	try (Session session = new Session(exec)) {
    		
	    	session.setHandler(h2s);
	    	session.start();
	    	Future<?> task1 = exec.submit(App::proc);
	    	
	    	DateTime end = DateTime.now().plusSeconds(1);
	    	
	    	while (DateTime.now().isBefore(end)) {
	    		System.out.println(task1.isCancelled());
	    		Thread.sleep(1000);
	    	}

	    	task1.cancel(true);
	    	exec.shutdown();
    	}
    }
}
