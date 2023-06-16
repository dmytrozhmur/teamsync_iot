package ua.nure;

public class Settings {
    private static Settings settings;

    private String listenerUrl;

    private Settings() {
        listenerUrl = "https://teamsync.nure.ua/api/v1/%s/conditions/update";
    }

    public static Settings getInstance() {
        if (settings == null) settings = new Settings();
        return settings;
    }

    public String getListenerUrl() {
        return listenerUrl;
    }

    public void setListenerUrl(String listenerUrl) {
        this.listenerUrl = listenerUrl;
    }
}
