package se.miun.dt170g;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTemperature, tvWindSpeed, tvCloudiness, tvPrecipitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTemperature = findViewById(R.id.tvTemperature);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        tvCloudiness = findViewById(R.id.tvCloudiness);
        tvPrecipitation = findViewById(R.id.tvPrecipitation);

        fetchWeatherData();
    }

    private void fetchWeatherData() {
        double staticTemperature = 15.2;
        double staticWindSpeed = 5.5;
        String staticWindDirection = "NE";
        double staticCloudiness = 65.3;
        Pair<Double, Double> staticPrecipitation = new Pair<>(1.2, 3.4);

        tvTemperature.setText(getString(R.string.temperature_value, staticTemperature));
        tvWindSpeed.setText(getString(R.string.windspeed_value, staticWindSpeed, staticWindDirection));
        tvCloudiness.setText(getString(R.string.cloudiness_value, staticCloudiness));
        tvPrecipitation.setText(getString(R.string.precipitation_value, staticPrecipitation.first, staticPrecipitation.second));
    }
}


/*
    private void fetchWeatherData() {
        // Create Retrofit instance and call the API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("YOUR_BASE_URL") // Replace with your base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Assuming WeatherInterfaceApi is your interface with the API call method
        WeatherInterfaceApi apiService = retrofit.create(WeatherInterfaceApi.class);

        // Replace with actual latitude and longitude
        Call<Weather> call = apiService.getWeatherData(59.3293, 18.0686);

        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Weather weather = response.body();

                    // Now set the values from the response to the TextViews
                    tvTemperature.setText(getString(R.string.temperature_value, weather.getTemprature()));
                    tvWindSpeed.setText(getString(R.string.windspeed_value, weather.getWindSpeed(), weather.getWindDirection()));
                    tvCloudiness.setText(getString(R.string.cloudiness_value, weather.getCloudiness()));
                    Pair<Double, Double> precipitation = weather.getPrecipitation();
                    tvPrecipitation.setText(getString(R.string.precipitation_value, precipitation.first, precipitation.second));
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                // Handle the error scenario
            }
        });


    }
}
   */