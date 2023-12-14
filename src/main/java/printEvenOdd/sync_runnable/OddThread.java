package printEvenOdd.sync_runnable;

import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddThread implements Runnable {

    final int maxNumber;
    final EvenOddShared evenOddShared;
    public OddThread(int maxNumber, EvenOddShared evenOddShared) {
        this.maxNumber = maxNumber;
        this.evenOddShared = evenOddShared;
    }

    @Override
    public void run() {
        while (evenOddShared.getNumber()<this.maxNumber) {
            synchronized (evenOddShared) {
                if (evenOddShared.getNumber() % 2 == 1) {
                    print(evenOddShared.getNumber());
                    evenOddShared.notifyAll();
                    evenOddShared.setNumber(evenOddShared.getNumber()+1);
                } else {
                    try {
                        evenOddShared.wait();
                        System.out.printf("Time : %s Waiting from Thread : %s Number :: %d ...........> OddThread Class %n"
                                , Instant.now().toString(),Thread.currentThread().getName(),evenOddShared.getNumber());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private void print(int number){
        System.out.printf("Time : %s Printing from Thread : %s Number :: %d ...........> OddThread Class %n"
                , Instant.now().toString(),Thread.currentThread().getName(),number);
    }
}
