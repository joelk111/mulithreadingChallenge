package threading;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;


/**Deutsche Bank Thread Challenge
 * 
 * Create a multi-threaded application that takes in 3 strings: thread1, thread2, thread3 and returns
 * their results in parallel. Your code should use Java8 in order to accomplish the task and have a single method
 * that runs it called runThreads.
 * 
 * 
 * 
 * 
 * @author joel kass
 *
 */

public class ThreadChallenge {

	
	/**
	 * Atomic can use incremeent and Get as well as update the value itself using compare and swap.
	 */
	static AtomicInteger aInt = new AtomicInteger();
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

		
		ThreadChallenge threads = new ThreadChallenge();
		threads.runThreads();
		
	}
	
	/** runThreads
	 * 
	 * Application Logic
	 * 
	 * 1. creates separate callable tasks for task1, task2 and task3
	 * 2. add them to a list
	 * 3. instantiate executor services
	 * 4. create a list of Futures and set it equal to executors.invoke all-->(callable list)
	 * 5. call invokeAll
	 * 6. get the results
	 * 
	 */
	public void runThreads() {

				//1. create separate callables

				Callable<Integer> call1=()->{System.out.println("task1"); 
				LocalTime localTime = LocalTime.now();
				System.out.println(localTime);
				String name = Thread.currentThread().getName();
				long id =Thread.currentThread().getId();
				System.out.println("The thread name is: "+ name+" and the id is: "+ id+ " and the time is: "+localTime);
				return 1; };
				
				
				Callable<Integer> call2=()->{System.out.println("task2"); 
				LocalTime localTime = LocalTime.now();
				System.out.println(localTime);
				String name = Thread.currentThread().getName();
				long id =Thread.currentThread().getId();
				System.out.println("The thread name is: "+ name+" and the id is: "+ id+ " and the time is: "+localTime);
				return 2; };
				
				
				Callable<Integer> call3=()->{System.out.println("task3"); 
				LocalTime localTime = LocalTime.now();
				System.out.println(localTime);
				String name = Thread.currentThread().getName();
				long id =Thread.currentThread().getId();
				System.out.println("The thread name is: "+ name+" and the id is: "+ id+ " and the time is: "+localTime);
				return 3; };
				

				
				//2 add them to a list
				List<Callable<Integer>> callables = Arrays.asList(
						call1, call2, call3);

				/** Executes the given tasks, returning a list of Futures holding their status 
				 * and results when all complete. 
		    		Future.isDone() is true for each element of the returned list.

					
				 */
				

				
				//3 instantiate executor services
				ExecutorService executor = Executors.newFixedThreadPool(4);
				
				
				//4 create a list of Futures and set it equal to executors.invoke all-->(callable list)
				
				
				/**
				 * INVOKE ALL
				 * 
				 * Executes the given tasks, returning a list of Futures holding their status 
				 * and results when all complete.
				 * 
				 * invokeAll blocks until each task in the collection is complete
				 */
				List<Future<Integer>> future = null;
				try {
					future = executor.invokeAll(callables);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				executor.shutdown();
				

				

				//5 for each Future Integer, get the result by setting to future.get
				for(Future<Integer> fut:  future) {
					
				
					
					try {
						System.out.println(fut.get());
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
					
				}
		
		
		
	}

}
