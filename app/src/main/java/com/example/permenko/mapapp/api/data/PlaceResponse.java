package com.example.permenko.mapapp.api.data;

import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;

public class PlaceResponse {
  @SerializedName("id") private int id;
  @SerializedName("categoryId") private int categoryId;
  @SerializedName("name") @Nullable private String name;
  @SerializedName("lat") private double lat;
  @SerializedName("lng") private double lng;

  public PlaceResponse(int id, int categoryId, @Nullable String name, double lat, double lng) {
    this.id = id;
    this.categoryId = categoryId;
    this.name = name;
    this.lat = lat;
    this.lng = lng;
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
}
