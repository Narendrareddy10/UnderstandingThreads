package printEvenOdd.sync_runnable;

public class EvenOdd {

    public static void main(String ...args) throws Exception {

        int maxNumber=100;
        EvenOddShared evenOddShared=new EvenOddShared();
       Thread evenThread=new Thread(new EvenThread(maxNumber,evenOddShared));
        Thread oddThread=new Thread(new OddThread(maxNumber,evenOddShared));
        evenThread.start();
        oddThread.start();
    }
}
