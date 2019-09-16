import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThread implements Callable<List<Byte>> {

    private List<Byte> list;
    static AtomicInteger length;

    MyThread(List<Byte> list) {
        this.list = list;
        length = new AtomicInteger(0);
    }

    @Override
    public List<Byte> call() {
        int n = list.size();
        Set<List<Byte>> seen = new HashSet<>();
        List<Byte> max = Collections.emptyList();
        for (int i = 0; i < n; i++) {
            for (int j = i + max.size() + 1; j <= n && j <= i + n / 2; j++) {
                if (j == n || !list.get(j).equals(list.get(j - 1))) {
                    List<Byte> sub = list.subList(i, j);
                    if (seen.contains(sub)) {
                        if (sub.size() > max.size()) {
                            max = sub;
                        }
                        length = new AtomicInteger(sub.size());
                    } else {
                        seen.add(sub);
                    }
                }
            }
        }
        return max;
    }
}
