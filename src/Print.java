package src;

import java.util.List;
import java.util.Vector;

public class Print {
    // ANSI codes: https://stackoverflow.com/a/5762502/14225169
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BLACK_BG = "\u001B[40m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String PURPLE_BG = "\u001B[45m";
    public static final String CYAN_BG = "\u001B[46m";
    public static final String WHITE_BG = "\u001B[47m";

    // handle printing to console using method overloading
    public static void print(String printThis, boolean noNewLine) {
        if (noNewLine) {
            System.out.print(printThis);
        }
    }

    public static void print(String printThis) {
        System.out.println(printThis);
    }

    public static void print(String printThis, String color) {
        System.out.println(color + printThis + RESET);
    }

    public static void print(Integer printThis) {
        System.out.println(printThis);
    }

    public static void print(Vector<Product> printThis) {
        for (Product element : printThis) {
            System.out.println(element);
            System.out.println();
        }
    }

    public static String getDashes(int numOfDash) {
        String dashes = "";
        for (int i = 0; i < numOfDash; i++) {
            dashes += "-";
        }
        return dashes;
    }

    // modified version of https://stackoverflow.com/a/50649715/14225169
    public static String formatAsTable(List<List<String>> rows, String headerColor, String color) {
        int[] maxLengths = new int[rows.get(0).size()];
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
            }
        }

        StringBuilder formatBuilder = new StringBuilder();
        for (int maxLength : maxLengths) {
            formatBuilder.append("%-").append(maxLength + 2).append("s");
        }
        String format = formatBuilder.toString();

        StringBuilder result = new StringBuilder();
        int reqDashes = String.format(format, rows.get(0).toArray()).length() + 1;

        result.append(headerColor);
        result.append(getDashes(reqDashes)).append("\n");

        for (List<String> row : rows) {
            if (rows.indexOf(row) == 0) {
                result.append(String.format(format, row.toArray())).append("|").append("\n");
                result.append(RESET);
                result.append(color);
            } else {
                result.append(String.format(format, row.toArray())).append("|").append("\n");
            }
        }

        result.append(getDashes(reqDashes));
        result.append(RESET);

        return result.toString();
    }
}
