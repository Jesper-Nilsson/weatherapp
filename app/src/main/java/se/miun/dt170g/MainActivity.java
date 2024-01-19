package se.miun.dt170g;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTemperature, tvWindSpeed, tvCloudiness, tvPrecipitation;
    private ImageView imageView;
    private Button button;

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the refresh action here
                fetchWeatherData2(); // You can call your data fetching method here
            }
        });


        fetchWeatherData();
    }

    private void fetchWeatherData() {
        // Example static values for debugging
        double staticTemperature = 15.2;
        double staticWindSpeed = 5.5;
        String staticWindDirection = "NE";
        double staticCloudiness = 65.3;
        Pair<Double, Double> staticPrecipitation = new Pair<>(1.2, 3.4);

        // Example of setting an image based on a condition
        String imageName = "clearsky_day"; // The name of the image, without extension
        int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        if (resourceId != 0) { // 0 means no resource found
            imageView.setImageResource(resourceId);
        } else {
            // Handle the case where the resource is not found
            // For example, set a default image
            imageView.setImageResource(R.drawable.fog); // Replace with your default image
        }

        // Set texts of TextViews
        tvTemperature.setText(getString(R.string.temperature_value, staticTemperature));
        tvWindSpeed.setText(getString(R.string.windspeed_value, staticWindSpeed, staticWindDirection));
        tvCloudiness.setText(getString(R.string.cloudiness_value, staticCloudiness));
        tvPrecipitation.setText(getString(R.string.precipitation_value, staticPrecipitation.first, staticPrecipitation.second));
    }



    private void fetchWeatherData2() {
        // Example static values for debugging
        double staticTemperature = 16.2;
        double staticWindSpeed = 5.5;
        String staticWindDirection = "NE";
        double staticCloudiness = 65.3;
        Pair<Double, Double> staticPrecipitation = new Pair<>(1.2, 3.4);

        // Example of setting an image based on a condition
        String imageName = "snow"; // The name of the image, without extension
        int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        if (resourceId != 0) { // 0 means no resource found
            imageView.setImageResource(resourceId);
        } else {
            // Handle the case where the resource is not found
            // For example, set a default image
            imageView.setImageResource(R.drawable.fog); // Replace with your default image
        }

        // Set texts of TextViews
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