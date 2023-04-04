import java.util.Arrays;

public class SizeConverter {
    static final String[] UNITS = {"b", "Kb", "Mb", "Gb", "Tb"};

    public static String getHumanReadableSize(long size) {
        for (int i = 0; i < UNITS.length; i++) {
            double value = size / Math.pow(1024, i);
            if (value < 1024) {
                return Math.round(value * 100)/100. + UNITS[i];
            }
        }
        return "Enormous!";
    }

    // TODO: REFACTOR FOR PROPER FLOAT HANDLING
    public static long getSizeFromHumanReadable(String size) {
        int numericLength = 0;
        for (char c : size.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                numericLength += 1;
            }  else {
                break;
            }
        }
        String numericPart = size.substring(0, numericLength);
        String unit = size.substring(numericLength);
        int power = Arrays.asList(UNITS).indexOf(unit);
        return (long) (Double.parseDouble(numericPart) * Math.pow(1024, power));
    }
}
