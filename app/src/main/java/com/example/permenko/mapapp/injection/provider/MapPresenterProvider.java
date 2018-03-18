package com.example.permenko.mapapp.injection.provider;

import android.support.annotation.NonNull;
import com.example.permenko.mapapp.ui.map.MapPresenter;

public class MapPresenterProvider {
  private static MapPresenter mapPresenter;

  @NonNull public static MapPresenter mapPresenter() {
    if (mapPresenter == null) mapPresenter = new MapPresenter();
    return mapPresenter;
  }
}
