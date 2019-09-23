package task1.part1;


import task1.utils.Util;

import java.util.concurrent.ArrayBlockingQueue;

public class PrimeWithManyCollections extends Thread {
    final ArrayBlockingQueue<Integer> primeQueue;
    private final int start;
    private final int end;

    PrimeWithManyCollections(int start, int end, ArrayBlockingQueue<Integer> primeQueue) {
        this.start = start;
        this.end = end;
        this.primeQueue = primeQueue;
    }

    @Override
    public void run() {
        Util.getPrimes(start, end, primeQueue);
    }
}
