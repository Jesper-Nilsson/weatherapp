package se.miun.dt170g;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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

        getWeatherData(59.93,10.72,90);
    }

    // Skapa en interceptor för att anpassa User-Agent
    Interceptor userAgentInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder()
                    .header("User-Agent", "MyUniqueWeatherApp/1.0") // Ersätt med din unika User-Agent
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    };

    public void getWeatherData(double latitude, double longitude, int altitude) {
        // Skapa en OkHttpClient och lägg till interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(userAgentInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
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
                    Log.d("WindDirection", weatherData.getWindDirection());


                } else {
                    // Handle the error response
                    int statusCode = response.code(); // Hämta HTTP-statuskoden
                    Log.d("HTTP Error Code", String.valueOf(statusCode));
                    Log.d("WindDirection", "Fail");

                    // Kontrollera felsvar
                    String errorMessage = "Unknown error";
                    try {
                        if (response.errorBody() != null) {
                            errorMessage = response.errorBody().string(); // Hämta felsvaret som en sträng
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d("Error Response", errorMessage);

                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                // Handle the failure, like network error
            }
        });

    }
}