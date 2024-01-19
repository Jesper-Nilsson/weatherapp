package se.miun.dt170g;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherApiFetcher test  = new WeatherApiFetcher();
        test.fetchAndParseWeatherData("https://api.met.no/weatherapi/locationforecast/2.0/classic?lat=59.93&lon=10.72&altitude=90");


    }

}