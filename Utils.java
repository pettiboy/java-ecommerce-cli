package UML;

import java.util.Scanner;
import java.util.Vector;

public class Utils {

    public static int showOptions(Vector<String> options, Scanner scanner) {
        // create a vector to store valid options ids
        Vector<Integer> validOptions = new Vector<>(options.size());

        // add option ids to the vector and print what that option does
        for (int i = 0; i < options.size(); i++) {
            validOptions.add(i + 1);
            print(i + 1 + ") " + options.get(i));
        }

        // keep asking user for input till correct choice is chosen
        while (true) {
            Scanner input = scanner;
            int choice = Integer.parseInt(input.next());
            if (validOptions.contains(choice)) {
                return choice - 1;
            }
            print("Invalid choice. Try again.");
        }
    }

    public static String getIntInRange(int minLength, int maxLength, Scanner scanner) {
        int validInteger = -1;
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                validInteger = scanner.nextInt();
                if (String.valueOf(validInteger).length() <= maxLength
                        && String.valueOf(validInteger).length() >= minLength) {
                    break;
                } else {
                    print("Invalid input integer. Try again...");
                }
            } else {
                print("Invalid input. Must be an integer, try again...");
                scanner.next();
            }
        }
        return Integer.toString(validInteger);
    }

    // handle printing to console using method overloading
    public static void print(String printThis, boolean noNewLine) {
        if (noNewLine) {
            System.out.print(printThis);
        }
    }

    public static void print(String printThis) {
        System.out.println(printThis);
    }

    public static void print(Integer printThis) {
        System.out.println(printThis);
    }
    
    public static void print(Vector<String> printThis) {
        System.out.println(printThis);
    }
}
