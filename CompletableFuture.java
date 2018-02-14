package com;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.IOUtils;

public class CompletableFuture {

	public static void main(String[] args) throws InterruptedException {
		
		
		submit();
		// TODO Auto-generated method stub

	}

	
	
	public static void submit() throws InterruptedException {
		
		
		
		
		/**
		 * This is where ExecutorCompletionService steps in. It is a thin wrapper around ExecutorService that "remembers" 
		 * all submitted tasks and allows you to wait for the first completed, as opposed to first submitted task. 
		 * In a way ExecutorCompletionService keeps a handle to all intermediate Future objects and once any of them finishes, 
		 * it's returned. Crucial API method is CompletionService.take() that blocks and waits for any underlying Future to complete. 
		 */
		
		
		final List<String> topSites = Arrays.asList(
		        "www.google.com", "www.youtube.com", "www.yahoo.com", "www.msn.com",
		        "www.wikipedia.org", "www.baidu.com", "www.microsoft.com", "www.qq1212abcd1.com"

		);
		
		final ExecutorService pool = Executors.newFixedThreadPool(8);
		final ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(pool);
		for (final String site : topSites) {
		    completionService.submit(new Callable<String>() {
		        @Override
		        public String call() throws Exception {
		        	
		        	
		        	
		        	LocalTime localTime = LocalTime.now();
					Thread.sleep(1000);
					System.out.println(localTime);
					String name = Thread.currentThread().getName();
					long id =Thread.currentThread().getId();
					System.out.println("The thread name is: "+ name+" and the id is: "+ id+ " and the time is: "+localTime);
					
					String siteName =IOUtils.toString(new URL("http://" + site), StandardCharsets.UTF_8);
					System.out.println(site);
		            return siteName;
		        }
		    });
		}
		
		
		/**
		 * You might be wondering why we need an extra counter? Unfortunately ExecutorCompletionService doesn't tell 
		 * you how many Future objects are still there waiting so you must remember how many times to call take().
		 */
		
		for(int i = 0; i < topSites.size(); ++i) {
		    final Future<String> future = completionService.take();
		    try {
		        final String content = future.get();
		  
		        System.out.println(content.length());
		        
		        
		    } catch (ExecutionException e) {
		        System.out.println("Error while downloading "+ e.getCause());
		    }
		}
		
		
		
		
	}
	
	
	
	
	
}
