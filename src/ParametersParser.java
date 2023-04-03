import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ParametersParser {
    private static final String[] REQUIRED_PARAMETERS = {"-d", "-l"};
    private final Map<String, String> parameters;

    public ParametersParser(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    String.format("Incorrect parameters count. Expected 4 but found %d instead.", args.length));
        }
        parameters = new HashMap<>();
        for (int i = 0; i < args.length - 1; i += 2) {
            parameters.put(args[i], args[i+1]);
        }
        for (String name : REQUIRED_PARAMETERS) {
            if (!parameters.containsKey(name)) {
                throw new IllegalArgumentException(String.format("Missing required parameter %s.", name));
            }
        }
    }

    public String getPath() {
        String path = parameters.get("-d");
        File dir = new File(path);
        if (!dir.exists()) {
            throw new IllegalArgumentException("Folder not found or invalid path.");
        }
    return path;
    }

    public long getLimit() {
        String limit = parameters.get("-l");
        if (!limit.matches("\\d+(\\.\\d+)?[KMGT]?b")) {
            throw new IllegalArgumentException("Invalid size threshold definition.");
        }
        return SizeConverter.getSizeFromHumanReadable(limit);
    }
}
