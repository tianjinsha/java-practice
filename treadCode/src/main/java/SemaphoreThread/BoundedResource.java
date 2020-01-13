package SemaphoreThread;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2020/1/10 15:27
 */
public class BoundedResource {
    private final Semaphore semaphore;
    private final int permits;
    private final static Random random=new Random(314159);


    public BoundedResource(int permits) {
        this.semaphore = new Semaphore(3);
        this.permits = permits;
    }

    public void use() throws InterruptedException {
        semaphore.acquire();
        try {
            doUse();
        } finally {
            semaphore.release();
        }
    }

    private void doUse() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" Begin:used="+(permits-semaphore.availablePermits()));
        TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
        System.out.println(Thread.currentThread().getName()+" End:used="+(permits-semaphore.availablePermits()));

    }
}
