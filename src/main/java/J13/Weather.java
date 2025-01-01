package J13;

import java.util.Date;

public class Weather {
    private Date date;
    private double temperature;
    private String precipitation;

    public Weather(Date date, double temperature, String precipitation) {
        this.date = date;
        this.temperature = temperature;
        this.precipitation = precipitation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    @Override
    public String toString() {
        return String.format("Дата: %s, Температура: %.1f, Осадки: %s.", date, temperature, precipitation);
    }
}

