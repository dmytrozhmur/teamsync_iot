package ua.nure.models;

public class HealthConditions {
    private double temperature;
    private int systolicPressure;
    private int diastolicPressure;
    private int oxygenInBlood;

    public String getState() {
        int level = 0;
        if (temperature < 35.9 || 37.1 < temperature) level++;
        if (systolicPressure < 119 || 141 < systolicPressure) level++;
        if (diastolicPressure < 79 || 91 < diastolicPressure) level++;
        if (oxygenInBlood < 94) level++;

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

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setSystolicPressure(int systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public void setDiastolicPressure(int diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public void setOxygenInBlood(int oxygenInBlood) {
        this.oxygenInBlood = oxygenInBlood;
    }
}
