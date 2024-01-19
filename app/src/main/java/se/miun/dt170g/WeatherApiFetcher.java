package se.miun.dt170g;

import android.os.Handler;
import android.util.Pair;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;

public class WeatherApiFetcher {
    private Handler handler;
    private String url;
    private WeatherListener weatherListener;

    public WeatherApiFetcher(Handler handler, String url, WeatherListener listener){
        this.handler = handler;
        this.url = url;
        this.weatherListener = listener;
    }
    public static interface WeatherListener {
        void onWeatherFetched(Weather weather);
        void onWeatherFetchFailed(Exception e);
    }

    public void fetchAndParseWeatherData() {
        String urlString = this.url;
        new Thread(() -> {
            HttpURLConnection connection = null;
            InputStream stream = null;
            try {
                URL url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0"); // Set User-Agent

                int responseCode = connection.getResponseCode();
                Log.d("responseCode", "" +  responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    stream = connection.getInputStream();
                    Weather weather = parseXML(stream);

                    // Assuming 'Log' and 'Weather' classes are defined and working
                    Log.d("WeatherData", "Wind Direction: " + weather.getWindDirection());
                    Log.d("WeatherData", "temprature: " + weather.getTemperature());
                    Log.d("WeatherData", "Wind spped: " + weather.getWindSpeed());
                    Log.d("WeatherData", "cloudiness: " + weather.getCloudiness());
                    Log.d("WeatherData", "symbol: " + weather.getSymbol());
                    Log.d("WeatherData", "rain min: " + weather.getPrecipitation().first);
                    Log.d("WeatherData", "rain max: " + weather.getPrecipitation().second);
                    Log.d("WeatherData", "symbol: " + weather.getSymbol());
                    // Update UI with weather object (on the main thread)
                    handler.post(() -> {
                        weatherListener.onWeatherFetched(weather);
                        // Update UI or handle weather object on main thread
                        // Example: updateWeatherUI(weather);
                    });
                } else {
                    Log.e("WeatherData", "HTTP error response: " + responseCode);
                }
            } catch (Exception e) {
                handler.post(() -> {
                    weatherListener.onWeatherFetchFailed(e);
                });
                e.printStackTrace();
                // Handle errors appropriately
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();

    }


    private Weather parseXML(InputStream stream) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(stream, null);

        Weather weather = new Weather();
        int forecastCounter = 0;

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagName = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (tagName.equals("time")) {
                        String dataType = parser.getAttributeValue(null, "datatype");
                        if ("forecast".equals(dataType)) {
                            forecastCounter++;
                        }
                    } else if (forecastCounter == 1) { // First <time> element with forecast
                        switch (tagName) {
                            case "temperature":
                                weather.setTemperature(Double.parseDouble(parser.getAttributeValue(null, "value")));
                                break;
                            case "windDirection":
                                weather.setWindDirection(parser.getAttributeValue(null, "name"));
                                break;
                            case "windSpeed":
                                weather.setWindSpeed(Double.parseDouble(parser.getAttributeValue(null, "mps")));
                                break;
                            case "cloudiness":
                                weather.setCloudiness(Double.parseDouble(parser.getAttributeValue(null, "percent")));
                                break;
                        }
                    } else if (forecastCounter == 2) { // Third <time> element with forecast
                        switch (tagName) {
                            case "precipitation":
                                double precipitationValue = Double.parseDouble(parser.getAttributeValue(null, "value"));
                                weather.setPrecipitation(new Pair<>(precipitationValue, precipitationValue)); // Adapt as needed
                                break;
                            case "symbol":
                                weather.setSymbol(parser.getAttributeValue(null, "id"));
                                break;
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }

        return weather;
    }



}

