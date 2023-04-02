import java.io.File;

public class ParametersParser {
    private final String[] args;

    public ParametersParser(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    String.format("Incorrect parameters count. Expected 4 but found %d instead.", args.length));
        }
        this.args = args;
    }

    public String getPath() {
        String path = args[1];
        File dir = new File(path);
        if (!dir.exists()) {
            throw new IllegalArgumentException("Folder not found or invalid path.");
        }
    return path;
    }

    public long getLimit() {
        return SizeConverter.getSizeFromHumanReadable(args[3]);
    }
}
