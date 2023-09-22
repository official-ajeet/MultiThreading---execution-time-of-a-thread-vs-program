package MultiThreading;
import java.math.BigInteger;

public class CalculateFactorialWithTiming {
    public static void main(String[] args) throws InterruptedException {
        long programStartTime = System.currentTimeMillis(); // Record the start time of the program

        int[] numbers = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 35000};
        System.out.println("Number of processors: " + Runtime.getRuntime().availableProcessors());

        MyThread[] threads = new MyThread[numbers.length];
        long[] executionTimes = new long[numbers.length];
        long maxExecutionTime = 0;

        for (int i = 0; i < numbers.length; i++) {
            threads[i] = new MyThread(numbers[i]);
            threads[i].start();
        }

        for (int i = 0; i < numbers.length; i++) {
            threads[i].join(); // Wait for the thread to die
            long executionTime = threads[i].getExecutionTime();
            executionTimes[i] = executionTime;

//to print the result - factorial calculated for each elements
//            System.out.println(threads[i].result);

            System.out.println("Factorial of " + numbers[i] + " took " + executionTime + " milliseconds.");

            // Update the maximum execution time
            maxExecutionTime = Math.max(executionTime,maxExecutionTime);
        }

        long programEndTime = System.currentTimeMillis(); // Record the end time of the program
        long totalProgramExecutionTime = programEndTime - programStartTime;// Total execution time for program 

        System.out.println("Total program execution time: " + totalProgramExecutionTime + " milliseconds");
        System.out.println("Maximum execution time of thread : " + maxExecutionTime + " milliseconds");
    }

    private static class MyThread extends Thread {
        private int num;
        private BigInteger result;
        private long executionTime;

        MyThread(int num) {
            this.num = num;
            this.result = BigInteger.ONE;
        }

        public void run() {
            long startTime = System.currentTimeMillis();//Record the start time of a single thread
            calculate();
            long endTime = System.currentTimeMillis();//Record the end time of a single thread
            executionTime = endTime - startTime;//Total execution time of a single thread
        }

        public long getExecutionTime() {
            return executionTime;
        }

        public void calculate() {
            for (int i = 2; i <= num; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
        }
    }
}
