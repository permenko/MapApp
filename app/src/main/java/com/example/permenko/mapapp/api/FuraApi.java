package com.example.permenko.mapapp.api;

import com.example.permenko.mapapp.api.data.PlacesResponse;
import retrofit2.http.GET;
import rx.Observable;

public interface FuraApi {
  @GET("/test/places")
  Observable<PlacesResponse> places();
}
