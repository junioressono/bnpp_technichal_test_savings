package me.junioressono.app.shell;

import java.util.Scanner;

public class ViewHandler {
    public boolean isRunning = true;
    private final Scanner scanner = new Scanner(System.in);

    public String readLine(String message) {
        System.out.print(message);
        return scanner.next();
    }
    public int readNumber(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }
}
