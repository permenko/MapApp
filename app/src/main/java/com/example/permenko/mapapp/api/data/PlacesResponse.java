package com.example.permenko.mapapp.api.data;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public final class PlacesResponse implements Serializable {
  @SerializedName("pageSize") private int pageSize;
  @SerializedName("hasMorePages") private boolean hasMorePages;
  @SerializedName("data") @NonNull private List<PlaceResponse> places;

  public PlacesResponse(int pageSize, boolean hasMorePages, @NonNull List<PlaceResponse> places) {
    this.pageSize = pageSize;
    this.hasMorePages = hasMorePages;
    this.places = places;
  }

  public int pageSize() {
    return pageSize;
  }

  public boolean hasMorePages() {
    return hasMorePages;
  }

  @NonNull public List<PlaceResponse> places() {
    return places;
  }
}
