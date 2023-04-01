import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    static String[] UNITS = {"b", "Kb", "Mb", "Gb", "Tb"};

    public static void main(String[] args){

        System.out.println("Hello world of sizes!\n");

        String folderPath = "D:/User/Learn";
        File file = new File(folderPath);
        long start = System.currentTimeMillis();

//        System.out.println("Total size: " + getFolderSize(file) / Math.pow(1024, 2) + " Mb");
        FolderSizeMeter meter = new FolderSizeMeter(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(meter);
        System.out.println("Total size: " + size);
        System.out.println("that is " + getHumanReadableSize(size));
        long duration = System.currentTimeMillis() - start;
        System.out.println("Elapsed: " + duration + " ms");
        System.out.println(getSizeFromHumanReadable(getHumanReadableSize(size)));

        System.out.println(getSizeFromHumanReadable("235Kb"));

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

    public static String getHumanReadableSize(long size) {
        int power = (int) (Math.log(size) / Math.log(1024));
        long value = Math.round(size / Math.pow(1024, power));
        return value + UNITS[power];
    }

    public static long getSizeFromHumanReadable(String size) {
        int numericLength = 0;
        for (char c : size.toCharArray()) {
            if (Character.isDigit(c)) {
                numericLength += 1;
            }  else {
                break;
            }
        }
        String numericPart = size.substring(0, numericLength);
        String unit = size.substring(numericLength);
        int power = Arrays.asList(UNITS).indexOf(unit);
        return (long) (Integer.parseInt(numericPart) * Math.pow(1024, power));
    }

}

//        Set<Object> keys = System.getProperties().keySet();
//        for (Object key : keys) {
//            System.out.println(key + ": " + System.getProperty(key.toString()));
//        }