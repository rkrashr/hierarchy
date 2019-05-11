package com.jsonar.data_gen;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Session implements Closeable {
	
	ScheduledExecutorService exec;
	List<Consumer<Integer>> messageHandlers;
	final AtomicInteger counter = new AtomicInteger(0);
	
	Runnable msgGen = ()->{
		messageHandlers.forEach(c -> c.accept(counter.incrementAndGet()));
	};
	
	ScheduledFuture<?> task;
	
	public Session(ScheduledExecutorService exec) {
		this.exec = exec;
		this.messageHandlers = new ArrayList<Consumer<Integer>>();
	}

	public void start() {
		task = exec.scheduleWithFixedDelay(msgGen, 0, 1, TimeUnit.MILLISECONDS);
	}
	
	public void stop() {
		task.cancel(true);
	}

	public void setHandler(Consumer<Integer> handler) {
		messageHandlers.add(handler);
	}

	@Override
	public void close() throws IOException {
		stop();
	}
	
}