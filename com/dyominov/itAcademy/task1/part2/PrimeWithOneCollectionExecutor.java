package task1.part2;


import task1.utils.Util;

import java.util.concurrent.ArrayBlockingQueue;

public class PrimeWithOneCollectionExecutor implements Runnable {
    static ArrayBlockingQueue<Integer> primeQueue;
    private final int start;
    private final int end;

    PrimeWithOneCollectionExecutor(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        Util.getPrimes(start, end, primeQueue);
    }
}
