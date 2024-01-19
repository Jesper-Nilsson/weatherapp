package se.miun.dt170g;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import se.miun.dt170g.Network.FetchWeather;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.met.no";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWeatherData(1,1,1);
    }

    public void getWeatherData(double latitude, double longitude, int altitude) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FetchWeather service = retrofit.create(FetchWeather.class);

        Call<Weather> weatherCall = service.getWeather(latitude, longitude, altitude);

        weatherCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()) {
                    // Handle the successful response here
                    Weather weatherData = response.body();
                    // TODO: Process the weather data
                } else {
                    // Handle the error response
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                // Handle the failure, like network error
            }
        });

    }
}