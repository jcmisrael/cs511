public class HelloThreadRunnable implements Runnable{
    public void run(){
        System.out.println("Hello");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        new Thread(new HelloThreadRunnable()).start();
    }
}
