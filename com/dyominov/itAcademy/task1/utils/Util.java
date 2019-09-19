package task1.utils;

import java.util.concurrent.ArrayBlockingQueue;

public class Util {

    public static void getPrimes(int start, int end, ArrayBlockingQueue<Integer> primeQueue) {
        for (int n = Math.max(2, start); n <= end; n++) {
            boolean prime = true;
            for (int j = 2; j <= n / 2; j++) {
                if (n % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                primeQueue.add(n);
            }
        }
    }
}
