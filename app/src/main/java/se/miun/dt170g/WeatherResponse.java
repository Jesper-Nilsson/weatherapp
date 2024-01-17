package se.miun.dt170g;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "location")
public class WeatherResponse {

    @Element(name = "latitude")
    private double latitude;

    @Element(name = "longitude")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
