package com.jsonar.data_gen;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class HandlerToStream<T> implements Consumer<T> {

	final BlockingQueue<T> q = new ArrayBlockingQueue<T>(10);
	
	@Override
	public void accept(T i) {
		q.add(i);
	}
	
	public Stream<T> stream() {
		return StreamSupport.stream(
			Spliterators.spliteratorUnknownSize(
				new Iterator<T>() {

					@Override
				    public boolean hasNext() {
				        return true;
				    }
				
				    @Override
				    public T next() {
				        try {
							return q.take();
						} catch (InterruptedException e) {
							throw new NoSuchElementException();
						}
				    }
				}, Spliterator.IMMUTABLE), false);
	}
	
	
}