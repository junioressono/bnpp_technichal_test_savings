package me.junioressono.app.shell.controllers;

import me.junioressono.app.shell.AppShell;

public class LeaveApplicationController implements Controller {
    private final AppShell shellController;

    public LeaveApplicationController(AppShell shellController) {
        this.shellController = shellController;
    }

    @Override
    public void handle() {
        System.out.println("""
                    You have decide to leave application.
                    
                    GOOD BYE !!!
                    """);
        shellController.isRunning = false;
        System.exit(0);
    }
}
