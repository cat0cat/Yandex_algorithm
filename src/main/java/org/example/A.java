package org.example;

import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String a = scanner.nextLine();
        char[] aArr = a.toCharArray();

        String b = scanner.nextLine();
        char[] bArr = b.toCharArray();
        scanner.close();

        int length = aArr.length;

        if (bArr.length == length) {

            char[] result = new char[length];

            // "plagiarism" check
            for (int i = 0; i < length; i++) {
                if (bArr[i] == aArr[i]) {
                    result[i] = 'P';
                    aArr[i] = (char) ((int) aArr[i] | 32);
                }
            }

            // "innocent" check
            for (int i = 0; i < length; i++) {
                if (result[i] != 'P') {
                    for (int j = 0; j < aArr.length; j++) {
                        if (bArr[i] == aArr[j]) {
                            aArr[j] = (char) ((int) aArr[j] | 32);
                            result[i] = 'S';
                            break;
                        }
                    }
                }
            }

            // "suspicious" check
            for (int i = 0; i < length; i++) {
                if (result[i] == '\0') {
                    result[i] = 'I';
                }
            }

            for (char c : result) {
                System.out.print(c);
            }
        }
    }

}