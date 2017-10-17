package cscie55.sec07;

import java.util.Date;

class Message {
    /*** A Message class shared between Waiter and Notifier  */

//  volatile guarantees latest value to Waiter and Notifier.
    public Message(String textMessage) {

        this.textMessage = textMessage;
    }

    private volatile String textMessage;

    public String getTextMessage() {
        return  textMessage;
    }
    public void setTextMessage(String textMessage){
        this.textMessage = textMessage;
    }
}

 class Waiter implements Runnable {



    /*** A Waiter class reading text message from Message class */


    private Message message;

    public Waiter(Message message) {
        this.message = message;
    }

    public void run() {
        // Synchronized context is necessary for wait()
        // The below synchronized block will lock message object


        synchronized (message) {
            try {
                System.out.println("Waiter is waiting for the Notifier at "
                        + new Date());

                // Release lock on message for Notifier
                message.wait();
            }

            catch(InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        // Synchronized block over, now message object is no more locked
        // Notifier notifies using notify() on message


        System.out.println("Waiter is done waiting at " + new Date());

        // Reading shared message set by Notifier
        System.out.println("Waiter got the message: "
                + message.getTextMessage());
    }
}


class Notifier implements Runnable {




    /*** A Notifier class writing Message vslue and notifies to waiter */


    private Message message;

    public Notifier(Message message) {
        this.message = message;
    }
 public void run() {
        System.out.println("Notifier is sleeping for 5 seconds at "
                + new Date());

        // Sleeping current thread for 5 seconds.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        // Synchronized context is necessary for notify()
        // The below synchronized block will lock message object
        synchronized (message) {

            // Writing into shared message object
            message.setTextMessage("Notifier took 5 seconds");
            System.out.println("Notifier is notifying waiting thread to wake up at " + new Date());

            // Notify the Waiter thread which is waiting for message
            message.notify();
        }
        // Synchronized block over, now message object is no more locked
    }
}

 public class WaitAndSleep {
 /*
     * Main class performing sleep(), wait() and notify() over Message, Waiter and
     * Notifier classes
     */

    public static void main(String[] args) {

        // Simple Message with Hello World! as text message
        Message message = new Message("Hello World!");

        // Waiter thread waiting for message from Notifier
        Waiter waiter = new Waiter(message);
        Thread waiterThread = new Thread(waiter, "Waiter Thread");
        waiterThread.start();

        // Notifier thread notifying message to Waiter
        Notifier notifier = new Notifier(message);
        Thread notifierThread = new Thread(notifier, "Notifier Thread");
        notifierThread.start();

    }
}



