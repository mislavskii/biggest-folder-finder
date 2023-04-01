import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeMeter extends RecursiveTask<Long> {
    private File folder;

    public FolderSizeMeter(File folder) {
        this.folder = folder;
    }

    @Override
    protected Long compute() {
        if(folder.isFile()) {
            return folder.length();
        }
        long total = 0;
        List<FolderSizeMeter> subTasks = new LinkedList<>();
        File[] files = folder.listFiles();
        for (File file : files) {
            FolderSizeMeter task = new FolderSizeMeter(file);
            task.fork();  // run asynchronously
            subTasks.add(task);
        }
        for (FolderSizeMeter task : subTasks) {
            total += task.join();
        }
        return total;
    }
}
