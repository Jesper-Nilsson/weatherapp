package se.miun.dt170g;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterfaceApi {

    // Retrofit Interface
        @GET("weather")
        Call<WeatherResponse> getWeatherData(
                @Query("lat") double latitude,
                @Query("lon") double longitude,
                @Query("appid") String apiKey
        );
    }