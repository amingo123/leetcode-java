package test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.LongStream;

//一千万个数，如何高效求和。
//https://stackoverflow.com/questions/59660413/multithread-summation-with-java
public class testParallelFor {
    volatile static long sum = 0;
    final static int start = 1;
    final static int end = 10000000;
    final static int max = 4000;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long a = 0;
        long b = 3999;
        long c = LongStream.rangeClosed(a, b)  // Creates a stream going from 1 to 10000000
                .parallel()  // Parallelize this stream
                .sum();      // Sums every value of this stream
        System.out.println(c);

        int num = 2*Runtime.getRuntime().availableProcessors();
        long startTime = System.currentTimeMillis();    //获取开始时间
        ExecutorService exec = Executors.newFixedThreadPool(num);
        List<Future<Long>> futures = new ArrayList<>();
        try {
            int s = 1;
            int e = s + max;
            boolean run = true;
            while(run)
            {
                if(e> end)
                {
                    e = end+1;
                    run = false;
                }
                int finalE = e;
                int finalS = s;
                futures.add(exec.submit(() -> Sum(finalS, finalE)));
                s = finalE;
                e = finalE + max;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
            exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            for (Future<Long> future : futures) {
                sum += future.get();
            }
            long endTime = System.currentTimeMillis();    //获取结束时间
            System.out.println("ParallelFor程序运行时间：" + (endTime - startTime) + "ms result="+sum);    //输出程序运行时间
        }
    }

    static long Sum(int i,int j)
    {
        //System.out.println(Thread.currentThread().getName() + "Sum from "+ i + " to " + j + " start.");
        long temp =0;
        for (int k = i; k < j; k++) {
            temp = temp + k;
        }
        //System.out.println(Thread.currentThread().getName() + "Sum from "+ i + " to " + j + " end. sum="+temp);
        return  temp;
    }
}
