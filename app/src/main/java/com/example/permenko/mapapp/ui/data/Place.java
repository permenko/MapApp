package com.example.permenko.mapapp.ui.data;

import android.support.annotation.NonNull;
import com.example.permenko.mapapp.api.data.PlaceResponse;

public class Place {
  private int id;
  private int categoryId;
  @NonNull private String name;
  private long lat;
  private long lng;

  public Place(int id, int categoryId, @NonNull String name, long lat, long lng) {
    this.id = id;
    this.categoryId = categoryId;
    this.name = name;
    this.lat = lat;
    this.lng = lng;
  }

  @NonNull public static Place create(@NonNull PlaceResponse placeResponse) {
    return new Place(placeResponse.id(), placeResponse.categoryId(), placeResponse.name(), placeResponse.lat(), placeResponse.lng());
  }

  public int id() {
    return id;
  }

  public int categoryId() {
    return categoryId;
  }

  public String name() {
    return name;
  }

  public long lat() {
    return lat;
  }

  public long lng() {
    return lng;
  }
}
