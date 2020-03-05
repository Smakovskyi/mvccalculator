package ua.kpi.calculator.controller;

@FunctionalInterface
public interface Command {
    String execute(String command);
}
