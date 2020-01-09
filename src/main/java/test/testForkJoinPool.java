package test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class testForkJoinPool {
    private class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 20;

        private long arr[];
        private int start;
        private int end;

        public SumTask(long[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        /**
         * 小计
         */
        private long subtotal() {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }

            return sum;
        }

        @Override
        protected Long compute() {
            if ((end - start) <= THRESHOLD) {
                return subtotal();
            }else {
                int middle = (start + end) / 2;
                SumTask left = new SumTask(arr, start, middle);
                SumTask right = new SumTask(arr, middle, end);
                left.fork();
                right.fork();

                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();    //获取开始时间
        long[] arr = new long[10000000];
        for (int i =0 ; i < 10000000; i++) {
            arr[i] =i +1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> result = pool.submit(new testForkJoinPool().new SumTask(arr, 0, arr.length));
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("ForkJoin程序运行时间：" + (endTime - startTime) + "ms " + result.get());    //输出程序运行时间
        pool.shutdown();
    }
}
