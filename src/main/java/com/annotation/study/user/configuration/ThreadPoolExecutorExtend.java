package com.annotation.study.user.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadPoolExecutorExtend extends ThreadPoolExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolExecutorExtend.class);

    /**
     * 线程开始时间
     */
    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    /**
     * 任务数
     */
    private final AtomicLong numTasks = new AtomicLong();
    /**
     * 线程池完成所有任务的总时间
     */
    private final AtomicLong totalTime = new AtomicLong();
    /**
     * 线程池名称
     */
    private String poolName;

    public ThreadPoolExecutorExtend(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, String poolName) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        this.poolName = poolName;
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        long endTime = System.currentTimeMillis();
        long useTime = endTime - startTime.get();
        numTasks.incrementAndGet();
        totalTime.addAndGet(useTime);
        super.afterExecute(r, t);
        // 线城结束时，结束startTime的生命周期，不然会发生内存泄漏
        startTime.remove();
        LOGGER.info("{}-pool-monitor: " +
                        "Duration: {} ms, PoolSize: {}, CorePoolSize: {}, Active: {}, " +
                        "Completed: {}, Task: {}, Queue: {}, LargestPoolSize: {}, " +
                        "MaximumPoolSize: {},  KeepAliveTime: {}, isShutdown: {}, isTerminated: {}",
                this.poolName,
                useTime, this.getPoolSize(), this.getCorePoolSize(), this.getActiveCount(),
                this.getCompletedTaskCount(), this.getTaskCount(), this.getQueue().size(), this.getLargestPoolSize(),
                this.getMaximumPoolSize(), this.getKeepAliveTime(TimeUnit.MILLISECONDS), this.isShutdown(), this.isTerminated());
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        startTime.set(System.currentTimeMillis());
    }

    /**
     * 线程池关闭
     */
    @Override
    public void shutdown() {
        LOGGER.info("{} Going to shutdown. Executed tasks: {}, Running tasks: {}, Pending tasks: {}",
                this.poolName, this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size());
        super.shutdown();
    }

    /**
     * 线程池立即关闭线程池
     * @return
     */
    @Override
    public List<Runnable> shutdownNow() {
        LOGGER.info("{} Going to immediately shutdown. Executed tasks: {}, Running tasks: {}, Pending tasks: {}",
                this.poolName, this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size());
        return super.shutdownNow();
    }
}
