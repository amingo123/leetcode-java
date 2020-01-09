package _1195FizzBuzzMultithreaded;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

public class _1195FizzBuzzMultithreaded {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        new Thread(()->
        {
            try {
                fizzBuzz.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"fizz").start();

        new Thread(()->
        {
            try {
                fizzBuzz.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"buzz").start();

        new Thread(()->
        {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"fizzbuzz").start();
        IntConsumer intConsumer = new IntConsumer();
        new Thread(()->
        {
            try {
                fizzBuzz.number(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"number").start();
    }

    static class IntConsumer
    {
        void accept(int x)
        {
            System.out.println(x);
        }
    }

    static class FizzBuzz {
        private int n;
        private Semaphore sem, sem3, sem5, sem15;

        public FizzBuzz(int n) {
            this.n = n;
            sem = new Semaphore(1);
            sem3 = new Semaphore(0);
            sem5 = new Semaphore(0);
            sem15 = new Semaphore(0);
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 3; i <= n; i += 3) {
                sem3.acquire();
                printFizz.run();
                if ((i + 3) % 5 == 0) // skip multiples of 15.
                    i += 3;
                sem.release();
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 5; i <= n; i += 5) {
                sem5.acquire();
                printBuzz.run();
                if ((i + 5) % 3 == 0) // skip multiples of 15.
                    i += 5;
                sem.release();
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 15; i <= n; i += 15) {
                sem15.acquire();
                printFizzBuzz.run();
                sem.release();
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; ++i) {
                sem.acquire();
                if (i % 15 == 0) {
                    sem15.release();
                }else if (i % 5 == 0) {
                    sem5.release();
                }else if (i % 3 == 0) {
                    sem3.release();
                }else {
                    printNumber.accept(i);
                    sem.release();
                }
            }
        }
    }
}
