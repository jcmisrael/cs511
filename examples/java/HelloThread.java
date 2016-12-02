public class HelloThread extends Thread {
    String str;
    public HelloThread(String _str){
        str = _str;
    }

    public void run(){
        for(int i = 0; i < 25; ++i){
            System.out.println(str);
            /*try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                }*/
        }
    }

    public static void main(String args[]){
        new HelloThread("Hello").start();
        new HelloThread("Hello2").start();
    }
}
