package se.miun.dt170g;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTemperature, tvWindSpeed, tvCloudiness, tvPrecipitation;
    private ImageView imageView;
    private Button button;

    class MyWeatherListener implements WeatherApiFetcher.WeatherListener{

        @Override
        public void onWeatherFetched(Weather weather) {

            tvTemperature.setText(""+ weather.getTemperature());
        }

        @Override
        public void onWeatherFetchFailed(Exception e) {
            //errors
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
        imageView = findViewById(R.id.ivCloud); // Correct initialization of ImageView
        button = findViewById(R.id.btnRefresh);

        Weather currentWeather = new Weather();
        Handler weatherMessagesToMainThread = new Handler();
        MyWeatherListener listenForWeather = new MyWeatherListener();
        WeatherApiFetcher fetcher = new WeatherApiFetcher(weatherMessagesToMainThread,"https://api.met.no/weatherapi/locationforecast/2.0/classic?lat=59.93&lon=10.72&altitude=90", listenForWeather );





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetcher.fetchAndParseWeatherData();
                // Handle the refresh action here
                // You can call your data fetching method here
            }
        });


        fetchWeatherData();
    }

    private void fetchWeatherData() {


    }


}
