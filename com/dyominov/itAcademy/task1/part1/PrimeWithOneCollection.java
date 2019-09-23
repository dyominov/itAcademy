package task1.part1;


import task1.utils.Util;

import java.util.concurrent.ArrayBlockingQueue;

public class PrimeWithOneCollection extends Thread {
    static ArrayBlockingQueue<Integer> primeQueue;
    private final int start;
    private final int end;

    PrimeWithOneCollection(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        Util.getPrimes(start, end, primeQueue);
    }
}
