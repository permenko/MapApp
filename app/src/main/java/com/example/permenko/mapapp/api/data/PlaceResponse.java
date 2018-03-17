package com.example.permenko.mapapp.api.data;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class PlaceResponse {
  @SerializedName("id") private int id;
  @SerializedName("category_id") private int categoryId;
  @SerializedName("name") @NonNull private String name;
  @SerializedName("lat") private long lat;
  @SerializedName("lng") private long lng;

  public PlaceResponse(int id, int categoryId, @NonNull String name, long lat, long lng) {
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

  public long lat() {
    return lat;
  }

  public long lng() {
    return lng;
  }
}
