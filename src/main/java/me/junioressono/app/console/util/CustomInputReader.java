package me.junioressono.app.console.util;

import java.util.Scanner;

public class CustomInputReader {
    private final Scanner scanner = new Scanner(System.in);

    public String readString(String message) {
        System.out.print(message);
        return scanner.next();
    }

    public int readNumber(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }
}
