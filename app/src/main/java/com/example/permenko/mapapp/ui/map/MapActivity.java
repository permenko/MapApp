package com.example.permenko.mapapp.ui.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.example.permenko.mapapp.R;
import com.example.permenko.mapapp.injection.provider.MapPresenterProvider;
import com.example.permenko.mapapp.ui.data.Place;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.maps.android.clustering.ClusterManager;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, MapView {
  private GoogleMap map;
  private ClusterManager clusterManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
    MapPresenterProvider.mapPresenter().attachView(this);
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    map = googleMap;
  }

  @Override public void showPlaces(@NonNull Place place) {
    if (clusterManager == null) clusterManager = new ClusterManager<Place>(this, map);
    map.setOnMarkerClickListener(clusterManager);
    clusterManager.addItem(place);
    clusterManager.cluster();
    //map.addMarker(new MarkerOptions().position(new LatLng(place.lat(), place.lng())).title(place.name()));
  }
}
