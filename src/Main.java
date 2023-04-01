import java.io.File;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args){

        System.out.println("Hello world of sizes!\n");

//        MyThread thread = new MyThread(1);
//        MyThread thread2 = new MyThread(2);
//
//        thread.start();
//        thread2.start();


        String folderPath = "D:/User/Learn";
        File file = new File(folderPath);
        long start = System.currentTimeMillis();

//        System.out.println("Total size: " + getFolderSize(file) / Math.pow(1024, 2) + " Mb");
        FolderSizeMeter meter = new FolderSizeMeter(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(meter);
        System.out.println("Total size: " + size / Math.pow(1024, 2) + " Mb");
        long duration = System.currentTimeMillis() - start;
        System.out.println("Elapsed: " + duration + " ms");

    }
    public static long getFolderSize(File folder) {
//        String[] units = {"b", "Kb", "Mb", "Gb"};
        if (folder.isFile()) {
            return folder.length();
        }
        long total = 0;
        File[] files = folder.listFiles();
        if (files != null) {
            for(File file : files) {
                total += getFolderSize(file);
            }
        }
        return total;
    }
}

//        Set<Object> keys = System.getProperties().keySet();
//        for (Object key : keys) {
//            System.out.println(key + ": " + System.getProperty(key.toString()));
//        }