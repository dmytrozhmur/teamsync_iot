package ua.nure;

import ua.nure.auth.Authorization;
import ua.nure.models.EnvironmentConditions;
import ua.nure.models.HealthConditions;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsoleApplication {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String URL = "listener url";
    public static boolean start(String... args) {
        Authorization authorization = new Authorization();
        String login = ConsoleUtils.getUserInput(LOGIN);
        String password = ConsoleUtils.getUserInput(PASSWORD);
        if (!authorization.authorized(login, password)) {
            System.err.println("Bad credentials, try again.");
            return false;
        }

        while (true) {
            String action = ConsoleUtils.getStartAction();
            switch (action) {
                case "settings":
                    handleSettingsChange();
                    break;
                case "start":
                    handleConditionsReading(authorization);
                    break;
            }
        }
//        return true;
    }

    private static void handleConditionsReading(Authorization auth) {
        HealthConditions healthCondition = ConsoleUtils.indicateHealth();
        EnvironmentConditions environmentCondition = ConsoleUtils.indicateEnv();
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = Settings.getInstance().getListenerUrl();
        String requestBody = String.format("{\"healthCondition\":\"%s\"," +
                "\"environmentCondition\":\"%s\"}", healthCondition.getState(), environmentCondition.getState());
        System.out.printf("Sending request to %s with request body %s\n", url, requestBody);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, auth.getLogin())))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Response was successfully sent");
            } else {
                System.err.println("Server returned " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Exception during sending the request: " + e);;
        }

    }

    private static void handleSettingsChange() {
        Settings settings = Settings.getInstance();
        settings.setListenerUrl(ConsoleUtils.getUserInput(URL));
    }
}
