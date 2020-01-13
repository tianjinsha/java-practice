package SemaphoreThread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author tjshan
 * @date 2020/1/10 15:26
 */
public class UserThread extends Thread{

    private final static Random random = new Random(26535);
    private final BoundedResource resource;

    UserThread(BoundedResource resource){
        this.resource=resource;
    }

    @Override
    public void run() {
        try {
            while (true){
                resource.use();
                TimeUnit.MILLISECONDS.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {

        }
    }
}
