import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int start = in.nextInt();
        int end = in.nextInt();
        int n = end - Math.max(1, start) + 1;
        int nThreads = in.nextInt();
        long startTime = System.currentTimeMillis();
        final PrimeWithOneCollection[] pThreadsOne = new PrimeWithOneCollection[nThreads];
        PrimeWithOneCollection.primeList = new ArrayBlockingQueue<>(n);
        int step = n / nThreads + 1;
        for (int i = 0; i < nThreads; i++) {
            pThreadsOne[i] = new PrimeWithOneCollection(i * step, Math.min(n, (i + 1) * step - 1));
            pThreadsOne[i].start();
        }
        try {
            for (int i = 0; i < nThreads; i++)
                pThreadsOne[i].join();
        } catch (InterruptedException ignored) {
        }

        System.out.println("One Collection has " + PrimeWithOneCollection.primeList.size() + " elements");
        System.out.println("One Collection " + (System.currentTimeMillis() - startTime) + " ms");

        startTime = System.currentTimeMillis();
        final PrimeWithManyCollections[] pThreadsMany = new PrimeWithManyCollections[nThreads];
        step = n / nThreads + 1;
        for (int i = 0; i < nThreads; i++) {
            pThreadsMany[i] = new PrimeWithManyCollections(i * step, Math.min(n, (i + 1) * step - 1), new ArrayBlockingQueue<>(step));
            pThreadsMany[i].start();
        }
        try {
            for (int i = 0; i < nThreads; i++)
                pThreadsMany[i].join();
        } catch (InterruptedException ignored) {
        }

        ArrayBlockingQueue<Integer> primeUnionCollection = new ArrayBlockingQueue<>(n);
        for (PrimeWithManyCollections withManyCollections : pThreadsMany) {
            primeUnionCollection.addAll(withManyCollections.primeList);
        }
        System.out.println("Union Collection has " + primeUnionCollection.size() + " elements");
        System.out.println("Union Collection " + (System.currentTimeMillis() - startTime) + " ms");

    }

}
