package SemaphoreThread;

/**
 * @author tjshan
 * @date 2020/1/10 15:26
 */
public class Main {

    public static void main(String[] args) {
        BoundedResource resource = new BoundedResource(3);
        for (int i=0;i<10;i++){
            new UserThread(resource).start();
        }
    }
}
