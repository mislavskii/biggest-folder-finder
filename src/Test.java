import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test {

    public static void main(String[] args) {

        System.out.println("getLimitLetsValidThrough: " + getLimitLetsValidThrough());

        System.out.println(getLimitStopsInvalid(100000000));

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
                    e.printStackTrace();
                }
            }
        }
        return !results.containsValue(false);
    }

    static boolean getLimitStopsInvalid(int pingCount) {
        long parsedLimit = 0;
        for (int i = 0; i < pingCount; i++) {
            String testInput = generateRandomLimit();
            String[] testArgs = {"-d", "c:/", "-l", testInput};
            ParametersParser parser = new ParametersParser(testArgs);
            try {
                parsedLimit = parser.getLimit();
                if (testInput.contains(".") ) {
                    System.out.println(testInput + " => " + parsedLimit);
                }
            } catch (IllegalArgumentException e) {
//                System.out.println("Invalid reflected: " + testInput);
            } catch (Exception e) {
                System.out.println("Invalid penetrated: " + testInput);
                return false;
            }
        }
        System.out.print("getLimitStopsInvalid: ");
        return true;
    }

    static String generateRandomLimit() {
        Random randomChar = new Random();
        Random randomLength = new Random();
        int length = randomLength.nextInt(1, 7);
        StringBuilder testInput = new StringBuilder();
        for (int i = 0; i < length; i++) {
            testInput.append((char) randomChar.nextInt(32, 127));
        }
        return testInput.toString();
    }
}

// Produced by ChatGPT:
//        for (int i = 32; i < 127; i++) {
//            char c = (char) i;
//            System.out.println(i + " " + c);
//        }