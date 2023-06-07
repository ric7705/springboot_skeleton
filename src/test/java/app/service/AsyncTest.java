package app.service;

import app.repository.UserDao;
import app.service.Impl.UserServiceImpl;
import app.vo.response.UserRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.security.auth.callback.Callback;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

@RunWith(MockitoJUnitRunner.class)
public class AsyncTest {

    /**
     * basic thread usage (extend)
     * */
    @Test
    public void v1() throws Exception {

        LongRunJob longRunJob = new LongRunJob();

        System.out.println("main start");

        longRunJob.start();
        longRunJob.join();
        System.out.println("main end");
    }

    /**
     * basic thread usage (implement)
     * */
    @Test
    public void v1_1() throws Exception {

        ILongRunJob iLongRunJob = new ILongRunJob();
        System.out.println("main start");
        iLongRunJob.run();
        System.out.println("main end");
    }


    /**
     * use thread pool
     * */
    @Test
    public void v2() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        System.out.println("main start");

        // 提交任務到線程池
        executorService.execute(new LongRunJob());
        executorService.submit(new LongRunJob());
        executorService.submit(new LongRunJob());
        executorService.submit(new LongRunJob());
        executorService.submit(new LongRunJob());

        // 關閉線程池
        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // 等待所有線程執行完畢
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads completed");

        System.out.println("main end");
    }

    /**
     * get result using Future
     * */
    @Test
    public void v3() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        System.out.println("main start");
        LongRunJobWithResult longRunJob = new LongRunJobWithResult();
        Future<String> future = executorService.submit(longRunJob);
        try {
            System.out.println("等待結果");
            String result = future.get();
            System.out.println("結果：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        System.out.println("main end");

    }

    /**
     * JDK1.8, Completable Future
     * */
    @Test
    public void v4() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        System.out.println("main start");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new LongRunJobWithResult2(), executorService);

//        String result = future.get();
//        System.out.println("thread return: " + result);

//        while (!completableFuture.isDone()) {
//            System.out.println("CompletableFuture is not finished yet...");
//        }
//        String result = completableFuture.get();
        executorService.shutdown();
        System.out.println("main end");
        ForkJoinPool s = new ForkJoinPool();
    }
}

class LongRunJob extends Thread{
    @Override
    public void run() {
        try {
            System.out.println("thread start");
            Thread.sleep(5000);
            System.out.println("thread end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


class ILongRunJob implements Runnable{
    @Override
    public void run() {
        try {
            System.out.println("thread start");
            Thread.sleep(5000);
            System.out.println("thread end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


class LongRunJobWithResult implements Callable<String> {

    @Override
    public String call() throws Exception {
        try {
            System.out.println("thread start");
            Thread.sleep(5000);
            System.out.println("thread end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OK";
    }
}


class LongRunJobWithResult2 implements Supplier<String> {
    @Override
    public String get() {
        try {
            System.out.println("thread start");
            Thread.sleep(5000);
            System.out.println("thread end");
            return "OK";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}

