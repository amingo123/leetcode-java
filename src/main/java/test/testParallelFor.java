package test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.LongStream;

//一千万个数，如何高效求和。
public class testParallelFor {
    volatile static long sum = 0;
    final static int step = 10;
    final static int start = 1;
    final static int end = 10000000;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long a = 1;
        long b = 10000000;
        long c = LongStream.rangeClosed(a, b)  // Creates a stream going from 1 to 10000000
                .parallel()  // Parallelize this stream
                .sum();      // Sums every value of this stream
        //System.out.println(c);


        int num = Runtime.getRuntime().availableProcessors();
        ExecutorService exec = Executors.newFixedThreadPool(num);
        List<Future<Long>> futures = new ArrayList<>();
        try {
            for (int i = start; i < end;i = i * step) {
                int finalI = i;
//                exec.submit(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println("before"+sum);
//                        sum = sum+ Sum(finalI, finalI * step);
//                    }
//                });
                futures.add(exec.submit(() -> Sum(finalI, finalI * step)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
            exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            for (Future<Long> future : futures) {
                sum += future.get();
            }
            sum = sum + end;
            System.out.println("result="+sum);
        }
    }

    static long Sum(int i,int j)
    {
        System.out.println(Thread.currentThread().getName() + "Sum from "+ i + " to " + j + " start.");
        long temp =0;
        for (int k = i; k < j; k++) {
            temp = temp + k;
        }
        System.out.println(Thread.currentThread().getName() + "Sum from "+ i + " to " + j + " end. sum="+temp);
        return  temp;
    }
}
