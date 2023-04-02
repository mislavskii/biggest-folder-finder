import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeMeter extends RecursiveTask<Long> {
    private final Node node;

    public FolderSizeMeter(Node node) {
        this.node = node;
    }

    @Override
    protected Long compute() {
        File folder = node.getFolder();
        if(folder.isFile()) {
            long size = folder.length();
            node.setSize(size);
            return size;
        }
        long total = 0;
        List<FolderSizeMeter> subTasks = new LinkedList<>();
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                Node subnode = new Node(file, node.getSizeThreshold());
                FolderSizeMeter task = new FolderSizeMeter(subnode);
                task.fork();  // run asynchronously
                subTasks.add(task);
                node.addSubnode(subnode);
            }
        }
        for (FolderSizeMeter task : subTasks) {
            total += task.join();
        }
        node.setSize(total);
        return total;
    }
}
