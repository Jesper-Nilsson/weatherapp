package se.miun.dt170g;

import android.util.Pair;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "location", strict = false)
public class Weather {
    @Element(name="temperature")
    private double temprature;
    @Element(name="windSpeed")
    private double windSpeed;
    @Element(name="windDirection")
    private String windDirection;
    @Element(name="cloudiness")
    private double cloudiness;
    private Pair<Double, Double> precipitation;

    private String weatherImage;

    public Weather(double temprature, double windSpeed, String windDirection, double cloudiness, Pair<Double, Double> precipitation) {
        this.temprature = temprature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.cloudiness = cloudiness;
        this.precipitation = precipitation;
        chooseWeatherImage();
    }

    private void chooseWeatherImage(){

    }

    public double getTemprature() {
        return temprature;
    }

    public void setTemprature(double temprature) {
        this.temprature = temprature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public double getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(double cloudiness) {
        this.cloudiness = cloudiness;
    }

    public Pair<Double, Double> getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Pair<Double, Double> precipitation) {
        this.precipitation = precipitation;
    }
}
