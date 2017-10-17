package cscie55.sec07;

import java.util.Random;

public class JoinExample implements Runnable {
    private Random rand = new Random(System.currentTimeMillis());

    public void run() {
        //simulate some CPU expensive task
        for(int i=0; i<100000000; i++) {
            rand.nextInt();
            if(Thread.interrupted()){
                System.out.println("["+Thread.currentThread().getName()+"] interrupted!");
                break;
            }
        }
        System.out.println("["+Thread.currentThread().getName()+"] finished.");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(new JoinExample(), "joinThread-"+i);
            threads[i].start();
            if (i<4) threads[i].join(1000);
        }
        threads[4].sleep(1000);
        threads[4].interrupt();
        System.out.println("["+Thread.currentThread().getName()+"] All threads have finished.");

    }
}