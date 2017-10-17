package cscie55.sec07;

class MyRunnable implements Runnable {
    public MyRunnable(){

    }
    public void run() {
        System.out.println("Executing thread "+Thread.currentThread().getName());
    }
}

class MyApp1 {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new MyRunnable(), "myRunnable");
        myThread.start();
    }
}
