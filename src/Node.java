import java.io.File;
import java.util.ArrayList;

public class Node {
    private final File folder;
    private final long sizeThreshold;
    private final ArrayList<Node> subnodes;
    private long size;
    private int level;

    public Node(File folder, long sizeThreshold) {
        this.folder = folder;
        this.sizeThreshold = sizeThreshold;
        subnodes = new ArrayList<>();
    }

    public void addSubnode(Node node) {
        node.setLevel(level + 1);
        subnodes.add(node);
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public File getFolder() {
        return folder;
    }

    public ArrayList<Node> getSubnodes() {
        return subnodes;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("  ".repeat(level));
        String size = SizeConverter.getHumanReadableSize(getSize());
        builder.append(String.format("%s - %s%n", folder.getName(), size));
        for (Node subnode : subnodes) {
            if (subnode.getSize() > sizeThreshold) {
                builder.append(subnode);
            }
        }
        return builder.toString();
    }
}
