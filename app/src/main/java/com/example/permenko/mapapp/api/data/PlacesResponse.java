package com.example.permenko.mapapp.api.data;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public final class PlacesResponse implements Serializable {
  @SerializedName("pageSize") private int pageSize;
  @SerializedName("data") @NonNull private Data data;

  public PlacesResponse(int pageSize, @NonNull Data data) {
    this.pageSize = pageSize;
    this.data = data;
  }

  public int getPageSize() {
    return pageSize;
  }

  public List<PlaceResponse> places() {
    return data.places;
  }

  private final class Data {
    @NonNull private List<PlaceResponse> places;

    public Data(@NonNull List<PlaceResponse> places) {
      this.places = places;
    }
  }
}
