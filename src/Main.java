import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args){
        System.out.println("Hello world of sizes!\n");

        String folderPath = "D:/User/Explore";
        long sizeThreshold = 50 * 1024 * 1024;

        File file = new File(folderPath);
        Node root = new Node(file, sizeThreshold);

        long start = System.currentTimeMillis();
        System.out.println("Total size: " + getFolderSize(file));
        long duration = System.currentTimeMillis() - start;
        System.out.println("Elapsed: " + duration + " ms");
        System.out.println();

        FolderSizeMeter meter = new FolderSizeMeter(root, sizeThreshold);
        ForkJoinPool pool = new ForkJoinPool();
        start = System.currentTimeMillis();
        pool.invoke(meter);
        duration = System.currentTimeMillis() - start;
        long size = root.getSize();
        System.out.println("Total size: " + size);
        System.out.println("that is " + SizeConverter.getHumanReadableSize(size));
        System.out.println("Elapsed: " + duration + " ms");
        System.out.println("Reverse engineered size: "
                + SizeConverter.getSizeFromHumanReadable(SizeConverter.getHumanReadableSize(size)));
        System.out.println();
        System.out.println(root);
    }
    public static long getFolderSize(File folder) {
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