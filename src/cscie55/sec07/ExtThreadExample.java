package cscie55.sec07;

class ExampleThread extends Thread{
    public ExampleThread(String name) {
        super(name);
    }
    public void run() {
        System.out.println("Executing thread "+Thread.currentThread().getName());
    }
}

class MyApp2 {
    public static void main(String[] args){
        ExampleThread t = new ExampleThread("myThread");
        t.start();
    }
}

