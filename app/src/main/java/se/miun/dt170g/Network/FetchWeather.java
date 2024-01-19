package se.miun.dt170g.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import se.miun.dt170g.Weather;


public interface FetchWeather {
    @GET("/weatherapi/locationforecast/2.0/classic")
    Call<Weather> getWeather(@Query("lat") double latitude,
                             @Query("lon") double longitude,
                             @Query("altitude") int altitude);
}


//c?lat=59.93&lon=10.72&altitude=90


