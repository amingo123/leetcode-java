package _1114PrintinOrder;

import javax.tools.ForwardingFileObject;
import java.net.SocketOptions;
import java.util.concurrent.CountDownLatch;

class _1114PrintinOrder {
    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        foo.first(() -> System.out.println("first"));
        foo.second(() -> System.out.println("second"));
        foo.third(() -> System.out.println("third"));
    }
}

class Foo {

    public Foo() {

    }
    private CountDownLatch stage1 = new CountDownLatch(1);
    private CountDownLatch stage2 = new CountDownLatch(1);

    void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        stage1.countDown();
    }

    void second(Runnable printSecond) throws InterruptedException {
        stage1.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        stage2.countDown();
    }

    void third(Runnable printThird) throws InterruptedException {
        stage2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}