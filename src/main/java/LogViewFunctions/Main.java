package LogViewFunctions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("1,200");
        stringList.add("");
        stringList.add("583,525");
        stringList.add("1");
        stringList.add("83,300");
        stringList.add("95,625");
        stringList.add("Apple, Banana");
        stringList.add("Cat");

        List<String> numericList = new ArrayList<>();
        numericList.add("1,200");
        numericList.add("");
        numericList.add("583,525");
        numericList.add("1");
        numericList.add("83,300");
        numericList.add("95,625");

        // Custom comparator for ascending order
        Comparator<String> ascendingComparator = (s1, s2) -> {
            // Treat empty strings as smaller
            if (s1.isEmpty()) {
                return s2.isEmpty() ? 0 : -1;
            } else if (s2.isEmpty()) {
                return 1;
            } else {
                // Remove commas and check if strings are numeric
                String num1 = s1.replaceAll(",", "");
                String num2 = s2.replaceAll(",", "");
                boolean isNumeric1 = isNumeric(num1);
                boolean isNumeric2 = isNumeric(num2);

                if (isNumeric1 && isNumeric2) {
                    // Both strings are numeric, compare them numerically
                    return compareNumeric(num1, num2);
                } else if (isNumeric1 || isNumeric2) {
                    // One string is numeric and the other is non-numeric
                    // Treat numeric string as smaller
                    return isNumeric1 ? -1 : 1;
                } else {
                    // Both strings are non-numeric, perform lexicographical comparison
                    return s1.compareToIgnoreCase(s2);
                }
            }
        };

        // Custom comparator for descending order
        Comparator<String> descendingComparator = ascendingComparator.reversed();

        // Sort the original list in ascending order using the ascending comparator
        Collections.sort(stringList, ascendingComparator);
        System.out.println("String List (Ascending Order): " + stringList);

        // Sort the original list in descending order using the descending comparator
        Collections.sort(stringList, descendingComparator);
        System.out.println("String List (Descending Order): " + stringList);

        // Sort the original list in ascending order using the ascending comparator
        Collections.sort(numericList, ascendingComparator);
        System.out.println("Numeric List (Ascending Order): " + numericList);

        // Sort the original list in descending order using the descending comparator
        Collections.sort(numericList, descendingComparator);
        System.out.println("Numeric List (Descending Order): " + numericList);
    }

    // Method to check if a string is numeric
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    // Method to compare numeric strings
    private static int compareNumeric(String s1, String s2) {
        double num1 = Double.parseDouble(s1);
        double num2 = Double.parseDouble(s2);
        return Double.compare(num1, num2);
    }
}
