import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task3 {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        while (true) {
            System.out.println("Enter file name");
            Scanner in = new Scanner(System.in);
            String fileName = in.next();
            byte[] arr = readByteFromFile(new File(fileName));
            System.out.println(Arrays.toString(arr));
            List<Byte> list = new ArrayList<>();
            StringBuilder s = new StringBuilder();
            for (byte b : arr) {
                list.add(b);
                s.append((char) b);
            }
            MyThread myThread = new MyThread(list);
            ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            Future<List<Byte>> bytes = executorService.submit(myThread);
            StringBuilder res = new StringBuilder();
            for (byte b : bytes.get()) {
                res.append((char) b);
            }
            int first = s.indexOf(res.toString());
            int second = s.indexOf(res.toString(), first + 1);
            System.out.println(bytes.get().size());
            System.out.println(first + " " + second);
        }
    }

    private static byte[] readByteFromFile(File name) throws IOException {
        return Files.readAllBytes(name.toPath());
    }


}
