package com.example.permenko.mapapp.db.data;

import android.support.annotation.NonNull;
import com.example.permenko.mapapp.ui.data.Place;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class PlaceRealmModel implements RealmModel {
  @PrimaryKey
  private int id;
  private int categoryId;
  @NonNull private String name;
  private double lat;
  private double lng;

  public PlaceRealmModel() {
  }

  public PlaceRealmModel(@NonNull Place place) {
    this.id = place.id();
    this.categoryId = place.categoryId();
    this.name = place.name();
    this.lat = place.lat();
    this.lng = place.lng();
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
