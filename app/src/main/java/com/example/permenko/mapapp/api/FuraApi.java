package com.example.permenko.mapapp.api;

import com.example.permenko.mapapp.api.data.PlacesResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FuraApi {
  @GET("test/places")
  Observable<PlacesResponse> places(@Query("startFrom") int startId);
}
