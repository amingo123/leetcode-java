package test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class testParallelFor {
    volatile static long sum = 0;
    final static int step = 100;
    final static int start = 1;
    final static int end = 10000000;
    public static void main(String[] args) throws InterruptedException {
        int num = Runtime.getRuntime().availableProcessors();
        ExecutorService exec = Executors.newFixedThreadPool(num);
        try {
            for (int i = start; i < end;i = i * step) {
                int finalI = i;
                exec.submit(new Runnable() {
                    @Override
                    public void run() {
                        sum = sum+ Sum(finalI, finalI * step);
                    }
                });
            }

        } finally {
            exec.shutdown();
            exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            sum = sum + end;
            System.out.println(sum);
        }
    }

    static long Sum(int i,int j)
    {
        System.out.println("Sum from "+ i + " to " + j + " start.");
        long temp =0;
        for (int k = i; k < j; k++) {
            temp = temp + k;
        }
        System.out.println("Sum from "+ i + " to " + j + " end. sum="+temp);
        return  temp;
    }
}
