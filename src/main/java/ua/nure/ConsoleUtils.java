package ua.nure;

import ua.nure.models.EnvironmentConditions;
import ua.nure.models.HealthConditions;

import java.util.Scanner;

public class ConsoleUtils {
    private static Scanner in = new Scanner(System.in);
    public static String getUserInput(String attribute) {
        System.out.printf("Enter your %s:\n", attribute);
        String input = in.nextLine();
        checkForExit(input);
        return input;
    }

    public static String getStartAction() {
        System.out.println("To edit settings enter 'settings', " +
                "to run conditions reading mode enter 'start':");
        String input = in.nextLine();
        checkForExit(input);
        return input;
    }

    private static void checkForExit(String input) {
        if (input.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }

    public static HealthConditions indicateHealth() {
        System.out.println("Reading health indicators...");
        HealthConditions health = new HealthConditions();
        health.setTemperature(Double.parseDouble(read("temperature")));
        health.setDiastolicPressure(Integer.parseInt(read("diastolic pressure")));
        health.setSystolicPressure(Integer.parseInt(read("systolic pressure")));
        health.setOxygenInBlood(Integer.parseInt(read("oxygen in blood")));
        return health;
    }

    public static EnvironmentConditions indicateEnv() {
        System.out.println("Reading environment indicators...");
        EnvironmentConditions environment = new EnvironmentConditions();
        environment.setTemperature(Integer.parseInt(read("temperature")));
        environment.setHeight(Long.parseLong(read("height")));
        environment.setOxygen(Integer.parseInt(read("oxygen")));
        return environment;
    }

    private static String read(String indicator) {
        System.out.printf("%s equals\n", indicator);
        return in.nextLine();
    }
}
