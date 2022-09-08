package org.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class E {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("inputE.txt");
             FileWriter writer = new FileWriter("outputE.txt")) {
            Scanner scanner = new Scanner(reader);
            String expr = scanner.nextLine();
            scanner.close();
            //writer.write(findWrongSequence(expr));
            System.out.println(findWrongSequence(expr));

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private static boolean goodBalance(String expr, int ignore) {
        int depth = 0;
        for (int i = 0; i < expr.length(); ++i) {
            if (i == ignore) {
                continue;
            }
            switch (expr.charAt(i)) {
                case '{':
                    ++depth;
                    break;
                case '}':
                    if (depth == 0) {
                        return false;
                    }
                    --depth;
                    break;
            }
        }
        return depth == 0;
    }

    private static int findSuspiciousSequence(String expr) {
        int depth = 0;
        int first_closing = -1;
        int last_opening_at_zero_depth = -1;
        for (int i = 0; i < expr.length(); ++i) {
            switch (expr.charAt(i)) {
                case '{':
                    if (depth == 0) {
                        last_opening_at_zero_depth = i;
                    }
                    ++depth;
                    break;
                case '}':
                    if (first_closing == -1) {
                        first_closing = i;
                    }
                    --depth;
                    break;
            }
        }
        if (depth == 1) {
            return last_opening_at_zero_depth;
        }
        if (depth == -1) {
            return first_closing;
        }
        return -1;
    }

    private static int findWrongSequence(String expr) {
        int ignore = findSuspiciousSequence(expr);
        if (ignore != -1 && goodBalance(expr, ignore)) {
            return ignore + 1;
        }
        return -1;
    }


}
