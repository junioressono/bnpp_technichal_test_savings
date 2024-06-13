package me.junioressono.app.console.controllers;

import me.junioressono.app.console.AppConsole;

public class LeaveApplicationController implements Controller {
    private final AppConsole shellController;

    public LeaveApplicationController(AppConsole shellController) {
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
