package com.griddatabase.model.cache;

import java.util.concurrent.*;

import org.apache.log4j.Logger;

public class RTLMPThreadPoolExecutor {

	private Logger logger = Logger.getLogger(RTLMPThreadPoolExecutor.class);
	
    private int poolSize = 2;
    
    private int maxPoolSize = 2;
 
    private long keepAliveTime = 15;
 
    private ThreadPoolExecutor threadPool = null;
 
    private final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1200);
 
    public RTLMPThreadPoolExecutor() {
        threadPool = new ThreadPoolExecutor(poolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, queue);
    }
 
    public void runTask(Runnable task) {
        threadPool.execute(task);
        logger.info("RTLMP Thread Pool task count.." + queue.size());
    }
    
    public void shutDown() {
        threadPool.shutdown();
    }
}
