package test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class testForkJoinPool {
    private class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 5000;

        private int start;
        private int end;

        public SumTask(int start, int end) {

            this.start = start;
            this.end = end;
        }

        /**
         * 小计
         */
        private long subtotal() {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += i;
            }
            //System.out.println(Thread.currentThread().getName() + ": ∑(" + start + "~" + end + ")=" + sum);
            return sum;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            if ((end - start) <= THRESHOLD) {
                for (int i=start; i<=end; i++)
                    sum += i;
            }else {
                int mid = start + (end - start) / 2;
                SumTask left = new SumTask(start, mid);
                SumTask right = new SumTask( mid+1, end);
                left.fork();
                right.fork();
                sum = left.join()  + right.join();

            }
            return  sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();    //获取开始时间
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> result = pool.submit(new testForkJoinPool().new SumTask( 1, 10000000));
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("ForkJoin程序运行时间：" + (endTime - startTime) + "ms " + result.get());    //输出程序运行时间
        pool.shutdown();
    }
}
