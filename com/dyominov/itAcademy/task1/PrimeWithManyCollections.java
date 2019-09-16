package task1;

import java.util.concurrent.ArrayBlockingQueue;

public class PrimeWithManyCollections extends Thread {
    final ArrayBlockingQueue<Integer> primeList;
    private final int start;
    private final int end;

    PrimeWithManyCollections(int start, int end, ArrayBlockingQueue<Integer> primeList) {
        this.start = start;
        this.end = end;
        this.primeList = primeList;
    }

    @Override
    public void run() {
        for (int n = Math.max(2, start); n <= end; n++) {
            boolean prime = true;
            for (int j = 2; j <= n / 2; j++) {
                if (n % j == 0) {
                    prime = false;
                    break;
                }
            }

            if (prime) {
                primeList.add(n);

            }

        }
    }
}
