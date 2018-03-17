package com.example.permenko.mapapp.ui.map;

import android.support.annotation.NonNull;
import com.example.permenko.mapapp.api.ApiFactory;
import com.example.permenko.mapapp.api.FuraApi;
import com.example.permenko.mapapp.api.data.PlaceResponse;
import com.example.permenko.mapapp.api.data.PlacesResponse;
import com.example.permenko.mapapp.ui.data.Place;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

public class MapPresenter {
  private final FuraApi furaApi;

  public MapPresenter() {
    furaApi = ApiFactory.furaApi();
  }

  @NonNull Observable<Place> places() {
    return furaApi.places()
        .map(PlacesResponse::places)
        .map(placeResponseList -> {
          List<Place> places = new ArrayList<>();
          for (PlaceResponse placeResponse : placeResponseList) {
            places.add(Place.create(placeResponse));
          }
          return places;
        })
        .flatMap(Observable::from);
  }
}
