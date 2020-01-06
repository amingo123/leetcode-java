package _1115PrintFooBarAlternately;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class _1115PrintFooBarAlternately {
    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(2);

        new Thread(()->
        {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"foo").start();

        new Thread(()->
        {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"foo").start();
    }
}

class FooBar {
    private int n;
    Semaphore s = new Semaphore(0);
    Semaphore s2 = new Semaphore(1);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            s2.acquire();
            printFoo.run();
            s.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // printBar.run() outputs "bar". Do not change or remove this line.
            s.acquire();
            printBar.run();
            s2.release();
        }
    }
}
