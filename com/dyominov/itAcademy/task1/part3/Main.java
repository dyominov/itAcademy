package task1.part3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Scanner in = new Scanner(System.in);
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        while (true) {
            System.out.println("Enter file name or EXIT");
            String fileName = in.next();
            if (fileName.equals("EXIT")) {
                break;
            }
            byte[] arr = readByteFromFile(new File(fileName));
            String s = convertToString(arr);
            List<Byte> list = getBytesFromArrayToList(arr);
            MyThread myThread = new MyThread(list);
            Future<List<Byte>> bytes = executorService.submit(myThread);
            while (!bytes.isDone()) {
                System.err.println(MyThread.length);
            }
            String res = convertToString(bytes);
            int first = s.indexOf(res);
            int second = s.indexOf(res, first + 1);
            System.out.println(bytes.get().size());
            System.out.println(first + " " + second);
        }
        executorService.shutdown();
    }

    private static byte[] readByteFromFile(File name) throws IOException {
        return Files.readAllBytes(name.toPath());
    }

    private static List<Byte> getBytesFromArrayToList(byte[] arr) {
        List<Byte> list = new ArrayList<>();
        for (byte b : arr) {
            list.add(b);
        }
        return list;
    }

    private static String convertToString(byte[] arr) {
        StringBuilder s = new StringBuilder();
        for (byte b : arr) {
            s.append((char) b);
        }
        return s.toString();
    }

    private static String convertToString(Future<List<Byte>> bytes) throws ExecutionException, InterruptedException {
        StringBuilder res = new StringBuilder();
        for (byte b : bytes.get()) {
            res.append((char) b);
        }
        return res.toString();
    }


}
