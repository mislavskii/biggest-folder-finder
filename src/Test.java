import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        System.out.println(getLimitLetsValidThrough());

    }
    static boolean getLimitLetsValidThrough() {
        Map<String, Boolean> results = new HashMap<>();
        String[] validLimits = {"0.11", "1.11", "12.22", "155.0", "155.10", "213.07", "0", "7", "07", "77", "123"};
        for (String value : validLimits) {
            for (String unit : SizeConverter.UNITS) {
                String testInput = value + unit;
                String[] testArgs = {"-d", "c:/", "-l", testInput};
                ParametersParser parser = new ParametersParser(testArgs);
                try {
                    parser.getLimit();
                    results.put(testInput, true);
                } catch (IllegalArgumentException e) {
                    results.put(testInput, false);
                }
            }
        }
        return !results.containsValue(false);
    }
}
