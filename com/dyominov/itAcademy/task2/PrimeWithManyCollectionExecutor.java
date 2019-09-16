import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;

public class PrimeWithManyCollectionExecutor implements Callable<Queue<Integer>> {
    private final int start;
    private final int end;

    PrimeWithManyCollectionExecutor(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public Queue<Integer> call() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int n = Math.max(2, start); n <= end; n++) {
            boolean prime = true;
            for (int j = 2; j <= n / 2; j++) {
                if (n % j == 0) {
                    prime = false;
                    break;
                }
            }

            if (prime) {
                queue.add(n);
            }

        }
        return queue;
    }

}
