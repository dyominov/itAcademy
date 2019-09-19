package task1.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner in = new Scanner(System.in);
        int start = in.nextInt();
        int end = in.nextInt();
        int n = end - Math.max(1, start) + 1;
        int nThreads = in.nextInt();
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        PrimeWithOneCollectionExecutor.primeList = new ArrayBlockingQueue<>(n);
        int step = n / nThreads + 1;
        for (int i = 0; i < nThreads; i++) {
            Runnable worker = new PrimeWithOneCollectionExecutor(i * step, Math.min(n, (i + 1) * step - 1));
            executor.execute(worker);
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("One Collection " + (System.currentTimeMillis() - startTime) + " ms");
        System.out.println("One Collection has " + PrimeWithOneCollectionExecutor.primeList.size() + " elements");
        startTime = System.currentTimeMillis();
        executor = Executors.newFixedThreadPool(nThreads);
        List<Callable<Queue<Integer>>> taskList = new ArrayList<>();
        for (int i = 0; i < nThreads; i++) {
            taskList.add(new PrimeWithManyCollectionExecutor(i * step, Math.min(n, (i + 1) * step - 1)));
        }
        List<Future<Queue<Integer>>> futures = executor.invokeAll(taskList, 1, TimeUnit.MINUTES);
        executor.shutdown();
        List<Integer> primes = new ArrayList<>();
        for (Future<Queue<Integer>> future : futures) {
            primes.addAll(future.get());
        }
        System.out.println("Union Collection " + (System.currentTimeMillis() - startTime) + " ms");
        System.out.println("Union Collection has " + primes.size() + " elements");
    }

}