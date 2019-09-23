package task1.part2;


import task1.utils.Util;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

public class PrimeWithManyCollectionExecutor implements Callable<Queue<Integer>> {
    private ArrayBlockingQueue<Integer> primeQueue;
    private final int start;
    private final int end;

    PrimeWithManyCollectionExecutor(int start, int end, ArrayBlockingQueue<Integer> primeQueue) {
        this.start = start;
        this.end = end;
        this.primeQueue = primeQueue;
    }


    @Override
    public Queue<Integer> call() {
        Util.getPrimes(start, end, primeQueue);
        return primeQueue;
    }

}
