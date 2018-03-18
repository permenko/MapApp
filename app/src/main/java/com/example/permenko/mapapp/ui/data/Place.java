package com.example.permenko.mapapp.ui.data;

import android.support.annotation.NonNull;
import com.example.permenko.mapapp.api.data.PlaceResponse;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Place implements ClusterItem {
  private int id;
  private int categoryId;
  @NonNull private String name;
  private double lat;
  private double lng;
  @NonNull private LatLng latLng;

  public Place(int id, int categoryId, @NonNull String name, double lat, double lng) {
    this.id = id;
    this.categoryId = categoryId;
    this.name = name;
    this.lat = lat;
    this.lng = lng;
    latLng = new LatLng(lat, lng);
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

  public double lat() {
    return lat;
  }

  public double lng() {
    return lng;
  }

  @Override public LatLng getPosition() {
    return latLng;
  }
}
