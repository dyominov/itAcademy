package task1;

import java.util.concurrent.ArrayBlockingQueue;

public class PrimeWithOneCollection extends Thread {
    public static ArrayBlockingQueue<Integer> primeList;
    private final int start;
    private final int end;

    public PrimeWithOneCollection(int start, int end) {
        this.start = start;
        this.end = end;
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
