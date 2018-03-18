package com.example.permenko.mapapp.ui.map;

import android.support.annotation.NonNull;
import com.example.permenko.mapapp.ui.data.Place;

public interface MapView {
  void showPlaces(@NonNull Place place);
}
