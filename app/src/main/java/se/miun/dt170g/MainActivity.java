package se.miun.dt170g;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvTemperature, tvWindSpeed, tvCloudiness, tvPrecipitation;
    private ImageView imageView;
    private Button button;
    private WeatherApiFetcher fetcher;

    class MyWeatherListener implements WeatherApiFetcher.WeatherListener {
        @Override
        public void onWeatherFetched(Weather weather) {
            String temperature = "Temperature: " + weather.getTemperature() + " Celsius";
            String windSpeed = "Wind Speed: " + weather.getWindSpeed() + " mps, towards " + weather.getWindDirection();
            String cloudiness = "Cloudiness: " + weather.getCloudiness() + " %";
            String precipitation = "Precipitation: Between " + weather.getPrecipitation().first + " mm and " + weather.getPrecipitation().second + " mm";
            String imageName = weather.getSymbol();
            int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());

            if (resourceId != 0) {
                imageView.setImageResource(resourceId);
            } else {
                imageView.setImageResource(R.drawable.fog);
            }

            tvTemperature.setText(temperature);
            tvWindSpeed.setText(windSpeed);
            tvCloudiness.setText(cloudiness);
            tvPrecipitation.setText(precipitation);
        }

        @Override
        public void onWeatherFetchFailed(Exception e) {
            // Log the error
            Log.e("MainActivity", "Weather fetch failed", e);

            // Show a toast message to the user
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Failed to fetch weather data", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TextViews, ImageView, and Button
        tvTemperature = findViewById(R.id.tvTemperature);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        tvCloudiness = findViewById(R.id.tvCloudiness);
        tvPrecipitation = findViewById(R.id.tvPrecipitation);
        imageView = findViewById(R.id.ivCloud);
        button = findViewById(R.id.btnRefresh);

        // Initialize WeatherApiFetcher with the WeatherListener
        MyWeatherListener listenForWeather = new MyWeatherListener();
        fetcher = new WeatherApiFetcher(new Handler(Looper.getMainLooper()), "https://api.met.no/weatherapi/locationforecast/2.0/classic?lat=59.93&lon=10.72&altitude=90", listenForWeather);

        // Fetch weather data when the app starts
        fetchWeatherData();

        // Set up button click listener to refresh weather data
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchWeatherData();
            }
        });
    }

    private void fetchWeatherData() {
        fetcher.fetchAndParseWeatherData();
    }
}
