package ua.nure.models;

public class EnvironmentConditions {
    private int temperature;
    private int oxygen;
    private long height;

    public String getState() {
        int level = 0;
        if (temperature < -15 || 35 < temperature) level++;
        if (8000 < height) level++;
        if (oxygen < 17 || 22 < oxygen) level++;

        switch (level) {
            case 0:
                return "GOOD";
            case 1:
                return "NORMAL";
            case 2:
                return "BAD";
            default:
                return "CRITICAL";
        }
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setOxygen(int oxygen) {
        this.oxygen = oxygen;
    }

    public void setHeight(long height) {
        this.height = height;
    }
}
