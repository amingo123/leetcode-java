package _1117BuildingH2O;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class _1117BuildingH2O {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        H2O h2O = new H2O("HOHHOHOHHHHOOHHHOH");
        new Thread(()->
        {
            try {
                h2O.hydrogen(() -> System.out.print("H"));
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        },"hydrogen").start();

        new Thread(()->
        {
            try {
                h2O.oxygen(() -> System.out.print("O"));
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        },"oxygen").start();
    }

    static class H2O {
        String s;
        CyclicBarrier  cb = new CyclicBarrier(2);
        public H2O(String s) {
            this.s = s;
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException, BrokenBarrierException {
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            int length = s.length() / 3;
            for (int i = 0; i < length; i++) {
                cb.await();
                releaseHydrogen.run();
                releaseHydrogen.run();
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException, BrokenBarrierException {
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            int length = s.length()/3;
            for (int i = 0; i < length; i++) {
                cb.await();
                releaseOxygen.run();
            }
        }
    }

    class H2O_ {
        Semaphore h, o;
        public H2O_() {
            h = new Semaphore(2, true);
            o = new Semaphore(0, true);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            h.acquire();
            releaseHydrogen.run();
            o.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            o.acquire(2);
            releaseOxygen.run();
            h.release(2);
        }
    }
}
