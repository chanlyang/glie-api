package com.gaililie.glieapi.utils;

import com.google.common.util.concurrent.*;

import javax.validation.constraints.NotNull;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 使用guava ListeningExecutorService 代替JDK老的线程池，
 */
public class ThreadUtils {

    private static ListeningExecutorService defaultThreadPool = MoreExecutors.listeningDecorator(
            new ThreadPoolExecutor(
                    Runtime.getRuntime().availableProcessors() + 1,
                    Runtime.getRuntime().availableProcessors() * 8,
                    5,
                    TimeUnit.MINUTES,
                    new ArrayBlockingQueue<>(1000),
                    new ThreadFactory() {
                        private final AtomicInteger index = new AtomicInteger();
                        @Override
                        public Thread newThread(@NotNull Runnable r) {
                            Thread thread = new Thread(r);
                            thread.setName("glie-api-manual-thread:" + index.getAndIncrement());
                            return thread;
                        }
                    },
                    // 抛出异常，避免发现不了问题
                    new ThreadPoolExecutor.AbortPolicy()
            )
    );

    /**
     *
     * FIXME warning 异步任务一定要加callBack
     *
     * @param callable
     * @param callback
     * @param <T>
     */
    public static <T> void submit(Callable<T> callable, FutureCallback<? super T> callback) {
        ListenableFuture<T> listenableFuture =  defaultThreadPool.submit(callable);
        Futures.addCallback(listenableFuture, callback, defaultThreadPool);
    }

    public static void submit(Runnable runnable) {
        defaultThreadPool.submit(runnable);
    }

}
